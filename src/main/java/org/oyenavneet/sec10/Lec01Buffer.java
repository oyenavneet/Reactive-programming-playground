package org.oyenavneet.sec10;

/*

    Collect items based on given internal / size
    -> buffer wait for event to complete, then return flux of list Flux<List<T>>
 */

import org.oyenavneet.common.Utils;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec01Buffer {

    public static void main(String[] args) {

//        demo1();
        demo4();
//          demo3();

        Utils.sleepSeconds(60);
    }

    private static void demo4(){
        eventStream()
                .bufferTimeout(3, Duration.ofSeconds(1)) // collect on every 3 items or max 1 sec
                .subscribe(Utils.subscriber());
    }


    private static void demo3(){
        eventStream()
                .buffer(Duration.ofMillis(500)) // collect every 500 ms
                .subscribe(Utils.subscriber());
    }

    private static void demo2(){
        eventStream()
                .buffer(3) // collect on every 3 items
                .subscribe(Utils.subscriber());
    }

    private static void demo1(){
        eventStream()
                .buffer() // int-max value or the source has to complete
                .subscribe(Utils.subscriber());
    }


    private static Flux<String> eventStream() {
        return Flux.interval(Duration.ofMillis(200))
                .take(10)
                .concatWith(Flux.never()) // Flux.never() never emit any item
                .map(i->"event-" + (i+1));

    }
}
