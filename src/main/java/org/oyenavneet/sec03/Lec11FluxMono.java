package org.oyenavneet.sec03;


import org.oyenavneet.common.Utils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/*
    ->sometimes we need to convert Flux <--> Mono
    ->so to do Mono to FLux we have Flux.from() method to do so
    -> to do Flux to Mono we can use flux.next() as mono can only product 1 so we get the
     next() from flux
     ->Mono also have Mono.from(flux) method to achieve it

 */
public class Lec11FluxMono {

    public static void main(String[] args) {

        var flux = Flux.range(1,10);
//        flux.next()
//                .subscribe(Utils.subscriber());

        Mono.from(flux)
                .subscribe(Utils.subscriber());


    }

    private static void monoToFlux(){
        var mono = getUsername(3);
        save(Flux.from(mono));
    }


    private static Mono<String> getUsername(int userid) {

        return switch (userid){
            case 1 -> Mono.just("oyenavneet");
            case 2 -> Mono.empty(); // similar to null
            default -> Mono.error(new RuntimeException("invalid userid"));
        };
    }

    private static void save(Flux<String> stringFLux){
        stringFLux.subscribe(Utils.subscriber());
    }

}
