package org.oyenavneet.sec05;

import org.oyenavneet.common.Utils;
import reactor.core.publisher.Mono;

import java.time.Duration;

/*
    timeout - will produce timeout error.
        - we can handle as part of onError methods
     there is also an overloaded method to accept publisher
     we can have multiple timeouts. the closest one to the subscriber wil take effect from the publisher

 */

public class Lec09Timeout {

    public static void main(String[] args) {
//        getProductName()
//                .timeout(Duration.ofSeconds(1))
//                .onErrorReturn("fallback")
//                .subscribe(Utils.subscriber());


//        getProductName()
//                .timeout(Duration.ofSeconds(2), fallbackService())
//                .subscribe(Utils.subscriber());



       var mono = getProductName()
                .timeout(Duration.ofSeconds(1), fallbackService());

       //in case of exiting function mono implementation we can use you own custom timeout
       mono
               .timeout(Duration.ofMillis(200))
               .subscribe(Utils.subscriber());

        Utils.sleepSeconds(5);
    }


    private static Mono<String > getProductName() {
        return Mono.fromSupplier(()-> "Service - " + Utils.faker().commerce().productName())
                .delayElement(Duration.ofMillis(1900));
    }

    private static Mono<String > fallbackService() {
        return Mono.fromSupplier(()-> "fallback - " + Utils.faker().commerce().productName())
                .delayElement(Duration.ofMillis(300))
                .doFirst(() -> System.out.println("do first"));
    }
}
