package org.oyenavneet.sec06;

import org.oyenavneet.common.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;


/*

    -publishe().autoConnect(0) will provide new value to the subscriber
    -replay allows us to cache
    -replay(1) -> allow to cache published data from publisher
 */

public class Lec04HotPublisherCache {

    private static final Logger logger = LoggerFactory.getLogger(Lec04HotPublisherCache.class);

    public static void main(String[] args) {

        var stockFLux = stockStream().replay(1).autoConnect(0);


        Utils.sleepSeconds(4);
        logger.info("nav joining");
        stockFLux
                .subscribe(Utils.subscriber("nav"));

        Utils.sleepSeconds(4);

        logger.info("sri joining");
        stockFLux
                .subscribe(Utils.subscriber("sri"));

        Utils.sleepSeconds(15);
    }


    // demo stock price stream
    private static Flux<Integer> stockStream() {
        return Flux.generate(sink -> sink.next(Utils.faker().random().nextInt(10, 100)))
                .delayElements(Duration.ofSeconds(3))
                .doOnNext(price -> logger.info("emitting Price: {}", price))
                .cast(Integer.class);
    }

}
