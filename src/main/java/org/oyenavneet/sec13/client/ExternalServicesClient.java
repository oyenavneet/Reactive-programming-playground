package org.oyenavneet.sec13.client;

import org.oyenavneet.common.AbstractHttpClient;
import org.oyenavneet.sec11.client.ClientError;
import org.oyenavneet.sec11.client.ServerError;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.ByteBufFlux;
import reactor.netty.http.client.HttpClientResponse;

public class ExternalServicesClient extends AbstractHttpClient {

    public Mono<String> getBook() {
        return this.httpClient.get()
                .uri("/demo07/book")
                .responseContent()
                .asString()
                .startWith(RateLimiter.limitCalls()) // when limitCalls return empty then it will call the client else give the error to subscriber
                .contextWrite(UserService.userCategoryContext()) // adding context
                .next();
    }

}
