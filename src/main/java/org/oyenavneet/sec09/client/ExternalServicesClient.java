package org.oyenavneet.sec09.client;

import org.oyenavneet.common.AbstractHttpClient;
import org.oyenavneet.sec09.assignment.Product;
import reactor.core.publisher.Mono;

public class ExternalServicesClient extends AbstractHttpClient {

    public Mono<Product> getProduct(int productId) {
        return Mono.zip(
                        getProductName(productId),
                        getProductReview(productId),
                        getProductPrice(productId)
                )
                .map(t -> new Product(t.getT1(), t.getT2(), t.getT3()));
    }


    private Mono<String> getProductName(int id) {
        return get("/demo05/product/" + id);
    }

    public Mono<String> getProductReview(int id) {
        return get("/demo05/review/" + id);
    }

    public Mono<String> getProductPrice(int id) {
        return get("/demo05/price/" + id);
    }

    private Mono<String> get(String path) {
        return this.httpClient.get()
                .uri(path)
                .responseContent()
                .asString()
                .next();
    }
}
