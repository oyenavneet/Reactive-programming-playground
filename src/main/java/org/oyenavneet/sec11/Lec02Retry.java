package org.oyenavneet.sec11;

import org.oyenavneet.common.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;


/*

    retry operator simple resubscribe when it sees error signal.
    if you don't provide retry count in .retry() then it will retry infinite
    Using retryWhen()
        - able retry n number of time
        - able to pass duration gap b/w retries
        - able to use doBeforeRetry() ,doBeforeRetry(), doAfterRetryAsync(), filter()

 */
public class Lec02Retry {

    public static final Logger logger = LoggerFactory.getLogger(Lec02Retry.class);


    public static void main(String[] args) {

        demo3();

        Utils.sleepSeconds(10);

    }


    // if we want to retry in case of specific exception
    private static void demo3(){
        getCountryName()
                .retryWhen(
                        Retry.fixedDelay(2, Duration.ofSeconds(1))
                                .filter(ex -> RuntimeException.class.equals(ex.getClass()))
                                .onRetryExhaustedThrow((spec, signal) -> signal.failure()) // if you don't want to show retry exhausted exception and want original exception from publisher

                )
                .subscribe(Utils.subscriber());
    }


    //when we want to retry n number of time having some duration gap
    private static void demo2(){
        getCountryName()
                .retryWhen(
                        Retry.fixedDelay(2, Duration.ofSeconds(1))
                                .doBeforeRetry(rs -> logger.info("retrying")) // for logging purpose

                )
                .subscribe(Utils.subscriber());
    }

    private static void demo1(){
        getCountryName()
                .retry(2)
                .subscribe(Utils.subscriber());
    }


    private static Mono<String> getCountryName() {
        var atomicInteger = new AtomicInteger(0);
        return Mono.fromSupplier(() -> {
                    if (atomicInteger.incrementAndGet() < 5) {
                        throw new RuntimeException("error");
                    }
                    return Utils.faker().country().name();
                })
                .doOnError(err -> logger.info("ERROR:{}", err.getMessage()))
                .doOnSubscribe(s -> logger.info("Subscribing"))
                ; // non-blocking IO
    }
}
