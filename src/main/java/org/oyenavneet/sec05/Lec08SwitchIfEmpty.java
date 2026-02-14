package org.oyenavneet.sec05;


import org.oyenavneet.common.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

/*
    Similar to error handling.
    Handle Empty response from publisher through a callback method
    real word example:
                ->Assume you have postgres + redis cache
                ->You want to get some cache data from redis, but data is not present in redis for that request
                ->So that scenario you can use switchIfEmpty to fetch tah data from db and cache object in redis
                ->in simple word check data from redis and if it's empty the Switch it to sb
 */
public class Lec08SwitchIfEmpty {

    private static final Logger logger = LoggerFactory.getLogger(Lec08SwitchIfEmpty.class);

    public static void main(String[] args) {


        Flux.range(1,10)
                .filter(i->i>10)
                .switchIfEmpty(fallback())
                .subscribe(Utils.subscriber());
    }


    private static Flux<Integer> fallback(){
        return Flux.range(100,3);
    }

}
