package org.oyenavneet.sec05;

import org.oyenavneet.common.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Lec05Subscribe {

    private static  final Logger logger = LoggerFactory.getLogger(Lec05Subscribe.class);

    public static void main(String[] args) {


        Flux.range(1,10)
                .doOnNext(i->logger.info("received{}",i))
                .doOnComplete(()->logger.info("completed"))
                .doOnError(err -> logger.error("error",err))
                .subscribe();
    }
}
