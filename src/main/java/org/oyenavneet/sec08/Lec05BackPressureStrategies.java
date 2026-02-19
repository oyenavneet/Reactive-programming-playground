package org.oyenavneet.sec08;

import org.oyenavneet.common.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;


/*

    Reactor provide backpressure handling strategies
    - buffer -> .onBackpressureBuffer() : is usefully when you some occasional spike
             -> .onBackpressureBuffer(10) : buffer with size will send error signal beyond the buffer size

    - drop   ->onBackpressureDrop()
             -> It will drop all the extra items which is produced by producer but not requested by subscribers
             -> Suppose we have slow subscriber which take 2 sec to consume one item produced by producer
             -> In that case all the items which is produced which subscribe is processing the item it will be dropped
             -> and if subscriber request for item then it will receive recent new product item from producer

    - latest -> onBackpressureLatest()
             -> suppose subscriber request for 2 items and producer produced 20 item so latest will 2 item to subscriber and drop
             -> remaining item from 2 to 19 and hold the 20th item (latest item) thinking that what if a downstream request in future

    - error -> .onBackpressureError()
            -> it simply observe if subscribe is too slow and producer is emitting lots of items
            -> it monitor and simply send error signal to downstream/subscriber and send canceled to upstream/producer


    - OverflowStrategy -> There is second parameter in Flux.create() and it is OverflowStrategy
      which used when you want one Strategy to handle backpressure for all you subscribers
 */

public class Lec05BackPressureStrategies {

    public static final Logger logger =  LoggerFactory.getLogger(Lec05BackPressureStrategies.class);

    public static void main(String[] args) {

        var producer = Flux.create(sink -> {
                    for(int i = 1; i <= 500  && !sink.isCancelled(); i++) {
                        logger.info("generating {}", i);
                        sink.next(i);
                        Utils.sleep(Duration.ofMillis(50));
                    }
                    sink.complete();
                })
                .cast(Integer.class)
                .subscribeOn(Schedulers.parallel());

        producer
//                .onBackpressureBuffer()
//                .onBackpressureError()
//                .onBackpressureBuffer(10)
//                .onBackpressureDrop()
                .onBackpressureLatest()
                .log()
                .limitRate(1)
                .publishOn(Schedulers.boundedElastic())
                .map(Lec05BackPressureStrategies::timeConsumingTask)
                .subscribe();

        Utils.sleepSeconds(60);

    }

    private static int timeConsumingTask(int i) {
        logger.info("received: {}", i);
        Utils.sleepSeconds(1);
        return i;
    }
}
