package org.oyenavneet.sec07;

import org.oyenavneet.common.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;


/*
    parallel(3) - user to execute the items in parallel - 3 is for 3 parallel item.task at a time
    runOn(Schedulers.parallel()) -  without runOn parallel processing didn't work we have to specify  the Schedulers type
    sequential() -  if you want rest of task process normally there user sequential()

    often time you really do not need this!
    - prefer non-blocking IO for network calls

    --parallel(), runOn() can be used in case of CPU intensive task or if you have multiple core of CPU and want to utilize it.
 */
public class Lec08Parallel {

    public static final Logger logger = LoggerFactory.getLogger(Lec08Parallel.class);

    public static void main(String[] args) {

        Flux.range(1, 10)
                .parallel(3)
                .runOn(Schedulers.parallel())
                .map(Lec08Parallel::process)
                //.sequential()
                .map(i-> i + "a")
                .subscribe(Utils.subscriber());

        Utils.sleepSeconds(30);

    }


    //some time-consuming task
    private static int process(int i){
        logger.info("time consuming task {}", i);
        Utils.sleepSeconds(1);
        return i*2;

    }
}
