package org.oyenavneet.sec10;

import org.oyenavneet.common.Utils;
import org.oyenavneet.sec10.assignment.buffer.BookOrder;
import org.oyenavneet.sec10.assignment.buffer.RevenueReport;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/*
    Suppose you have stream of Book order
    You need to create Revenue Report based on genre
    for each 5 sec

 */
public class Lec02BufferAssignment {


    public static void main(String[] args) {


        var allowedCategories = Set.of(
                "Science friction",
                "Fantasy",
                "Suspense/Thriller"
        );

        orderStream()
                .filter(o->allowedCategories.contains(o.genre()))
                .buffer(Duration.ofSeconds(5))
                .map(Lec02BufferAssignment::generateReport)
                .subscribe(Utils.subscriber());

        Utils.sleepSeconds(60);
    }


    private static Flux<BookOrder> orderStream(){
        return Flux.interval(Duration.ofMillis(200))
                .map(i -> BookOrder.create());
    }


    private static RevenueReport generateReport(List<BookOrder> orders){
        var revenue = orders.stream()
                .collect(Collectors.groupingBy(
                        BookOrder::genre,
                        Collectors.summingInt(BookOrder::price)
                ));

        return new RevenueReport(LocalTime.now(), revenue);
    }
}
