package org.oyenavneet.sec09.helper;

import org.oyenavneet.common.Utils;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Qatar {

    public static final String AIRLINE = "Qatar";


    public static Flux<Flight>  getFlights() {
        return Flux.range(1, Utils.faker().random().nextInt(3,5))
                .delayElements(Duration.ofMillis(Utils.faker().random().nextInt(200,500)))
                .map(i-> new Flight(AIRLINE,Utils.faker().random().nextInt(100,5000)))
                .transform(Utils.fluxLogger(AIRLINE));

    }
}
