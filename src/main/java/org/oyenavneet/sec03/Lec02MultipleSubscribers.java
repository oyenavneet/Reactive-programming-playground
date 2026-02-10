package org.oyenavneet.sec03;

import org.oyenavneet.common.Utils;
import reactor.core.publisher.Flux;

public class Lec02MultipleSubscribers {
    public static void main(String[] args) {
        var flux = Flux.just(1,2,3,4,5,6);

        flux.subscribe(Utils.subscriber("sub1"));
        flux
                .filter(i->i>7)
                .subscribe(Utils.subscriber("sub2"));
        flux
                .filter(i->i%2==0)
                .map(i->i+"a")
                .subscribe(Utils.subscriber("sub3"));
    }
}
