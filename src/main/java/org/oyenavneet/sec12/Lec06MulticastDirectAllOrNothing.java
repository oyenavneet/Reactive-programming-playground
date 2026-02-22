package org.oyenavneet.sec12;

import org.oyenavneet.common.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Sinks;

import java.time.Duration;


/*

    multicast().directAllOrNothing()  - if one subscriber is slow then don't deliver to anyone
    - it is like deliver to all or no one

 */
public class Lec06MulticastDirectAllOrNothing {

    private static final Logger logger = LoggerFactory.getLogger(Lec06MulticastDirectAllOrNothing.class);



    public static void main(String[] args) {
        demo1();

        Utils.sleepSeconds(10);
    }


    private static void demo1(){


        System.setProperty("reactor.bufferSize.small", "16");
        // handle through which we would push items
        // onBackpressureBuffer - bounded Queue
        //var sink = Sinks.many().multicast().onBackpressureBuffer(100); // you can give the buffer size value to store no of message to store
        var sink = Sinks.many().multicast().directAllOrNothing();

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
}
