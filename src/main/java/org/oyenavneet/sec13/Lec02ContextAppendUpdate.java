package org.oyenavneet.sec13;


import org.oyenavneet.common.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

import java.util.Map;

/*
    Context is an immutable map. We can append additional info

 */
public class Lec02ContextAppendUpdate {

    private static final Logger logger = LoggerFactory.getLogger(Lec02ContextAppendUpdate.class);

    public static void main(String[] args) {
        getWelcomeMessage()
                .contextWrite(ctx -> ctx.put("user", ctx.get("user").toString().toUpperCase()))
                .contextWrite(ctx->ctx.delete("c")) // deleting c key from Context
                .contextWrite(ctx -> Context.of("user","mike"))
//                .contextWrite(ctx -> Context.empty()) // overriding to empty Context
                .contextWrite(Context.of("a","b").put("c","d").put("e","f"))
                .contextWrite(Context.of("user","sw"))
                .subscribe(Utils.subscriber());
    }



    private static void demo1(){
        getWelcomeMessage()
                .contextWrite(Context.of("a","b").put("c","d").put("e","f"))
                .contextWrite(Context.of("user","sw"))
                .subscribe(Utils.subscriber());
    }

    private static Mono<String> getWelcomeMessage() {
        return Mono.deferContextual(ctx -> {
            logger.info("{}",ctx);

            if(ctx.hasKey("user")) {
                return Mono.just("Welcome %s".formatted(ctx.<String>get("user")));
            }
            return Mono.error(new Exception("Unauthenticated"));
        });
    }

}
