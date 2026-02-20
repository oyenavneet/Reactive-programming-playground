package org.oyenavneet.sec09.helper;

import org.oyenavneet.common.Utils;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class AmericanAirline {

    public static final String AIRLINE = "American Airline";


    public static Flux<Flight>  getFlights() {
        return Flux.range(1, Utils.faker().random().nextInt(5,13))
                .delayElements(Duration.ofMillis(Utils.faker().random().nextInt(200,1200)))
                .map(i-> new Flight(AIRLINE,Utils.faker().random().nextInt(50,100)))
                .transform(Utils.fluxLogger(AIRLINE));

    }
}
