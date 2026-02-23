package org.oyenavneet.sec13;

import org.oyenavneet.common.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.context.Context;


/*
    contextWrite() will propagate Context to all the upstream producer which are concatWith the main producer
    If you want to not receive context to any of producer then use : producer2().contextWrite(ctx -> Context.empty()

 */
public class Lec03ContextPropagation {

    private static final Logger logger = LoggerFactory.getLogger(Lec03ContextPropagation.class);

    public static void main(String[] args) {
        getWelcomeMessage()
                .concatWith(Flux.merge(producer1(), producer2().contextWrite(ctx -> Context.empty())))
                .contextWrite(Context.of("user","sw"))
                .subscribe(Utils.subscriber());


        Utils.sleepSeconds(5);
    }

    private static Mono<String> getWelcomeMessage() {
        return Mono.deferContextual(ctx -> {

            if(ctx.hasKey("user")) {
                return Mono.just("Welcome %s".formatted(ctx.<String>get("user")));
            }
            return Mono.error(new Exception("Unauthenticated"));
        });
    }

    private static Mono<String> producer1(){
        return Mono.<String>deferContextual(ctx -> {
            logger.info("Producer 1 {}", ctx);
            return Mono.empty();
        })
                .subscribeOn(Schedulers.boundedElastic());
    }


    private static Mono<String> producer2(){
        return Mono.<String>deferContextual(ctx -> {
                    logger.info("Producer 2 {}", ctx);
                    return Mono.empty();
                })
                .subscribeOn(Schedulers.parallel());
    }
}
