package org.oyenavneet.sec10;

import org.oyenavneet.common.Utils;
import org.oyenavneet.sec10.assignment.groupby.OrderProcessingService;
import org.oyenavneet.sec10.assignment.groupby.PurchaseOrder;
import reactor.core.publisher.Flux;

import java.time.Duration;

/*
    You have a stream of PurchaseOrder
    Based on Order Category you have to perform some task
    - Kids orders
        - add 1 free order from every kids order
    - Automotive orders
        - add $100 to the price

 */

public class Lec06GroupByAssignment {

    public static void main(String[] args) {


        orderStream()
                .filter(OrderProcessingService.canProcess())
                .groupBy(PurchaseOrder::category)
                .flatMap(gf -> gf.transform(OrderProcessingService.getProcessor(gf.key())))
                .subscribe(Utils.subscriber());

        Utils.sleepSeconds(60);

    }


    private static Flux<PurchaseOrder> orderStream() {
        return Flux.interval(Duration.ofMillis(100))
                .map(i -> PurchaseOrder.create());

    }
}
