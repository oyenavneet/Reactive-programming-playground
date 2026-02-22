package org.oyenavneet.sec12;

import org.oyenavneet.common.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Sinks;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

/*
        tryEmitNext() is like try to emit item but not guaranty
        Sinks in not thread safe in case of multiple thread trying to access
        EmitFailureHandler to make it Thread safe
        - emitNext(T var1, EmitFailureHandler var2)
 */

public class Lec03SinkThreadSafety {

    private static final Logger logger = LoggerFactory.getLogger(Lec03SinkThreadSafety.class);


    public static void main(String[] args) {
        demo2();


    }


    // to demo that sink is not thread safe
    private static void demo1(){

        // handle through which we would push items
        // onBackpressureBuffer - unbounded Queue
        var sink = Sinks.many().unicast().onBackpressureBuffer();

        //handle through which subscribers will receive items
        var flux = sink.asFlux();

        // arraylist is not thread safe
        // internationally chose for demo purpose
        var list = new ArrayList<>();
        flux.subscribe(list::add);


        // to check thread safety
        for(int i = 0; i < 1000; i++){
            var j=i;
            CompletableFuture.runAsync(()->{
                sink.tryEmitNext(j);
            });
        }

        Utils.sleepSeconds(2);
        logger.info("list size: {}", list.size());

    }



    // making sinks thread safe using emitNext(T var1, EmitFailureHandler var2)
    private static void demo2(){

        // handle through which we would push items
        // onBackpressureBuffer - unbounded Queue
        var sink = Sinks.many().unicast().onBackpressureBuffer();

        //handle through which subscribers will receive items
        var flux = sink.asFlux();


        // arraylist is not thread safe
        // internationally chose for demo purpose
        var list = new ArrayList<>();
        flux.subscribe(list::add);


        // to check thread safety
        for(int i = 0; i < 1000; i++){
            var j=i;
            CompletableFuture.runAsync(()->{
                sink.emitNext(j, ((signalType, emitResult) -> {
                    return Sinks.EmitResult.FAIL_NON_SERIALIZED.equals(emitResult);
                }));
            });
        }


        Utils.sleepSeconds(2);
        logger.info("list size: {}", list.size());

    }
}
