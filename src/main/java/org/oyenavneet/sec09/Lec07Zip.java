package org.oyenavneet.sec09;


import org.oyenavneet.common.Utils;
import reactor.core.publisher.Flux;

import java.time.Duration;

/*
    Zip
     - We will subscribe to all the producer at the same time
     - all or noting
     - all producer will have to emit an items


 */
public class Lec07Zip {

    record Car(String body, String engine, String tiers){}

    public static void main(String[] args) {

        Flux.zip(getBody(), getEngine(), getTiers())
                .map(t -> new Car(t.getT1(), t.getT2(), t.getT3()))
                .subscribe(Utils.subscriber());

        Utils.sleepSeconds(5);

    }


    private static Flux<String> getBody(){
        return Flux.range(1, 5)
                .map(i-> "body-"+i)
                .delayElements(Duration.ofMillis(100));
    }

    private static Flux<String> getEngine(){
        return Flux.range(1, 3)
                .map(i-> "engine-"+i)
                .delayElements(Duration.ofMillis(200));
    }

    private static Flux<String> getTiers(){
        return Flux.range(1, 10)
                .map(i-> "tier-"+i)
                .delayElements(Duration.ofMillis(75));
    }

    private static Flux<Integer> producer4(){
        return Flux.range(1, 10);
    }


}
