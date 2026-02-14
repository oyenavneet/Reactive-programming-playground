package org.oyenavneet.sec05;

import org.oyenavneet.common.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class Lec10Transform {

    private static final Logger logger = LoggerFactory.getLogger(Lec10Transform.class);

    record Customer(int id, String name){}
    record PurchaseOrder (String productName, int price, int quantity){}

    public static void main(String[] args) {

//        getCustomers()
//                .doOnNext(i->logger.info("received{}",i))
//                .doOnComplete(()->logger.info("completed"))
//                .doOnError(err -> logger.error("error",err))
//                .subscribe();


//        getPurchaseOrder()
//                .doOnNext(i->logger.info("received{}",i))
//                .doOnComplete(()->logger.info("completed"))
//                .doOnError(err -> logger.error("error",err))
//                .subscribe();

        var isDebugEnabled = true;
        getCustomers()
                .transform(isDebugEnabled? addDebugger():Function.identity())
                .subscribe();

        getPurchaseOrder()
                .transform(addDebugger())
                .subscribe();
    }

    private static Flux<Customer> getCustomers(){
        return Flux.range(1,3)
                .map(i->new Customer(i, Utils.faker().name().firstName()));
    }

    private static Flux<PurchaseOrder> getPurchaseOrder(){
        return Flux.range(1,5)
                .map(i->new PurchaseOrder(Utils.faker().commerce().productName(), i,i*10));
    }


    //re-usable operator
    private static <T> UnaryOperator<Flux<T>> addDebugger(){
        return flux-> flux
                .doOnNext(i->logger.info("received{}",i))
                .doOnComplete(()->logger.info("completed"))
                .doOnError(err -> logger.error("error",err));
    }
}
