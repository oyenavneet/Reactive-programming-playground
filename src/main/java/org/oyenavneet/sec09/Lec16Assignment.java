package org.oyenavneet.sec09;

/*

    Get all users and build 1 object as shown below
    record UserInformation(Integer userId, String username, Integer balance, List<Order> order){}
 */

import org.oyenavneet.common.Utils;
import org.oyenavneet.sec09.applications.*;
import reactor.core.publisher.Mono;

import java.util.List;

public class Lec16Assignment {

    record UserInformation(Integer userId, String username, Integer balance, List<Order> orders){}

    public static void main(String[] args) {

        UserService.getAllUsers()
                .flatMap(Lec16Assignment::getUserInformation)
                .subscribe(Utils.subscriber());

        Utils.sleepSeconds(5);
    }


    private static Mono<UserInformation> getUserInformation(User user){
        return Mono.zip(
                PaymentService.getUserBalance(user.id()),
                OrderService.getUserOrders(user.id()).collectList()
        )
                .map(t -> new UserInformation(user.id(), user.username(), t.getT1(), t.getT2()));
    }

}
