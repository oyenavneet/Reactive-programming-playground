package org.oyenavneet.sec09.helper;

import org.oyenavneet.common.Utils;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Kayak {


    public static Flux<Flight> getFlights() {
        return Flux.merge(
                AmericanAirline.getFlights(),
                Emirates.getFlights(),
                Qatar.getFlights()
        )
                .take(Duration.ofSeconds(2));

    }

}
