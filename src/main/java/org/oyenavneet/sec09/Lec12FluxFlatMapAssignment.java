package org.oyenavneet.sec09;

import org.oyenavneet.common.Utils;
import org.oyenavneet.sec09.client.ExternalServicesClient;
import reactor.core.publisher.Flux;

public class Lec12FluxFlatMapAssignment {

    public static void main(String[] args) {


        var client = new ExternalServicesClient();

        Flux.range(1, 10)
                .flatMap(client::getProduct,3)
                .subscribe(Utils.subscriber());

        Utils.sleepSeconds(20);
    }
}
