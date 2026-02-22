package org.oyenavneet.sec12;

import org.oyenavneet.common.Utils;
import reactor.core.publisher.Sinks;

import java.time.Duration;


/*

        When you want you late subscribe to see teh previous emitted messages
         - replay().all() - store all the message in unbounded Queue so that it can give all the message to late subscribers
         - replay().limit(1) - if you want to only limited message to view by late subscribe ((limit should be numbed of message or Duration of time))
 */
public class Lec07Replay {

    public static void main(String[] args) {
        demo1();
    }


    private static void demo1(){

        // handle through which we would push items
//        var sink = Sinks.many().replay().all();


        // if you want to only limited message to view by late subscribe (limit should be number of message or Duration of time)
//        var sink = Sinks.many().replay().limit(1);
        // you can user duration
        var sink = Sinks.many().replay().limit(Duration.ofSeconds(5));

        //handle through which subscribers will receive items
        var flux = sink.asFlux();


        flux.subscribe(Utils.subscriber("sw"));
        flux.subscribe(Utils.subscriber("nav"));

        sink.tryEmitNext("hi");
        sink.tryEmitNext("how are you");
        sink.tryEmitNext("?");

        Utils.sleepSeconds(2);

        flux.subscribe(Utils.subscriber("sri"));

        sink.tryEmitNext("new message");
    }
}
