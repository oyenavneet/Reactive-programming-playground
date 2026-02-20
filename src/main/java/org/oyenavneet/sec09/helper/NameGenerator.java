package org.oyenavneet.sec09.helper;

import org.oyenavneet.common.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class NameGenerator {

    public static final Logger logger = LoggerFactory.getLogger(NameGenerator.class);
    // assuming it is some caching for demo
    private final List<String> redis = new ArrayList<>();

    public Flux<String> generateNames() {
        return Flux.generate(sink -> {
            logger.info("Generating names");
            Utils.sleepSeconds(1);
            var name = Utils.faker().name().fullName();
            redis.add(name);
            sink.next(name);
        })
                .startWith(redis)
                .cast(String.class);
    }
}
