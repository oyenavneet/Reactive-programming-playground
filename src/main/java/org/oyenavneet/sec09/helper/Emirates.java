package org.oyenavneet.sec09.helper;

import jdk.jshell.execution.Util;
import org.oyenavneet.common.Utils;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Emirates {

    public static final String AIRLINE = "Emirates";


    public static Flux<Flight>  getFlights() {
        return Flux.range(1, Utils.faker().random().nextInt(2,10))
                .delayElements(Duration.ofMillis(Utils.faker().random().nextInt(100,1000)))
                .map(i-> new Flight(AIRLINE,Utils.faker().random().nextInt(200,1000)))
                .transform(Utils.fluxLogger(AIRLINE));

    }
}
