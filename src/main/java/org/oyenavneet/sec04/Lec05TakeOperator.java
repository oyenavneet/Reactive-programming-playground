package org.oyenavneet.sec04;

import org.oyenavneet.common.Utils;
import reactor.core.publisher.Flux;

import java.util.stream.IntStream;

/*
    ->Take is similar to IntStream's limit
    ->Take is Processor range is acting like a producer and limit is acting like subscriber
 */
public class Lec05TakeOperator {

    public static void main(String[] args) {


//        IntStream.rangeClosed(1,10)
//                .limit(3)
//                .forEach(System.out::println);


//        Flux.range(1, 10)
//                .log("take")
//                .take(50)
//                .log("subscriber")
//                .subscribe(Utils.subscriber());


//        takeWhile();
        takeUntil();
    }


    private static void take(){
        Flux.range(1, 10)
                .log("take")
                .take(3)
                .log("subscriber")
                .subscribe(Utils.subscriber());
    }


    private static void takeWhile(){
        Flux.range(1, 10)
                .log("take")
                .takeWhile(i-> i<5) // stop when condition is not met
                .log("subscriber")
                .subscribe(Utils.subscriber());
    }

    private static void takeUntil(){
        Flux.range(1, 10)
                .log("take")
                .takeUntil(i-> i<5) // stop when condition is met + allow the last item which satisfied the condition
                .log("subscriber")
                .subscribe(Utils.subscriber());
    }
}
