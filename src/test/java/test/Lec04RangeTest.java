package test;

import org.junit.jupiter.api.Test;
import org.oyenavneet.common.Utils;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class Lec04RangeTest {


    private Flux<Integer> getItems() {
        return Flux.range(1, 50);
    }

    private Flux<Integer> getRandomItems() {
        return Flux.range(1, 50)
                .map(i -> Utils.faker().random().nextInt(1,100));
    }

    @Test
    public void rangeTest() {
        StepVerifier.create(getItems())
                .expectNext(1,2,3) // verify first three
                .expectNextCount(47) //but expecting 47 more items
                .expectComplete()// expecting complete signal
                .verify();
    }


    @Test
    public void rangeTest1() {
        StepVerifier.create(getItems())
                .expectNext(1,2,3) // verify first three
                .expectNextCount(22)// ignoring next 22 items after 3
                .expectNext(26, 27,28) // verifying next value , item no 3+22+1
                .expectNextCount(22)// and expecting 22 more items more
                .expectComplete()// expecting complete signal
                .verify();
    }


    // test for random
    @Test
    public void rangeTest2() {
        StepVerifier.create(getRandomItems())
                .expectNextMatches(i -> i >0 && i<101) // test only first/one item
                .expectNextCount(49)
                .expectComplete()// expecting complete signal
                .verify();
    }


    // test for random - thenConsumeWhile() predicate verify all items
    @Test
    public void rangeTest3() {
        StepVerifier.create(getRandomItems())
                .thenConsumeWhile(i -> i >0 && i<101)
                .expectComplete()// expecting complete signal
                .verify();
    }

}
