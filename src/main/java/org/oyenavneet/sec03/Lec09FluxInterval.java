package org.oyenavneet.sec03;

import org.oyenavneet.common.Utils;
import reactor.core.publisher.Flux;

import java.time.Duration;


/*
        If you have requirement of something like do something every second of 500 ms
        like emit a message very few ms to do so we have interval() in flux
     */
public class Lec09FluxInterval {
    public static void main(String[] args) {


        Flux.interval(Duration.ofMillis(500))
                .map(i-> Utils.faker.name().firstName())
                .subscribe(Utils.subscriber());

        Utils.sleepSeconds(6);
    }

}
