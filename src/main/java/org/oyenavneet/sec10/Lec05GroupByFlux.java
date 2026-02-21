package org.oyenavneet.sec10;

import jdk.jshell.execution.Util;
import org.oyenavneet.common.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.GroupedFlux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class Lec05GroupByFlux {

    public static final Logger logger = LoggerFactory.getLogger(Lec05GroupByFlux.class);


    /*
        .groupBy() group the emitted item into n group based on grouping logic

     */

    public static void main(String[] args) {


        Flux.range(1, 5)
                .delayElements(Duration.ofSeconds(1))
                .map(i -> i * 2)
                .startWith(1)
                .groupBy(i -> i % 2) // 0,1
                .flatMap(Lec05GroupByFlux::processEvent)
                .subscribe(Utils.subscriber());

        Utils.sleepSeconds(60);
    }


    private static Mono<Void> processEvent(GroupedFlux<Integer, Integer> groupedFlux) {
        logger.info("received flux for {}", groupedFlux.key());
        return groupedFlux.doOnNext(i -> logger.info("Key: {}, item {}", groupedFlux.key(), i))
                .doOnComplete(() -> logger.info("completed flux for {}", groupedFlux.key()))
                .then();
    }
}
