package org.oyenavneet.sec09;


import jdk.jshell.execution.Util;
import org.oyenavneet.common.Utils;
import org.oyenavneet.sec09.applications.Order;
import org.oyenavneet.sec09.applications.OrderService;
import org.oyenavneet.sec09.applications.UserService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/*
    Sequential non-blocking IO Call
    flatMap is used to flatten the inner publisher / to subscriber to the inner publisher
    Mono is supposed to be 1 item - what if the flatMap returns multiple items?

 */
public class Lec10MonoFlatMapMany {

    public static void main(String[] args) {

         /*
            We have username
            Get all user account orders
         */


        // if inner publisher is going to return many items / Flux() there flatMapMany() is being used
        UserService.getUserId("Navneet")
                .flatMapMany(OrderService::getUserOrders)
                .subscribe(Utils.subscriber());

        Utils.sleepSeconds(3);

    }
}
