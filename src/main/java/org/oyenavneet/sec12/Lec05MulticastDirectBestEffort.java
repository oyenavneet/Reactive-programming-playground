package org.oyenavneet.sec12;

import org.oyenavneet.common.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Sinks;
import reactor.util.concurrent.Queues;

/*
    --Problem:
     -when you have two subscriber and one of the subscriber is slow then it's going to effect performance of other subscriber
    --Solution
        - 1 : increase the buffer size, it will partially fix the issue
        - 2 : directBestEffort() - it basically - focus on fast subscriber and ignore the slow subscriber

 */

import java.time.Duration;

public class Lec05MulticastDirectBestEffort {

    private static final Logger logger = LoggerFactory.getLogger(Lec05MulticastDirectBestEffort.class);

    public static void main(String[] args) {


        demo1();

        Utils.sleepSeconds(50);
    }


    //problem simulation
    private static void demo1(){


        System.setProperty("reactor.bufferSize.small", "100");
        // handle through which we would push items
        // onBackpressureBuffer - bounded Queue
//        var sink = Sinks.many().multicast().onBackpressureBuffer(100); // you can give the buffer size value to store no of message to store
        var sink = Sinks.many().multicast().onBackpressureBuffer();

        //handle through which subscribers will receive items
        var flux = sink.asFlux();


        //when you have two subscriber and one of the subscriber is slow then it's going to effect performance of other subscriber
        flux.subscribe(Utils.subscriber("sw"));
        //making slow the second subscriber for demo
        flux.delayElements(Duration.ofMillis(200)).subscribe(Utils.subscriber("nav"));

        for (int i=1;i<=100;i++){
            var result = sink.tryEmitNext(i);
            logger.info("Emitting item:{} , result: {}",i,result);
        }

    }


    //solution demo - directBestEffort() : focus on fast subscriber and ignore the slow subscriber
    private static void demo2(){


        System.setProperty("reactor.bufferSize.small", "16");
        // handle through which we would push items
        // onBackpressureBuffer - bounded Queue
       //var sink = Sinks.many().multicast().onBackpressureBuffer(100); // you can give the buffer size value to store no of message to store
        var sink = Sinks.many().multicast().directBestEffort();

        //handle through which subscribers will receive items
        var flux = sink.asFlux();


        //when you have two subscriber and one of the subscriber is slow then it's going to effect performance of other subscriber
        flux.subscribe(Utils.subscriber("sw"));
        //making slow the second subscriber for demo

//        flux.delayElements(Duration.ofMillis(200)).subscribe(Utils.subscriber("nav"));

        //onBackpressureBuffer() - inform publisher that the subscriber is slow so add the emitted items in buffer queue so that slow subscribe will pick from there
        // using onBackpressureBuffer() slow subscribe didn't miss any message/items
        flux.onBackpressureBuffer().delayElements(Duration.ofMillis(200)).subscribe(Utils.subscriber("nav"));

        for (int i=1;i<=100;i++){
            var result = sink.tryEmitNext(i);
            logger.info("Emitting item:{} , result: {}",i,result);
        }

    }
}
