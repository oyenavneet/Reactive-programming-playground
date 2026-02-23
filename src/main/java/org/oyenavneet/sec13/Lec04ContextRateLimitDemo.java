package org.oyenavneet.sec13;

import jdk.jshell.execution.Util;
import org.oyenavneet.common.Utils;
import org.oyenavneet.sec13.client.ExternalServicesClient;
import reactor.util.context.Context;


/*
    Suppose you have a book service
    You want
    - Standard users - allow 2 calls / 5 sec
    - prime users -  allow 3 calls / 5 sec

    Based on user context and rate limiter call attempts to subscribe with user context can same no of time

 */
public class Lec04ContextRateLimitDemo {

    public static void main(String[] args) {
        var client = new ExternalServicesClient();


        for (int i = 0; i < 20; i++) {
            client.getBook()
                    .contextWrite(Context.of("user","nav")) // adding user context
                    .subscribe(Utils.subscriber());
            //blocking thread for 1 sec for each call
            Utils.sleepSeconds(1);
        }


        Utils.sleepSeconds(5);
    }
}
