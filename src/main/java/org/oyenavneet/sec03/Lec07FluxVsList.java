package org.oyenavneet.sec03;

import org.oyenavneet.common.Utils;
import org.oyenavneet.sec01.subscriber.SubscriberImpl;
import org.oyenavneet.sec03.helper.NameGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

/*
        ->In List method we're blocked for 10 second to get thr NameList
        ->While in Reactive Flux approach while getting the list we are not block
 */
public class Lec07FluxVsList {
    private static final Logger logger = LoggerFactory.getLogger(Lec06Log.class);
    public static void main(String[] args) {

//        var list = NameGenerator.getNamesList(10);
//        System.out.println(list);
        var subscriber =new SubscriberImpl();
        NameGenerator.getNamesFlux(10)
                .subscribe(subscriber);

        subscriber.getSubscription().request(3);
        subscriber.getSubscription().cancel();

    }
}
