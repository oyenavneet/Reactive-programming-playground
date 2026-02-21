package org.oyenavneet.sec11;

import org.oyenavneet.common.Utils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

/*

    repeat operator simply resubscribe when it sees complete signal.
    it does not like error signal
    When we add repeat() operator mono become flux, and it emits infinite items!
    we can use repeat(3) with numRepeat
 */
public class Lec01Repeat {

    public static void main(String[] args) {


        // we use repeat() in Flux also
        Flux.just(1,2,3)
                .repeat(3)
                        .subscribe(Utils.subscriber());

//    demo1();
//    demo2();
//    demo5();



    Utils.sleepSeconds(10);

    }

    // repeat on some interval, and we can limit the item using flux.take()
    private static void demo5(){
        var atomicInteger = new AtomicInteger(0);
        getCountryName()
                .repeatWhen(flux->flux.delayElements(Duration.ofSeconds(2)).take(2))
                .subscribe(Utils.subscriber());
    }


    //we can pass BooleanSupplier/ predicate
    private static void demo4(){
        var atomicInteger = new AtomicInteger(0);
        getCountryName()
                .repeat(() -> atomicInteger.incrementAndGet()<3)
                .subscribe(Utils.subscriber());
    }


    // repeat unit it emit India
    private static void demo3(){
        getCountryName()
                .repeat()
                .takeUntil(c->c.equalsIgnoreCase("India"))
                .subscribe(Utils.subscriber());
    }


    private static void demo2(){
        getCountryName()
                .repeat(3)
                .subscribe(Utils.subscriber());
    }

    private static void demo1(){
        getCountryName()
                .repeat()
                .subscribe(Utils.subscriber());
    }

    private static Mono<String> getCountryName() {
        return Mono.fromSupplier(() -> Utils.faker().country().name()); // non-blocking IO
    }
}
