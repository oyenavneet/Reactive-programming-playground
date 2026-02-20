package org.oyenavneet.sec09;

/*
    Sequential non-blocking IO Call
    flatMap is used to flatten the inner publisher / to subscriber to the inner publisher
 */

import jdk.jshell.execution.Util;
import org.oyenavneet.common.Utils;
import org.oyenavneet.sec09.applications.Order;
import org.oyenavneet.sec09.applications.OrderService;
import org.oyenavneet.sec09.applications.User;
import org.oyenavneet.sec09.applications.UserService;
import reactor.core.publisher.Flux;

public class Lec11FluxFlatMap {

    public static void main(String[] args) {

        /*
                Get all the orders from order service
         */


        UserService.getAllUsers()
                .map(User::id)
                .flatMap(OrderService::getUserOrders)
                .subscribe(Utils.subscriber());


        Utils.sleepSeconds(4);
    }
}
