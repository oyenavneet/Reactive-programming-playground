package org.oyenavneet.sec07;

import org.oyenavneet.common.Utils;
import org.oyenavneet.sec07.client.ExternalServicesClient;

import java.util.logging.Logger;

public class Lec06EventLoopIssueFix {
    public static final Logger logger = Logger.getLogger(Lec06EventLoopIssueFix.class.getName());

    public static void main(String[] args) {
        var client = new ExternalServicesClient();

        for(int i=1; i<=5; i++) {
            client.getProductName(i)
                    .map(Lec06EventLoopIssueFix::process)
                    .subscribe(Utils.subscriber());
        }

        Utils.sleepSeconds(20);
    }


    private static String process(String input) {
        Utils.sleepSeconds(1);
        return input +" - Processed";
    }
}
