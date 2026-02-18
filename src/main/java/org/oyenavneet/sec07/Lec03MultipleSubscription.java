package org.oyenavneet.sec07;

import org.oyenavneet.common.Utils;
import org.slf4j.Logger;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/*
        we can have multiple subscribeOn
        !the closest to the source/producer will take the precedence
        Schedulers.immediate()-> in case if you don't want to change thread them immediate() can be used
 */

public class Lec03MultipleSubscription {
    public static final Logger logger = org.slf4j.LoggerFactory.getLogger(Lec03MultipleSubscription.class);

    public static void main(String[] args) {


        var flux = Flux.create(sink->{
                    for (int i=1; i<3; i++) {
                        logger.info("generating: {} ", i);
                        sink.next(i);
                    }
                    sink.complete();
                })
//                .subscribeOn(Schedulers.immediate())
                .subscribeOn(Schedulers.newParallel("sw"))
                .doOnNext(v-> logger.info("value {}", v))
                .doFirst(()->logger.info("first1"))
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst(()->logger.info("first2"));



        Runnable runnable1 = () ->flux.subscribe(Utils.subscriber("sub1"));

        Thread.ofPlatform().start(runnable1);


        // blocking main thread
        Utils.sleepSeconds(2);
    }


}
