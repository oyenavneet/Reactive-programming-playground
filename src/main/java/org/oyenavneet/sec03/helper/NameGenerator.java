package org.oyenavneet.sec03.helper;

import org.oyenavneet.common.Utils;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class NameGenerator {

    public static List<String> getNamesList(int count){
        return IntStream.rangeClosed(1, count)
                .mapToObj(i->generateName())
                .toList();
    }

    public static Flux<String> getNamesFlux(int count){
        return Flux.range(1, count)
                .map(i->generateName());
    }

    private static String generateName() {
        Utils.sleepSeconds(1);
        return Utils.faker().name().firstName();
    }
}
