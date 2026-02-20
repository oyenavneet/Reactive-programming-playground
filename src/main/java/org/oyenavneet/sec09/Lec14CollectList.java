package org.oyenavneet.sec09;


import org.oyenavneet.common.Utils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/*
    - To collect the items received vie flux. Assuming we will have finite items!
    - If we want to collect flux items in list, collectList() return Mono<List<?>>
    - in case of error it only returns error

 */
public class Lec14CollectList {
    public static void main(String[] args) {


        Flux.range(1, 10)
                .concatWith(Mono.error(new RuntimeException("error")))
                .collectList()
                .subscribe(Utils.subscriber());

    }
}
