package test;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

public class Lec10TimeOutTest {

    private Flux<Integer> getItems() {
        return Flux.range(1, 5)
                .delayElements(Duration.ofMillis(200));
    }


    @Test
    public void timeOutTest() {
        StepVerifier.create(getItems())
                .expectNext(1,2,3,4,5)
                .expectComplete()
                .verify(Duration.ofMillis(1500)); // if you want your test should be completed withing some durations
    }
}
