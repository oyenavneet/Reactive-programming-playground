package org.oyenavneet.sec04;

import org.oyenavneet.common.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Lec07FluxGenerateUntil {

    private static final Logger logger = LoggerFactory.getLogger(Lec07FluxGenerateUntil.class);

    public static void main(String[] args) {
//        Flux.generate(synchronousSink -> {
//                    var country = Utils.faker().country().name();
//                    synchronousSink.next(country);
//                    if (country.equalsIgnoreCase("canada")) {
//                        synchronousSink.complete();
//                    }
//
//                }).subscribe(Utils.subscriber());

//        demo1();
        demo2();
    }

    private static void demo1() {
        Flux.generate(synchronousSink -> {
            var country = Utils.faker().country().name();
            synchronousSink.next(country);
            if (country.equalsIgnoreCase("canada")) {
                synchronousSink.complete();
            }

        }).subscribe(Utils.subscriber());
    }


    private static void demo2() {
        Flux.<String>generate(synchronousSink -> {
            var country = Utils.faker().country().name();
            synchronousSink.next(country);
        })
                .takeUntil(i->i.equalsIgnoreCase("india"))
                .subscribe(Utils.subscriber());
    }
}
