package org.oyenavneet.sec04;

import org.oyenavneet.common.Utils;
import org.oyenavneet.sec01.subscriber.SubscriberImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;


/*
    Flux.create() does not check the downstream by default! It is by design
    If we cancel the subscription immediately after subscribe it still produce the data

    --user cae of fluxSink
    -->It is designed to be used when we have a single subscriber
    -->FluxSink is thread safe. we can share it with multiple threads.
    -->we can keep on emitting data into the sink without worrying about downstream demand
    -->FluxSink will deliver everything to subscriber safely
 */
public class Lec04FluxCreateDownstreamDemand {
    private static final Logger logger = LoggerFactory.getLogger(Lec04FluxCreateDownstreamDemand.class);

    public static void main(String[] args) {
        produceOnDemand();

    }

    private static void produceEarly(){
        var subscriber = new SubscriberImpl();
        Flux.<String>create(fluxSink -> {
            for (int i = 1; i <= 10; i++) {
                var name = Utils.faker().name().fullName();
                logger.info("name: {}",name);
                fluxSink.next(name);
            }
            fluxSink.complete();
        }).subscribe(subscriber);

        Utils.sleepSeconds(2);
        subscriber.getSubscription().request(2);
        Utils.sleepSeconds(2);
        subscriber.getSubscription().request(2);
        Utils.sleepSeconds(2);
        subscriber.getSubscription().cancel();
        subscriber.getSubscription().request(2);
    }

    private static void produceOnDemand(){
        var subscriber = new SubscriberImpl();
        Flux.<String>create(fluxSink -> {

            fluxSink.onRequest(request -> {
                for (int i = 0; i < request && !fluxSink.isCancelled(); i++) {
                    var name = Utils.faker().name().fullName();
                    logger.info("name: {}",name);
                    fluxSink.next(name);
                }
            });

        }).subscribe(subscriber);

        Utils.sleepSeconds(2);
        subscriber.getSubscription().request(2);
        Utils.sleepSeconds(2);
        subscriber.getSubscription().request(2);
        Utils.sleepSeconds(2);
        subscriber.getSubscription().cancel();
        subscriber.getSubscription().request(2);
    }
}
