package org.oyenavneet.sec04;

import org.oyenavneet.common.Utils;
import reactor.core.publisher.Flux;

public class Lec08GenerateWithState {

    public static void main(String[] args) {


        Flux.generate(
                () -> 0,
                (counter, sink) -> {
                    var country = Utils.faker().country().name();
                    sink.next(country);
                    counter++;
                    if (counter == 10 || country.equalsIgnoreCase("india")) {
                        sink.complete();
                    }
                    return counter;
                }
        ).subscribe(Utils.subscriber());
    }
}
