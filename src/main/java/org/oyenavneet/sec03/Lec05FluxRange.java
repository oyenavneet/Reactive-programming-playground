package org.oyenavneet.sec03;

import org.oyenavneet.common.Utils;
import reactor.core.publisher.Flux;

public class Lec05FluxRange {
    public static void main(String[] args) {
//        Flux.range(1, 10)
//                .subscribe(Utils.subscriber("sub1"));

        Flux.range(1, 10)
                .map(x->Utils.faker().name().firstName())
                .subscribe(Utils.subscriber("sub1"));


    }
}
