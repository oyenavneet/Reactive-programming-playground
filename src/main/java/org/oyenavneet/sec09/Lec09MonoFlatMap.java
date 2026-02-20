package org.oyenavneet.sec09;

import org.oyenavneet.common.Utils;
import org.oyenavneet.sec09.applications.PaymentService;
import org.oyenavneet.sec09.applications.UserService;
import reactor.core.publisher.Mono;

public class Lec09MonoFlatMap {

    /*
        Sequential non-blocking IO Call
        flatMap is used to flatten the inner publisher / to subscriber to the inner publisher

     */

    public static void main(String[] args) {

        /*
            We have username
            Get user account balance
         */

        // if we user .map() if will return Inner MonoSupplier
//        UserService.getUserId("Bholu")
//                .map(userId -> PaymentService.getUserBalance(userId))
//                .subscribe(Utils.subscriber());

        // if we usee .flatMap() -> it will automatically subscribe to the getUserBalance producer
         UserService.getUserId("Navneet")
                .flatMap(PaymentService::getUserBalance)
                 .subscribe(Utils.subscriber());

    }
}
