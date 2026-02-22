package org.oyenavneet.sec12;


/*

    We can emit multiple messages. but there will be only one subscriber.
 */

import org.oyenavneet.common.Utils;
import reactor.core.publisher.Sinks;

public class Lec02SinkUnicast {


    public static void main(String[] args) {


    demo2();

    }


    private static void demo1(){

        // handle through which we would push items
        // onBackpressureBuffer - unbounded Queue
        var sink = Sinks.many().unicast().onBackpressureBuffer();

        //handle through which subscribers will receive items
        var flux = sink.asFlux();


        sink.tryEmitNext("hi");
        sink.tryEmitNext("how are you");
        sink.tryEmitNext("?");

        flux.subscribe(Utils.subscriber("sw"));
    }



    //in case of two subscribers unicast() it will give error for second subscriber:(Sinks.many().unicast() sinks only allow a single Subscriber)
    private static void demo2(){

        // handle through which we would push items
        var sink = Sinks.many().unicast().onBackpressureBuffer();

        //handle through which subscribers will receive items
        var flux = sink.asFlux();


        sink.tryEmitNext("hi");
        sink.tryEmitNext("how are you");
        sink.tryEmitNext("?");

        flux.subscribe(Utils.subscriber("sw"));
        flux.subscribe(Utils.subscriber("nav"));
    }
}
