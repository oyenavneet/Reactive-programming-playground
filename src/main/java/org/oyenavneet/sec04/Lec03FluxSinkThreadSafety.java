package org.oyenavneet.sec04;

import org.oyenavneet.common.Utils;
import org.oyenavneet.sec04.helper.NameGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.ArrayList;

/*
    FluxSink is Thread Safe
 */

public class Lec03FluxSinkThreadSafety {
    private static final Logger logger = LoggerFactory.getLogger(Lec03FluxSinkThreadSafety.class);

    public static void main(String[] args) {
//        demo1();
        demo2();
    }


    //below is example is to demonstrate below implementation is not thread safe
    private static void demo1(){
        var list = new ArrayList<Integer>();

        Runnable runnable = () -> {
            for(int i=0;i<1000;i++){
                list.add(i);
            }
        };

        for (int i=0;i<10;i++){
            //Java <17
            //  new Thread(runnable).start();
            //Java 21+
            Thread.ofPlatform().start(runnable);
        }
        Utils.sleepSeconds(3);

        /*list .size() is expected 10000 but due to thread is not safe it will never be
         10000 it will return random value less than 10000 */
        logger.info("list size: {}",list.size());
    }


    //below example is to demonstrate FluxSink is Thread safe
    //arrayList is not thread safe
    // fluxSink is hread safe, we get all the 10000 item safely into array list
    private static void demo2(){
        var list = new ArrayList<String>();

        var generator = new NameGenerator();
        var flux = Flux.create(generator);
        flux.subscribe(list::add); // when getting name -> adding to list


        Runnable runnable = () -> {
            for(int i=0;i<1000;i++){
                generator.generate();
            }
        };

        for (int i=0;i<10;i++){
            Thread.ofPlatform().start(runnable);
        }
        Utils.sleepSeconds(3);

        /*as fluxsSink is thread safe it will generate 10000 name ans the list size will always be 10000 */
        logger.info("list size: {}",list.size());


    }

}
