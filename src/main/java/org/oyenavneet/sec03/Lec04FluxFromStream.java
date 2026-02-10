package org.oyenavneet.sec03;

import org.oyenavneet.common.Utils;
import reactor.core.publisher.Flux;

import java.util.List;

public class Lec04FluxFromStream {
    public static void main(String[] args) {
        var list = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        var stream = list.stream();


//        stream.forEach(System.out::println);
//        stream.forEach(System.out::println);
        var flux = Flux.fromStream(list::stream);
        flux.subscribe(Utils.subscriber("sub1"));
        flux.subscribe(Utils.subscriber("sub2"));


    }
}
