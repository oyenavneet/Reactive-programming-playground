package org.oyenavneet.sec09;

import org.oyenavneet.common.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;

/*

    Call multiple publishers in a specific order
    -> We can use .startWith(-1,0) to emit some items before data being emitted from publisher
    -> We can use .startWith(List.of(-2,-1,0)) to emit some items using iterable before data being emitted from publisher
    -> We can use another publisher with .startWith(producer2()) to produce data, the producer which is getting pass in startWith() will emit data first
    -> we can use multiple .startWith() precedent will be from subscribe to publisher
 */

public class Lec01StartWith {

    public static final Logger logger = LoggerFactory.getLogger(Lec01StartWith.class);

    public static void main(String[] args) {

//        demo1();
//        demo2();
//        demo3();
//        demo4();
        demo5();

        Utils.sleepSeconds(3);

    }


    private static void demo5(){
        producer1()
                .startWith(0)
                .startWith(producer2())
                .startWith(49,50)
                .subscribe(Utils.subscriber());
    }


    private static void demo4(){
        producer1()
                .startWith(producer2())
                .startWith(1000)
                .subscribe(Utils.subscriber());
    }


    private static void demo3(){
        producer1()
                .startWith(producer2())
                .subscribe(Utils.subscriber());
    }


    private static void demo2(){
        producer1()
                .startWith(List.of(-2,-1,0))
                .subscribe(Utils.subscriber());
    }


    private static void demo1(){
        producer1()
                .startWith(-1,0)
                .subscribe(Utils.subscriber());
    }

    private static Flux<Integer> producer1(){
        return Flux.just(1,2,3)
                .doOnSubscribe(s-> logger.info("Subscribing to producer 1"))
                .delayElements(Duration.ofMillis(10));
    }

    private static Flux<Integer> producer2(){
        return Flux.just(51,52,53)
                .doOnSubscribe(s-> logger.info("Subscribing to producer 2"))
                .delayElements(Duration.ofMillis(10));
    }
}
