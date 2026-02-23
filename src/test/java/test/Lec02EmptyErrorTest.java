package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Lec02EmptyErrorTest {




    Mono<String> getUsername(int userid) {

        return switch (userid){
            case 1 -> Mono.just("oyenavneet");
            case 2 -> Mono.empty(); // similar to null
            default -> Mono.error(new RuntimeException("invalid userid"));
        };
    }


    @Test
    public void userTest() {
        StepVerifier.create(getUsername(1))
                .expectNext("oyenavneet")
                .expectComplete()
                .verify(); // similar to subscribe
    }

    @Test
    public void emptyTest() {
        StepVerifier.create(getUsername(2))
                .expectComplete()
                .verify(); // similar to subscribe
    }


    @Test
    public void errorTest1() {
        StepVerifier.create(getUsername(3))
                .expectError()
                .verify(); // similar to subscribe
    }


    @Test
    public void errorTest2() {
        StepVerifier.create(getUsername(3))
                .expectError(RuntimeException.class)
                .verify(); // similar to subscribe
    }


    @Test
    public void errorTest3() {
        StepVerifier.create(getUsername(3))
                .expectErrorMessage("invalid userid")
                .verify(); // similar to subscribe
    }

    @Test
    public void errorTest4() {
        StepVerifier.create(getUsername(3))
                .consumeErrorWith(ex -> {
                    Assertions.assertEquals(RuntimeException.class,ex.getClass());
                    Assertions.assertEquals("invalid userid",ex.getMessage());
                })
                .verify(); // similar to subscribe
    }
}
