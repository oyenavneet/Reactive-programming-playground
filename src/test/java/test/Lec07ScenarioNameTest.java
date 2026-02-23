package test;

import org.junit.jupiter.api.Test;
import org.oyenavneet.common.Utils;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;

public class Lec07ScenarioNameTest {

    private Flux<Integer> getItems() {
        return Flux.range(1, 3);
    }


    @Test
    public void scenarioNameTest() {
        var option = StepVerifierOptions.create().scenarioName("1 to 3 test");
        StepVerifier.create(getItems(),option)
                .expectNext(1)
                .as("first item should be 11")
                .expectNext(2,3)
                .as("tehn 2 and 3")
                .expectComplete()
                .verify();
    }

}
