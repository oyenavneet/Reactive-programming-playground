package org.oyenavneet.sec05;

import org.oyenavneet.common.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Lec03DoCallback {
    private static final Logger logger = LoggerFactory.getLogger(Lec03DoCallback.class);

    public static void main(String[] args) {

        Flux.<Integer>create(fluxSink -> {
                    logger.info("producer begins");
                    for (int i = 0; i < 4; i++) {
                        fluxSink.next(i);
                    }
                    fluxSink.complete();
                    // fluxSink.error(new RuntimeException("oops"));
                    logger.info("producer ends");
                })
                .doOnComplete(() -> logger.info("doOnComplete-1"))
                .doFirst(() -> logger.info("doFirst-1"))
                .doOnNext(item -> logger.info("doOnNext-1: {}", item))
                .doOnSubscribe(subscription -> logger.info("doOnSubscribe-1: {}", subscription))
                .doOnRequest(request -> logger.info("doOnRequest-1: {}", request))
                .doOnError(error -> logger.info("doOnError-1: {}", error.getMessage()))
                .doOnTerminate(() -> logger.info("doOnTerminate-1")) // complete or error case
                .doOnCancel(() -> logger.info("doOnCancel-1"))
                .doOnDiscard(Object.class, o -> logger.info("doOnDiscard-1: {}", o))
                .doFinally(signal -> logger.info("doFinally-1: {}", signal)) // finally irrespective of the reason
                // .take(2)
                .doOnComplete(() -> logger.info("doOnComplete-2"))
                .doFirst(() -> logger.info("doFirst-2"))
                .doOnNext(item -> logger.info("doOnNext-2: {}", item))
                .doOnSubscribe(subscription -> logger.info("doOnSubscribe-2: {}", subscription))
                .doOnRequest(request -> logger.info("doOnRequest-2: {}", request))
                .doOnError(error -> logger.info("doOnError-2: {}", error.getMessage()))
                .doOnTerminate(() -> logger.info("doOnTerminate-2")) // complete or error case
                .doOnCancel(() -> logger.info("doOnCancel-2"))
                .doOnDiscard(Object.class, o -> logger.info("doOnDiscard-2: {}", o))
                .doFinally(signal -> logger.info("doFinally-2: {}", signal)) // finally irrespective of the reason
                //.take(4)
                .subscribe(Utils.subscriber("subscriber"));


    }

}
