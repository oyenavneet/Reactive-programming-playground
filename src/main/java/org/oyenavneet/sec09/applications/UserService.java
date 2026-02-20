package org.oyenavneet.sec09.applications;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

public class UserService {

    public static final Map<String, Integer> usersTable = Map.of(
            "Navneet", 1,
            "Bholu", 2,
            "Babu", 3
    );

    public static Flux<User> getAllUsers() {
        return Flux.fromIterable(usersTable.entrySet())
                .map(e -> new User(e.getValue(), e.getKey()));
    }

    public static Mono<Integer> getUserId(String username) {
        return Mono.fromSupplier(() -> usersTable.get(username));
    }
}
