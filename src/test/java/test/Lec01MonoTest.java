package test;


import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/*
        - Test using StepVerifier
        - StepVerifier act like a subscriber

 */
public class Lec01MonoTest {


    private static final Logger logger = LoggerFactory.getLogger(Lec01MonoTest.class);


    private Mono<String> getProduct(int id) {
        return Mono.fromSupplier(() -> "Product-" + id)
                .doFirst(() -> logger.info("invoked"));
    }


    @Test
    public void productTest() {
        StepVerifier.create(getProduct(1))
                .expectNext("Product-1")
                .expectComplete()
                .verify(); // similar to subscribe
    }

}
