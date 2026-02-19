package org.oyenavneet.sec08;

import org.oyenavneet.common.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

/*

    Flux.create() didn't have automatic backpressure handling
    Producer start producing and store the items in internal queue
    When all items produced then the subscriber start consuming
    So we need to handle it by ourselves
 */

public class Lec04FluxCreate {

    public static final Logger logger = LoggerFactory.getLogger(Lec04FluxCreate.class);


    public static void main(String[] args) {

        System.setProperty("reactor.bufferSize.small", "16");


        var producer = Flux.create(sink -> {
                    for(int i = 0; i <= 500  && !sink.isCancelled(); i++) {
                        logger.info("generating {}", i);
                        sink.next(i);
                        Utils.sleep(Duration.ofMillis(50));
                    }
                    sink.complete();
                })
                .cast(Integer.class)
                .subscribeOn(Schedulers.parallel());

        producer
                .limitRate(1)
                .publishOn(Schedulers.boundedElastic())
                .map(Lec04FluxCreate::timeConsumingTask)
                .subscribe();

        Utils.sleepSeconds(60);

    }

    private static int timeConsumingTask(int i) {
        logger.info("received: {}", i);
        Utils.sleepSeconds(1);
        return i;
    }

}
