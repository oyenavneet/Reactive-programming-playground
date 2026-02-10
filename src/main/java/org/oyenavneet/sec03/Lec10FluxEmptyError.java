package org.oyenavneet.sec03;

import org.oyenavneet.common.Utils;
import reactor.core.publisher.Flux;


/*
        Flux always not bound to give message if it has it can give on subscriber request
        -> but it can also return empty using empty()

        To delay the response we can use defer()
     */
public class Lec10FluxEmptyError {

    public static void main(String[] args) {


        Flux.empty()
                .subscribe(Utils.subscriber());
        Flux.error(new RuntimeException("oops"))
                .subscribe(Utils.subscriber());


    }
}
