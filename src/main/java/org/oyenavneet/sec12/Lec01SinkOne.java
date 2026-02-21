package org.oyenavneet.sec12;

import org.oyenavneet.common.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import static org.slf4j.LoggerFactory.getLogger;

public class Lec01SinkOne {

    public static final Logger logger = getLogger(Lec01SinkOne.class);


    public static void main(String[] args) {

        demo3();

    }


    private static void demo1(){
        var sink = Sinks.one();
        var mono = sink.asMono();
        mono.subscribe(Utils.subscriber());
        //sink.tryEmitValue("hi");  // to emit data
        //sink.tryEmitEmpty();     // to emit empty
        sink.tryEmitError(new RuntimeException("error"));           // to emit error
    }



    //we can have multiple subscriber
    private static void demo2(){
        var sink = Sinks.one();
        var mono = sink.asMono();
        sink.tryEmitValue("hi");  // try to emit value but in case of issue it will not notify error
        mono.subscribe(Utils.subscriber("sub1"));
        mono.subscribe(Utils.subscriber("sub2"));

    }



    //emit failure handler - we can not emit after complete
    private static void demo3(){
        var sink = Sinks.one();
        var mono = sink.asMono();

        mono.subscribe(Utils.subscriber("sub1"));

        //emitValue we want to be sure that you or sending and if error occur it notify error
        sink.emitValue("hi", ((signalType, emitResult) -> {
            logger.info("hi");
            logger.info("signalType={}, emitResult={}", signalType.name(), emitResult.name());
            return false;
        }));


        sink.emitValue("hello", ((signalType, emitResult) -> {
            logger.info("hello");
            logger.info("signalType={}, emitResult={}", signalType.name(), emitResult.name());
            return false;  // true to retry
        }));


    }
}
