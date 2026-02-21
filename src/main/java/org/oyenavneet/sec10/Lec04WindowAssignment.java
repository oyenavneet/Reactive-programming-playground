package org.oyenavneet.sec10;

import org.oyenavneet.common.Utils;
import org.oyenavneet.sec10.assignment.window.FileWriter;
import reactor.core.publisher.Flux;

import java.nio.file.Path;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

/*
    Suppose you have a log stream
    Create a log txt file every 1800 ms

 */

public class Lec04WindowAssignment {

    public static void main(String[] args) {

        var counter = new AtomicInteger(0);
        var fileNameFormate = "src/main/resources/sec10/file%d.txt";



        eventStream()
                .window(Duration.ofMillis(1800))
                .flatMap(flux -> FileWriter.create(flux, Path.of(fileNameFormate.formatted(counter.getAndIncrement()))))
                .subscribe(Utils.subscriber());

        Utils.sleepSeconds(60);

    }

    private static Flux<String> eventStream() {
        return Flux.interval(Duration.ofMillis(500))
//                .take(10)
//                .concatWith(Flux.never()) // Flux.never() never emit any item
                .map(i -> "event-" + (i + 1));

    }
}
