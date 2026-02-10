package org.oyenavneet.sec02;

import org.oyenavneet.common.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;
/*
    ->To delay the publisher creation
    ->In Case when Creating publisher is time-consuming task, and it didn't have yet subscribe
    we can use Mono.defer to delay the Creation of publisher so that creating publisher didn't
    waster resources and time.
    ->Mono.defer only create publisher when there is subscription.

 */


public class Lec10MonoDefer {
    public static final Logger logger = LoggerFactory.getLogger(Lec10MonoDefer.class);

    public static void main(String[] args) {
        Mono.defer(Lec10MonoDefer::createPublisher)
                .subscribe(Utils.subscriber());
    }

    private static Mono<Integer> createPublisher(){
        logger.info("creating Publisher");
        var list = List.of(1,2,3);
        Utils.sleepSeconds(1);
        return Mono.fromSupplier(()->sum(list));
    }

    //time-consuming business logic
    public static int sum(List<Integer> list) {
        logger.info("Find the sum of {}",list);
        Utils.sleepSeconds(3);
        return list.stream().mapToInt(a->a).sum();
    }


}
