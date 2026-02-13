package org.oyenavneet.sec05;


import org.oyenavneet.common.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/*
    How to handle error in reactive pipeline
    Flux.(...)
        ...
        ...
        ...

   --> In case of exception we can use fallbackValue to avoid exception
   like:.onErrorReturn(-1)
        .onErrorReturn(IllegalArgumentException.class, -1)
        .onErrorResume(ex -> fallback()) : User where we to call a fallback method
   ->We can use different combination of onError method to handle the error
   -->.onErrorComplete() : use in case you do not want any error , only want either it give response or nothing

   ->.onErrorContinue((ex, obj)->logger.error("->{} error | ex",obj, ex)) : when you to process the response from publisher without blocking in case of error
 */
public class Lec06ErrorHandling {
    private static final Logger logger = LoggerFactory.getLogger(Lec06ErrorHandling.class);

    public static void main(String[] args) {


        Flux.range(1,10)
                .map(i-> i==5?5/0: i) // intentional just for test
                .onErrorContinue((ex, obj)->logger.error("->{} error | ex",obj, ex))
                .subscribe(Utils.subscriber());
    }


    //when you to process the response from publisher without blocking in case of error
    private static void onErrorContinue() {
        Flux.range(1,10)
                .map(i-> i==5?5/0: i) // intentional just for test
                .onErrorContinue((ex, obj)->logger.error("->{} error | ex",obj, ex))
                .subscribe(Utils.subscriber());
    }


    //in case of error, emit complete
    private static void onErrorComplete() {
        Mono.just(5)
                .onErrorComplete()
                .subscribe(Utils.subscriber());
    }

    //when you want to return a hardcoded value/simple computation
    private static void onErrorResume(){
        Mono.just(5)
                .map(i-> i==5?5/0: i) // intentional just for test
                .onErrorResume(ArithmeticException.class, ex -> fallback())
                .onErrorResume( ex -> fallback1())
                .onErrorReturn(-5)
                //.onErrorResume(ex -> fallback())
                .subscribe(Utils.subscriber());
    }

    private static void onErrorReturnMono() {
        Mono.just(5)
                .map(i-> i==5?5/0: i) // intentional just for test
                .onErrorReturn(IllegalArgumentException.class, -1)
                .onErrorReturn(ArithmeticException.class, -2)
                .onErrorReturn(-3)
                .subscribe(Utils.subscriber());

    }

    private static void onErrorReturnFlux() {
        Flux.range(1,10)
                .map(i-> i==5?5/0: i) // intentional just for test
                .onErrorReturn(IllegalArgumentException.class, -1)
                .onErrorReturn(ArithmeticException.class, -2)
                .onErrorReturn(-3)
                .subscribe(Utils.subscriber());

    }


    private static Mono<Integer> fallback(){
        return Mono.fromSupplier(()->Utils.faker().random().nextInt(10,100));
    }

    private static Mono<Integer> fallback1(){
        return Mono.fromSupplier(()->Utils.faker().random().nextInt(100,1000));
    }

}
