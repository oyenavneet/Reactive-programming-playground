package org.oyenavneet.sec03;

import org.oyenavneet.common.Utils;
import reactor.core.publisher.Flux;

import java.util.List;

public class Lec03FluxFromIterableOrArray {
    public static void main(String[] args) {
        var list = List.of("a", "b", "c");
        Flux.fromIterable(list)
                .subscribe(Utils.subscriber());

        Integer[] arr = {1, 2, 3};
        Flux.fromArray(arr)
                .subscribe(Utils.subscriber("sub1"));


    }
}
