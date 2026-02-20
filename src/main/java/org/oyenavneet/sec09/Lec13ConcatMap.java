package org.oyenavneet.sec09;

import org.oyenavneet.common.Utils;
import org.oyenavneet.sec09.client.ExternalServicesClient;
import reactor.core.publisher.Flux;



/*

    ConcatMap give item in sequential order as IO is in sequential order
 */
public class Lec13ConcatMap {

    public static void main(String[] args) {

        var client = new ExternalServicesClient();

        Flux.range(1, 10)
                .concatMap(client::getProduct)
                .subscribe(Utils.subscriber());

        Utils.sleepSeconds(20);

    }
}
