package org.oyenavneet.sec03.assignment;

import org.oyenavneet.common.Utils;
import org.oyenavneet.sec03.client.ExternalServicesClient;

import java.util.logging.Logger;

public class Assignment03 {
    private static final Logger logger = Logger.getLogger(Assignment03.class.getName());

    public static void main(String[] args) {
        var client = new ExternalServicesClient();

        var subscriber = new StockPriceObserver();
        client.getPriceChanges()
                .subscribe(subscriber);

        Utils.sleepSeconds(20);

    }


}
