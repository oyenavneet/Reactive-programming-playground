package org.oyenavneet.sec02;

import org.oyenavneet.common.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;
/*
    Mono.fromSupplier -> is used whn the value is not in the memory we still need to get
    it from a method or process, so using fromSupplier/Callable we delay th execution
    -> In other word when you want to delay the compute intensive operation then we can use fromSupplier
 */


public class Lec05MonoFromSupplier {
    public static final Logger logger = LoggerFactory.getLogger(Lec05MonoFromSupplier.class);

    public static void main(String[] args) {
        var list = List.of(1,2,3);
        Mono.fromSupplier(()->sum(list))
                .subscribe(Utils.subscriber());
    }

    public static int sum(List<Integer> list) {
        logger.info("Find the sum of {}",list);
        return list.stream().mapToInt(a->a).sum();
    }
}
