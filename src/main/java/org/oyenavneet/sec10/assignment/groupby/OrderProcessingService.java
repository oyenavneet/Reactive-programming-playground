package org.oyenavneet.sec10.assignment.groupby;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class OrderProcessingService {

    public static final Map<String, UnaryOperator<Flux<PurchaseOrder>>>  PROCESSOR_MAP = Map.of(
            "Kids",kidsProcessing(),
            "Automotive", automotiveProcessing()
    );

    //
    private static UnaryOperator<Flux<PurchaseOrder>> automotiveProcessing() {
        return flux -> flux
                .map(p -> new PurchaseOrder(p.item(), p.category(), p.price() + 100));
    }



    //for each order we are creating one publisher and adding startWith(p) original purchase order
    private static UnaryOperator<Flux<PurchaseOrder>> kidsProcessing() {
        return flux -> flux
                .flatMap(p -> getFreeKidsOrder(p).flux().startWith(p));
    }


    //creating publishe for free order which emit free purchase order
    private static Mono<PurchaseOrder> getFreeKidsOrder(PurchaseOrder order){
        return Mono.fromSupplier(() -> new PurchaseOrder(
                order.item() + "-Free",
                order.category(),
                0
        ));
    }


    public static Predicate<PurchaseOrder> canProcess(){
        return po -> PROCESSOR_MAP.containsKey(po.category());
    }

    public static  UnaryOperator<Flux<PurchaseOrder>> getProcessor(String category){
        return PROCESSOR_MAP.get(category);
    }

}
