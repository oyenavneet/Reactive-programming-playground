package org.oyenavneet.sec09;

import jdk.jshell.execution.Util;
import org.oyenavneet.common.Utils;
import org.oyenavneet.sec09.client.ExternalServicesClient;

public class Lec08ZipAssignment {

    public static void main(String[] args) {


        var client = new ExternalServicesClient();

        for(int i=1; i<=10; i++) {
            client.getProduct(i)
                    .subscribe(Utils.subscriber());
        }

        Utils.sleepSeconds(2);
    }

}
