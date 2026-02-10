package org.oyenavneet.sec02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class Lec03MonoSubscribe {
    private static final Logger logger =  LoggerFactory.getLogger(Lec03MonoSubscribe.class);

    public static void main(String[] args) {
        var mono = Mono.just("oyenavneet")
                .map(i->i+"sri");

        mono.subscribe(
                i->logger.info("Received :{}",i),
                err->logger.error("error", err),
                ()->logger.info("Completed"),
                subscription -> subscription.request(1)
        );
    }
}
