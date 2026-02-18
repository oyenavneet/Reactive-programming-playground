package org.oyenavneet.sec07;

import org.oyenavneet.common.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/*
    publishOn is for Downstream
 */

public class Lec05PublishOn {


    public static final Logger logger = LoggerFactory.getLogger(Lec05PublishOn.class);

    public static void main(String[] args) {

        var flux = Flux.create(sink->{
                    for (int i=1; i<3; i++) {
                        logger.info("generating: {} ", i);
                        sink.next(i);
                    }
                    sink.complete();
                })
                .publishOn(Schedulers.parallel())
                .doOnNext(v-> logger.info("value {}", v))
                .doFirst(()->logger.info("first1 "))
                .publishOn(Schedulers.boundedElastic())
                .doFirst(()->logger.info("first2"));



        Runnable runnable1 = () ->flux.subscribe(Utils.subscriber("sub1"));

        Thread.ofPlatform().start(runnable1);


        // blocking main thread
        Utils.sleepSeconds(2);

    }
}
