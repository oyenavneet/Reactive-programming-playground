package org.oyenavneet.sec08;

import org.oyenavneet.common.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;


/*

    Reactor automatically handles the backpressure.
    System.setProperty("reactor.bufferSize.small", "16");

    *By default, producer handle backpressure it uses Queues to store the produced items, when the Queues are full the producer wait for
    subscribe to consume the items.
    *When subscribe consume 75% of data from Queues then producer re-start producing items
 */

public class Lec01BackPressureHandling {

    public static final Logger logger = LoggerFactory.getLogger(Lec01BackPressureHandling.class);

    public static void main(String[] args) {

        System.setProperty("reactor.bufferSize.small", "16");
        //reactor queues user internally
//        Queues queues ;

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
                .publishOn(Schedulers.boundedElastic())
                .map(Lec01BackPressureHandling::timeConsumingTask)
                .subscribe(Utils.subscriber());

        Utils.sleepSeconds(60);

    }

    private static int timeConsumingTask(int i) {
        Utils.sleepSeconds(1);
        return i;
    }


}
