package org.oyenavneet.sec07;

import org.oyenavneet.common.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/*
    --project reactor support virtual threads
    --by default Virtual Thread is not enabled as per current release in java 21
    --We need to explicitly set system properties to use virtual thread
    System.setProperty("reactor.schedulers.defaultBoundedElasticOnVirtualThreads", "true");

 */

public class Lec04VirtualThreads {

    public static final Logger logger = LoggerFactory.getLogger(Lec04VirtualThreads.class);

    public static void main(String[] args) {
        //setting system properties to use virtual thread
        System.setProperty("reactor.schedulers.defaultBoundedElasticOnVirtualThreads", "true");

        var flux = Flux.create(sink->{
                    for (int i=1; i<3; i++) {
                        logger.info("generating: {} ", i);
                        sink.next(i);
                    }
                    sink.complete();
                })
                .doOnNext(v-> logger.info("value {}", v))
                .doFirst(()->logger.info("first1 - {}", Thread.currentThread().isVirtual()))
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst(()->logger.info("first2"));



        Runnable runnable1 = () ->flux.subscribe(Utils.subscriber("sub1"));

        Thread.ofPlatform().start(runnable1);


        // blocking main thread
        Utils.sleepSeconds(2);

    }
}
