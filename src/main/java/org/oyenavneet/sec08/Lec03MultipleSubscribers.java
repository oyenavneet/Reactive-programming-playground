package org.oyenavneet.sec08;

import org.oyenavneet.common.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/*
    Producer speed depend on consumer processing speed
 */

public class Lec03MultipleSubscribers {

    public static final Logger logger = LoggerFactory.getLogger(Lec03MultipleSubscribers.class);

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


        //slow subscriber
        producer
                .limitRate(5)
                .publishOn(Schedulers.boundedElastic())
                .map(Lec03MultipleSubscribers::timeConsumingTask)
                .subscribe(Utils.subscriber("sub1"));


        //fast subscriber
        producer
                .take(100)
                .publishOn(Schedulers.boundedElastic())
                .subscribe(Utils.subscriber("sub2"));

        Utils.sleepSeconds(60);

    }

    private static int timeConsumingTask(int i) {
        Utils.sleepSeconds(1);
        return i;
    }

}
