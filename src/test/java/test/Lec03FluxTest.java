package test;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class Lec03FluxTest {


    private Flux<Integer> getItems() {
        return Flux.just(1, 2, 3)
                .log();
    }

    @Test
    public void fluxTest1() {
        StepVerifier.create(getItems(),1) // get only 1 item
                .expectNext(1)
                .thenCancel()// thenCancel() otherwise StepVerifier keep waiting to get Complete signal
                .verify();
    }



    // order is important to pass the test
    @Test
    public void fluxTest2() {
        StepVerifier.create(getItems())
                .expectNext(1)
                .expectNext(2)
                .expectNext(3)
                .expectComplete()
                .verify();
    }


    @Test
    public void fluxTest3() {
        StepVerifier.create(getItems())
                .expectNext(1,2,3)
                .expectComplete()
                .verify();
    }

}
