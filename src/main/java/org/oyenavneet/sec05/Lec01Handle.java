package org.oyenavneet.sec05;


import org.oyenavneet.common.Utils;
import reactor.core.publisher.Flux;

/*
    Handle behave like filter + map

    -> When We add Operator to Flux it create a flux object

    #example requirement
    1-> -2
    4-> do not send
    7-> error
    everything else -> send as it is

    --
 */
public class Lec01Handle {
    public static void main(String[] args) {

//        Flux<Integer> flux = Flux.range(1, 10);
//
//        Flux<Integer> flux1 = flux.handle((item, sink) ->{
//            sink.error(new RuntimeException("error"));
//        });
//
//        flux1.subscribe(Utils.subscriber());


        Flux.range(1, 10)
                .filter(i->i!=7)
                .handle((item, sink) -> {
                    switch (item) {
                        case 1 -> sink.next(-2);
                        case 4 -> {
                        }
                        case 7 -> sink.error(new RuntimeException("error"));
                        default -> sink.next(item);

                    }
                }).subscribe(Utils.subscriber());

    }
}
