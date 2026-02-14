package org.oyenavneet.sec05;


import org.oyenavneet.common.Utils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

/*
    Similar to error handling.
    Handle empty value from producer
 */
public class Lec07DefaultIfEmpty {

    public static void main(String[] args) {

//        Mono.empty()
//                .defaultIfEmpty("fallback")
//                .subscribe(Utils.subscriber());


        Flux.range(1,10)
                .filter(i->i>11)
                .defaultIfEmpty(50)
                .subscribe(Utils.subscriber());



    }
}
