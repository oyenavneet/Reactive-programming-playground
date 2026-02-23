package test;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.oyenavneet.common.Utils;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Objects;

/*
    - "assertNext" is a method in StepVerifier
    - assertNext -> consumeNextWith
    - We can also collect all the items and test

 */
public class Lec05AssertNextTest {


    record Book(int id, String author, String title){}

    private Flux<Book> getBooks() {
        return Flux.range(1,3)
                .map(i-> new Book(i, Utils.faker().book().author(), Utils.faker().book().title()));
    }


    // test one by one
    @Test
    public void testAssertNext() {
        StepVerifier.create(getBooks())
                .assertNext(book -> Assertions.assertEquals(1, book.id()))
                .thenConsumeWhile(b-> Objects.nonNull(b.title()))
                .expectComplete()
                .verify();
    }


    @Test
    public void collectAllAndTest() {
        StepVerifier.create(getBooks().collectList())
                .assertNext(list -> Assertions.assertEquals(3, list.size()))
                .expectComplete()
                .verify();
    }

}
