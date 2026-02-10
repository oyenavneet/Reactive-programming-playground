package org.oyenavneet.sec03;

import org.oyenavneet.common.Utils;
import org.oyenavneet.sec03.client.ExternalServicesClient;

public class Lec08NonBlockingStreamMessages {

/*
non-blocking IO with stream message
 */

    public static void main(String[] args) {
        var client = new ExternalServicesClient();
        client
                .getNames()
                .subscribe(Utils.subscriber("sub1"));

        client
                .getNames()
                .subscribe(Utils.subscriber("sub2"));
        Utils.sleepSeconds(6);

    }
}
