package org.oyenavneet.sec03;

import org.oyenavneet.common.Utils;
import reactor.core.publisher.Flux;

/*
    .log()  processor work as a middle man, when producer produce it go
    through log and when subscriber it also go through log
    log() are used for debuting
 */
public class Lec06Log {
    public static void main(String[] args) {

        Flux.range(1, 5)
                .log("range")
                .map(x->Utils.faker().name().firstName())
                //.log()
                .subscribe(Utils.subscriber("sub1"));


    }
}
