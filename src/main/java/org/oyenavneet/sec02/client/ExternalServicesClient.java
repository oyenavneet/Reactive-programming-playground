package org.oyenavneet.sec02.client;

import org.oyenavneet.common.AbstractHttpClient;
import reactor.core.publisher.Mono;

public class ExternalServicesClient extends AbstractHttpClient {

    public Mono<String> getProductName(int productId) {
        return this.httpClient.get()
                .uri("/demo01/product/" + productId)
                .responseContent()
                .asString()
                .next();
    }
}
