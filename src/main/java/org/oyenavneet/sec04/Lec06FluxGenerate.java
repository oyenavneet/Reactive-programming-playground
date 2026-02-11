package org.oyenavneet.sec04;

import org.oyenavneet.common.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

/*
    Flux generate
    ->invokes the given lambda expression again and again based on downstream demand.
    ->Using synchronousSink we are allower to emit only one item
    ->will stop when complete method is invoked
    ->will stop when error method is invoked
    ->will stop downstream cancels
 */

public class Lec06FluxGenerate {
    private static final Logger logger = LoggerFactory.getLogger(Lec06FluxGenerate.class);

    public static void main(String[] args) {


        Flux.generate(synchronousSink -> {
                    logger.info("invoked");
                    synchronousSink.next(1);
//            synchronousSink.next(2);
                    //synchronousSink.complete();

                }).take(4)
                .subscribe(Utils.subscriber());
    }
}
