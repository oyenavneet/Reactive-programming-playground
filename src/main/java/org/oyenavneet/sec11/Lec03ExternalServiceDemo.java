package org.oyenavneet.sec11;

import org.oyenavneet.common.Utils;
import org.oyenavneet.sec11.client.ExternalServicesClient;
import org.oyenavneet.sec11.client.ServerError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.util.retry.Retry;

import java.time.Duration;

import static org.slf4j.LoggerFactory.getLogger;

public class Lec03ExternalServiceDemo {

    public static final Logger logger = getLogger(Lec03ExternalServiceDemo.class);

    public static void main(String[] args) {

//        repeat();

        retry();

        Utils.sleepSeconds(60);
    }

    private static void repeat(){
        var client = new ExternalServicesClient();
        client.getCountry()
                .repeat()
                .takeUntil(c->c.equalsIgnoreCase("India"))
                .subscribe(Utils.subscriber());
    }


    private static void retry(){
        var client = new ExternalServicesClient();
        client.getProductName(2)
                .retryWhen(retryOnServerError())
                .subscribe(Utils.subscriber());
    }


    private static Retry retryOnServerError(){
        return Retry.fixedDelay(20, Duration.ofSeconds(1))
                .filter(ex-> ServerError.class.equals(ex.getClass()))
                .doBeforeRetry(rs -> logger.info("retrying {}", rs.failure().getMessage()));
    }
}
