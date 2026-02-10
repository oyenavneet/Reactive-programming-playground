package org.oyenavneet.sec04;

import org.oyenavneet.common.Utils;
import org.oyenavneet.sec04.helper.NameGenerator;
import reactor.core.publisher.Flux;

public class Lec02FluxCreateRefactor {
    public static void main(String[] args) {

        var generator = new NameGenerator();
        var flux = Flux.create(generator);
        flux.subscribe(Utils.subscriber());

        for(int i=0;i<10;i++){
            generator.generate();
        }
    }
}
