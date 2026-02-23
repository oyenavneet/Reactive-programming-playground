package test;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

public class Lec06VirtualTimeTest {

    // this producer take 10 sec to emit one item
    private Flux<Integer> getItems() {
        return Flux.range(1, 5)
                .delayElements(Duration.ofSeconds(10));
    }


    // to test like below we have to wait for 50 sec to test get completed
    @Test
    public void rangeTest() {
        StepVerifier.create(getItems())
                .expectNext(1,2,3,4,5)
                .expectComplete()
                .verify();
    }


    //using withVirtualTime and thenAwait(51) we don't have to wait for 50 sec
    // -  it will simulate 51 sec and test the result
    @Test
    public void virtualTimeTest() {
        StepVerifier.withVirtualTime(this::getItems)
                .thenAwait(Duration.ofSeconds(51)) // escape 51 seconds and then validate the case
                .expectNext(1,2,3,4,5)
                .expectComplete()
                .verify();
    }


    @Test
    public void virtualTimeTest1() {
        StepVerifier.withVirtualTime(this::getItems)
                .expectSubscription() // exception subscription
                .expectNoEvent(Duration.ofSeconds(9)) // no event for first 9 sec
                .thenAwait(Duration.ofSeconds(1))
                .expectNext(1)
                .thenAwait(Duration.ofSeconds(40))
                .expectNext(2,3,4,5)
                .expectComplete()
                .verify();
    }




}
