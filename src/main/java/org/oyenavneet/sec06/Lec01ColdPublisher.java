package org.oyenavneet.sec06;

import org.oyenavneet.common.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicInteger;

/*
    One fluxSink is for one subscriber

 */

public class Lec01ColdPublisher {

    private static final Logger logger = LoggerFactory.getLogger(Lec01ColdPublisher.class);

    public static void main(String[] args) {

        AtomicInteger count = new AtomicInteger(0);
        var flux = Flux.create(fluxSink -> {
            logger.info("invoke");
            for (int i=0;i<3;i++){
                fluxSink.next(count.incrementAndGet());
            }
            fluxSink.complete();
        });


        flux.subscribe(Utils.subscriber("sub1"));
        flux.subscribe(Utils.subscriber("sub2"));

    }


}
