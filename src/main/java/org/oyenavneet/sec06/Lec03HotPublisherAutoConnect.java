package org.oyenavneet.sec06;

import org.oyenavneet.common.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

/*
    almost same as publish().refCount(1)
    - does NOT stop when subscriber cancel. so it will start producing even for 0 subscriber one it started.
    - to make it real hot publisher - publish().autoConnect(0)
 */

public class Lec03HotPublisherAutoConnect {

    private static final Logger logger = LoggerFactory.getLogger(Lec03HotPublisherAutoConnect.class);

    public static void main(String[] args) {

        var movieFlux = moviewStream().publish().autoConnect(0);


        Utils.sleepSeconds(2);
        //--assume two user is watching
        movieFlux
                .take(4) // user want to leave after 4 scene
                .subscribe(Utils.subscriber("nav"));

        Utils.sleepSeconds(3);
        //--nav1 user join after 2 seconds
        movieFlux
                .take(2) // user want to leave after 2 scene
                .subscribe(Utils.subscriber("nav1"));

        Utils.sleepSeconds(14);
    }


    // demo movie theater
    private static Flux<String> moviewStream() {
        return Flux.generate(
                        () -> {
                            logger.info("received request");
                            return 1;
                        },
                        (state, sink) -> {
                            var scene = "Movie scene" + state;
                            logger.info("playing {}: " + scene);
                            sink.next(scene);
                            return ++state;
                        }
                )
                .take(10)
                .delayElements(Duration.ofSeconds(1))
                .cast(String.class);
    }
}
