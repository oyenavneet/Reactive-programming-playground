package org.oyenavneet.sec02;

import org.oyenavneet.common.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;
/*
    if you have a completableFuture already, then we can convert that into a Mono

 */


public class Lec08MonoFromFuture {
    public static final Logger logger = LoggerFactory.getLogger(Lec08MonoFromFuture.class);

    public static void main(String[] args) {
        Mono.fromFuture(Lec08MonoFromFuture::getName)
                .subscribe(Utils.subscriber());
        Utils.sleepSeconds(2);
    }
    private static CompletableFuture<String> getName(){
        return CompletableFuture.supplyAsync(()->{
            logger.info("generating name");
            return Utils.faker().name().fullName();
        });
    }
}
