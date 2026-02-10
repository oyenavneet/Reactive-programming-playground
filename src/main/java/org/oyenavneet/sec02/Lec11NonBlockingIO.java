package org.oyenavneet.sec02;

import org.oyenavneet.common.Utils;
import org.oyenavneet.sec02.client.ExternalServicesClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
    To demo non-blocking IO
    Ensure that the external service is up and running

    ->In the traditional programing if you want to get 100 product info and assuming each every product request take 1 sec
        ->then for loop take 100 sec complete,
        -> for each for loop will take 1 sec to get response which make block for 1 sec
        -> or we need 100 thread to send all the request at the same time
        ->But in below case it only take 1 sec and used only ont thread

     -> Why not to user .block() in reactive it will convert request into sequential request
     -> .block()  can be used for unit testing
 */

public class Lec11NonBlockingIO {
    private static final Logger logger = LoggerFactory.getLogger(Lec11NonBlockingIO.class);

    public static void main(String[] args) {
            var client = new ExternalServicesClient();
            logger.info("Starting");

            for(int i=1; i<=100; i++) {
            client.getProductName(i)
                    .subscribe(Utils.subscriber());



            //.block(); -- using block()
//                var name = client.getProductName(i)
//                        .block();
//                logger.info(name);
            }

            Utils.sleepSeconds(2);
    }
}
