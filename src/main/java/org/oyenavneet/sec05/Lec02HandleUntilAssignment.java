package org.oyenavneet.sec05;

import org.oyenavneet.common.Utils;
import reactor.core.publisher.Flux;

public class Lec02HandleUntilAssignment {

    public static void main(String[] args) {


        Flux.<String>generate(sink -> sink.next(Utils.faker().country().name()))
                .handle((item, sink)->{
                    sink.next(item);
                    if(item.equalsIgnoreCase("india")){
                        sink.complete();
                    }
                })
                .subscribe(Utils.subscriber());
    }
}
