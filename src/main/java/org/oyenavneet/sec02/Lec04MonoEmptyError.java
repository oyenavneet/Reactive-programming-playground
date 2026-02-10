package org.oyenavneet.sec02;


import org.oyenavneet.common.Utils;
import reactor.core.publisher.Mono;

public class Lec04MonoEmptyError {
    public static void main(String[] args) {

        getUsername(3).subscribe(
                s-> System.out.println(s),
                error -> System.out.println("Error: " + error.getMessage())
        );
    }

    private static Mono<String> getUsername(int userid) {

        return switch (userid){
            case 1 -> Mono.just("oyenavneet");
            case 2 -> Mono.empty(); // similar to null
            default -> Mono.error(new RuntimeException("invalid userid"));
        };
    }
}
