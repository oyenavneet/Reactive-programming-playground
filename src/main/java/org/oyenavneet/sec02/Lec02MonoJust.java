package org.oyenavneet.sec02;

import org.oyenavneet.sec01.subscriber.SubscriberImpl;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

public class Lec02MonoJust {
    public static void main(String[] args) {

//Some time we have to create a publisher to pass in some method like save(Publisher<T> publisher)
// which only accept Publisher then user can user Mono.just(<T>) to create and pass the publisher in method.


        var mono = Mono.just("oyenavneet");
        var subscriber = new SubscriberImpl();
        mono.subscribe(subscriber);
        subscriber.getSubscription().request(10);
        subscriber.getSubscription().request(10);
        save(Mono.just("oyenavneet"));
    }

    private static void save(Publisher<String> publisher) {

    }
}
