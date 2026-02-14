package org.oyenavneet.sec05;

import org.oyenavneet.common.Utils;
import org.oyenavneet.sec05.assignment.ExternalServicesClient;

public class Lec11Assignment {
    public static void main(String[] args) {
        var client = new ExternalServicesClient();


        for(int i=0; i<5; i++){
            client.getProductName(i)
                    .subscribe(Utils.subscriber());
        }

        Utils.sleepSeconds(3);
    }
}
