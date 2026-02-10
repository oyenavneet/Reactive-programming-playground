package org.oyenavneet.sec02;

import org.oyenavneet.common.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;
/*
    Mono.fromCallable -> is used when the value is not in the memory we still need to get
    it from a method or process, so using fromSupplier/Callable we delay th execution
    -> In other word when you want to delay the compute intensive operation then we can use fromCallable
    -->fromSupplier does not have checked exception,
    ---->i.g it the method you are calling have throws exception there fromSupplier didn't work
    -->fromCallable have throws exception

 */


public class Lec06MonoFromCallable {
    public static final Logger logger = LoggerFactory.getLogger(Lec06MonoFromCallable.class);

    public static void main(String[] args) {
        var list = List.of(1,2,3);
        Mono.fromCallable(()->sum(list))
                .subscribe(Utils.subscriber());
    }

    public static int sum(List<Integer> list) throws Exception {
        logger.info("Find the sum of {}",list);
        return list.stream().mapToInt(a->a).sum();
    }
}
