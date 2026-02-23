package test;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;
import reactor.util.context.Context;

public class Lec08ContextTest {

    Mono<String> getWelcomeMessage() {
        return Mono.deferContextual(ctx -> {

            if(ctx.hasKey("user")) {
                return Mono.just("Welcome %s".formatted(ctx.<String>get("user")));
            }
            return Mono.error(new Exception("Unauthenticated"));
        });
    }



    // we can provide some context - something like authentication token etc.
    @Test
    public void welcomeMessagTest() {

        var option = StepVerifierOptions.create().withInitialContext(Context.of("user", "nav"));

        StepVerifier.create(getWelcomeMessage(), option)
                .expectNext("Welcome nav")
                .expectComplete()
                .verify();

    }

    @Test
    public void unauthenticatedMessageTest() {

        var option = StepVerifierOptions.create().withInitialContext(Context.empty());

        StepVerifier.create(getWelcomeMessage(), option)
                .expectErrorMessage("Unauthenticated")
                .verify();

    }
}
