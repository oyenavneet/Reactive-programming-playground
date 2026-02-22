package org.oyenavneet.sec12;

import org.oyenavneet.common.Utils;
import reactor.core.publisher.Sinks;


/*
    In multicast() we can have multiple subscribers
    When there is no subscriber for sink of multicast() and some message is being produce then it goes to bounded queue based om queue size
    - when some subscriber join later than it emit message to first subscriber
    - and other than first subscriber will not see those message
    - any new message of subscribe will be visible by every one


 */
public class Lec04Multicast {


    public static void main(String[] args) {
        demo2();
    }



    private static void demo1(){

        // handle through which we would push items
        // onBackpressureBuffer - bounded Queue
//        var sink = Sinks.many().multicast().onBackpressureBuffer(100); // you can give the buffer size value to store no of message to store
        var sink = Sinks.many().multicast().onBackpressureBuffer();

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


    //warmup behaviour
    private static void demo2(){

        // handle through which we would push items
        // onBackpressureBuffer - bounded Queue
        var sink = Sinks.many().multicast().onBackpressureBuffer();

        //handle through which subscribers will receive items
        var flux = sink.asFlux();

        sink.tryEmitNext("hi");
        sink.tryEmitNext("how are you");
        sink.tryEmitNext("?");

        Utils.sleepSeconds(2);

        flux.subscribe(Utils.subscriber("sw"));
        flux.subscribe(Utils.subscriber("nav"));

        flux.subscribe(Utils.subscriber("sri"));

        sink.tryEmitNext("new message");
    }
}
