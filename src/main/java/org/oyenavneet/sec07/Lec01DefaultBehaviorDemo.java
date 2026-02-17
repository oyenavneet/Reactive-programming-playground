package org.oyenavneet.sec07;

import org.oyenavneet.common.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;


/*
    By Default (main thread), the current thread is doing all the work
 */
public class Lec01DefaultBehaviorDemo {

    public static final Logger logger = LoggerFactory.getLogger(Lec01DefaultBehaviorDemo.class);

    public static void main(String[] args) {

        var flux = Flux.create(sink->{
                for (int i=1; i<3; i++) {
                    logger.info("generating: {} ", i);
                    sink.next(i);
                }
                sink.complete();
        })
                .doOnNext(v-> logger.info("value {}", v));

        //if you run below code each subscribe have there separate sink, but executed by same main thread
//        flux.subscribe(Utils.subscriber("sub1"));
//        flux.subscribe(Utils.subscriber("sub2"));


        // now using below code we can execute the code using runnable in separate dedicated thread of as per business requirement
        Runnable runnable = () -> flux.subscribe(Utils.subscriber("sub1"));
        Thread.ofPlatform().start(runnable);



    }
}
