package org.oyenavneet.sec09;

import org.oyenavneet.common.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;


/*
    Subscriber subscribes to al the producer at the same time.
    ->Whichever publisher emit first will be consumed by subcscriber
 */
public class Lec05Merge {


    public static final Logger logger = LoggerFactory.getLogger(Lec05Merge.class);

    public static void main(String[] args) {


//        demo1();

        demo2();


        Utils.sleepSeconds(3);
    }


    private static void demo2() {
        producer2()
                .mergeWith(producer1())
                .mergeWith(producer2())
                .take(2)
                .subscribe(Utils.subscriber());
    }

    private static void demo1(){
        Flux.merge(producer1(),producer2(),producer3())
                .take(2)
                .subscribe(Utils.subscriber());
    }



    private static Flux<Integer> producer1(){
        return Flux.just(1,2,3)
                .transform(Utils.fluxLogger("producer 1"))
                .delayElements(Duration.ofMillis(10));
    }

    private static Flux<Integer> producer2(){
        return Flux.just(51,52,53)
                .transform(Utils.fluxLogger("producer 2"))
                .delayElements(Duration.ofMillis(10));
    }

    private static Flux<Integer> producer3(){
        return Flux.just(11,12,13)
                .transform(Utils.fluxLogger("producer 3"))
                .delayElements(Duration.ofMillis(10));
    }

}
