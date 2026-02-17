package org.oyenavneet.sec07;

import org.oyenavneet.common.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec02SubscribeOn {

    public static final Logger logger = LoggerFactory.getLogger(Lec02SubscribeOn.class);


    public static void main(String[] args) {

//        var flux = Flux.create(sink->{
//                    for (int i=1; i<3; i++) {
//                        logger.info("generating: {} ", i);
//                        sink.next(i);
//                    }
//                    sink.complete();
//                })
//                .doOnNext(v-> logger.info("value {}", v));

        var flux = Flux.create(sink->{
                    for (int i=1; i<3; i++) {
                        logger.info("generating: {} ", i);
                        sink.next(i);
                    }
                    sink.complete();
                })
                .doOnNext(v-> logger.info("value {}", v))
                .doFirst(()->logger.info("first1"))
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst(()->logger.info("first2"));

        // first2 is executed for main thread ans rest will be handle by boundedElastic thread (subscriber execute from bottom to up)
//        flux
//                .doFirst(()->logger.info("first1"))
//                .subscribeOn(Schedulers.boundedElastic())
//                .doFirst(()->logger.info("first2"))
//                .subscribe(Utils.subscriber());


        // as we created the new thread to execute to subscriber first2 will be executed by thread-0 and rest will be same
//        Runnable runnable = () ->flux
//                .doFirst(()->logger.info("first1"))
//                .subscribeOn(Schedulers.boundedElastic())
//                .doFirst(()->logger.info("first2"))
//                .subscribe(Utils.subscriber());
//
//        Thread.ofPlatform().start(runnable);


        // in below there is 2 different subscribe with their own thread, Schedulers.boundedElastic() provide two different to each subscriber
        Runnable runnable1 = () ->flux.subscribe(Utils.subscriber("sub1"));
        Runnable runnable2 = () ->flux.subscribe(Utils.subscriber("sub2"));

        Thread.ofPlatform().start(runnable1);
        Thread.ofPlatform().start(runnable2);



        // blocking main thread
        Utils.sleepSeconds(2);
    }
}
