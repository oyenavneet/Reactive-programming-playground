package org.oyenavneet.sec13;


import org.oyenavneet.common.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

/*
    Context is for providing metadata about the request (Similar to HTTP headers)
     - Reactor provide deferContextual() it have ContextView and you have to provide a Publisher
     - To pass Context value from subscriber it provide contextWrite() in which we can pass key/value pair
     - deferContextual(), contextWrite() can be used for authentication, authorization etc.

 */
public class Lec01Context {


    private static final Logger logger = LoggerFactory.getLogger(Lec01Context.class);


    public static void main(String[] args) {
        getWelcomeMessage()
                .contextWrite(Context.of("user","sw"))
                .subscribe(Utils.subscriber());
    }

    private static Mono<String> getWelcomeMessage() {
        return Mono.deferContextual(ctx -> {

            if(ctx.hasKey("user")) {
                return Mono.just("Welcome %s".formatted(ctx.<String>get("user")));
            }
            return Mono.error(new Exception("Unauthenticated"));
        });
    }


}
