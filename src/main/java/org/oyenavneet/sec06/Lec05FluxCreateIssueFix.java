package org.oyenavneet.sec06;

import org.oyenavneet.common.Utils;
import org.oyenavneet.sec04.helper.NameGenerator;
import reactor.core.publisher.Flux;

public class Lec05FluxCreateIssueFix {
    public static void main(String[] args) {
        //use .share() || autoConnect() to make it Hot Publisher, multiple subscriber can consume same source
        var generator = new NameGenerator();
        var flux = Flux.create(generator).share();
        flux.subscribe(Utils.subscriber("sub1"));
        flux.subscribe(Utils.subscriber("sub2"));

        for(int i=0;i<10;i++){
            generator.generate();
        }
    }
}
