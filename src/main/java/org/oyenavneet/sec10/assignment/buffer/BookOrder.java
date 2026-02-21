package org.oyenavneet.sec10.assignment.buffer;

import org.oyenavneet.common.Utils;

public record BookOrder(String genre,
                        String title,
                        Integer price) {

    public static BookOrder create() {
        var book = Utils.faker().book();
        return new BookOrder(
                book.genre(),
                book.title(),
                Utils.faker().random().nextInt(100,500)
                );
    }
}
