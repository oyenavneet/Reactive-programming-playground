package org.oyenavneet.sec02;

import org.oyenavneet.common.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;
/*
    Creating a publisher is a lightweight operation.
    Executing time-consuming business logic should be delayed

 */


public class Lec09PublisherCreateVsExecution {
    public static final Logger logger = LoggerFactory.getLogger(Lec09PublisherCreateVsExecution.class);

    public static void main(String[] args) {
        getName()
                .subscribe(Utils.subscriber());
    }
    private static Mono<String> getName(){
        logger.info("inside method getName");
        return Mono.fromSupplier(()->{
            logger.info("generating name");
            Utils.sleepSeconds(2);
            return Utils.faker().name().fullName();
        });
    }
}
