package org.oyenavneet.sec10;

import org.oyenavneet.common.Utils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class Lec03Window {


    public static void main(String[] args) {
        eventStream()
//                .window(5) // it could be items /durations
                .window(Duration.ofMillis(1800))
                .flatMap(Lec03Window::processEvent)
                .subscribe(Utils.subscriber());

        Utils.sleepSeconds(60);

    }

    private static Flux<String> eventStream() {
        return Flux.interval(Duration.ofMillis(500))
//                .take(10)
//                .concatWith(Flux.never()) // Flux.never() never emit any item
                .map(i -> "event-" + (i + 1));

    }


    private static Mono<Void> processEvent(Flux<String> flux) {
        return flux
                .doOnNext(i -> System.out.print("*"))
                .doOnComplete(System.out::println)
                .then();
    }
}
