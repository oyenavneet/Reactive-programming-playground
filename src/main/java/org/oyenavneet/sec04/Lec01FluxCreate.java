package org.oyenavneet.sec04;


import org.oyenavneet.common.Utils;
import reactor.core.publisher.Flux;

/*
    To Create a flux & emit item programmatically
    Using Flux.create and fluxSink we can emit something while meet some condition or business logic
 */
public class Lec01FluxCreate {
    public static void main(String[] args) {
        Flux.create(fluxSink -> {

            //example 1
//                    for (int i = 1; i <= 10; i++) {
//                        fluxSink.next(Utils.faker().company().name());
//                    }
//                    fluxSink.complete();

            //example 2
                String country;
                do{
                    country=Utils.faker().country().name();
                    fluxSink.next(country);
                }while (!country.equalsIgnoreCase("canada"));
                fluxSink.complete();


                })
                .subscribe(Utils.subscriber());
    }


}
