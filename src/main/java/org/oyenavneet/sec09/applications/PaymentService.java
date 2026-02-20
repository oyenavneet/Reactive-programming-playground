package org.oyenavneet.sec09.applications;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

public class PaymentService {

    public static final Map<Integer, Integer> userBalance = Map.of(
            1, 100,
            2, 1000,
            3, 1200
    );


    public static Mono<Integer> getUserBalance(Integer userId) {
        return Mono.fromSupplier(() -> userBalance.get(userId));
    }
}
