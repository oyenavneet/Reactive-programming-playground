package org.oyenavneet.sec08;

import org.oyenavneet.common.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/*
    Reactor automatically handle the backpressure.
    we can also adjust the limit
    We can use .limitRate(5) when subscribing to a publisher to tell the producer that I'm a slow subscriber and my limitRate is x.

 */

public class Lec02LimitRate {

    public static final Logger logger = LoggerFactory.getLogger(Lec02LimitRate.class);

    public static void main(String[] args) {


        var producer = Flux.generate(
                        () -> 1,
                        (state, sink) -> {
                            logger.info("generating {}", state);
                            sink.next(state);
                            return ++state;
                        }
                )
                .cast(Integer.class)
                .subscribeOn(Schedulers.parallel());

        producer
                .limitRate(5)
                .publishOn(Schedulers.boundedElastic())
                .map(Lec02LimitRate::timeConsumingTask)
                .subscribe(Utils.subscriber());

        Utils.sleepSeconds(60);

    }

    private static int timeConsumingTask(int i) {
        Utils.sleepSeconds(1);
        return i;
    }
}
