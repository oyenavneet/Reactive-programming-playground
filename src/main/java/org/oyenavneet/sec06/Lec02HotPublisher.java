package org.oyenavneet.sec06;

import org.oyenavneet.common.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

/*
    Flux.share() used to make a Publisher HotPublishe
    Hot - 1 data producer for all the subscribers.
    Share -> publisher().refCount(1)  (share() is alias of publisher().refCount(1))
    It needs 1 minimum subscriber to emit data.
    It stops when there is 0 subscriber


 */

public class Lec02HotPublisher {

    private static final Logger logger = LoggerFactory.getLogger(Lec02HotPublisher.class);

    public static void main(String[] args) {

        // -- (share() is alias of publisher().refCount(1))
        var movieFlux = moviewStream().share();
//        var movieFlux = moviewStream().publish().refCount(1);

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
