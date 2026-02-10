package org.oyenavneet.sec02;

import org.oyenavneet.common.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;
/*
    Emitting empty after some method invocation:
    i.g: When we're requesting something like product and the product is not available
         on that can while sending plain empty we can use Mono.fromRunnable to notify
         business that this product is not available by invoking some method

 */


public class Lec07MonoFromRunnable {
    public static final Logger logger = LoggerFactory.getLogger(Lec07MonoFromRunnable.class);

    public static void main(String[] args) {
        getProductName(2)
                .subscribe(Utils.subscriber());
    }

    private  static Mono<String> getProductName(int productId){
        if(productId==1){
            return Mono.fromSupplier(()->Utils.faker.commerce().productName());
        }
        return Mono.fromRunnable(()->notifyBusiness(productId));
    }

    private static void notifyBusiness(int productId){
        logger.info("notify Business for unavailable product {}",productId);
    }
}
