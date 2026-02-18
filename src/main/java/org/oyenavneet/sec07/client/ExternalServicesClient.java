package org.oyenavneet.sec07.client;

import org.oyenavneet.common.AbstractHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;


/*
 adding .publishOn(Schedulers.boundedElastic()) to published it sift the rest of task to oundedElastic thread
  --
 */
public class ExternalServicesClient extends AbstractHttpClient {

    public static final Logger logger = LoggerFactory.getLogger(ExternalServicesClient.class);

    public Mono<String> getProductName(int productId) {
        return this.httpClient.get()
                .uri("/demo01/product/" + productId)
                .responseContent()
                .asString()
                .doOnNext(v -> logger.info("next  {}", v))
                .next()
                .publishOn(Schedulers.boundedElastic());
    }
}
