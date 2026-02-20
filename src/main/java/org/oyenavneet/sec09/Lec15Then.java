package org.oyenavneet.sec09;

import org.oyenavneet.common.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

/*
    "then" could be helpful when we are not interested in the result of a publisher, or
     we need to have sequential execution of asynchronous task.

     - if you want to finally result not the in-between item emitted by publisher
     - then() wait for complete/error signal it return

 */

public class Lec15Then {

    public static final Logger logger = LoggerFactory.getLogger(Lec15Then.class);

    public static void main(String[] args) {

        var records = List.of("a", "b", "c", "d", "e", "f");

//        saveRecords(records)
//                .then()
//                .subscribe(Utils.subscriber());


        saveRecords(records)
                .then(sendNotification(records))
                .subscribe(Utils.subscriber());

        Utils.sleepSeconds(5);
    }


    private static Flux<String> saveRecords(List<String> records) {
        return Flux.fromIterable(records)
                .map(r -> "Saved record: " + r)
                .delayElements(Duration.ofMillis(500));
    }

    private static Mono<Void> sendNotification(List<String> records) {
        return Mono.fromRunnable(() -> logger.info("Records {} saved successfully", records));
    }

}
