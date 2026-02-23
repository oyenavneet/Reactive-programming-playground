package org.oyenavneet.sec13.client;

import reactor.util.context.Context;

import java.util.Map;
import java.util.function.Function;

public class UserService {

    public static final Map<String, String> USER_CATEGORY = Map.of(
            "sw", "standard",
            "nav", "prime",
            "sri", "standard"
    );


    static Function<Context, Context> userCategoryContext(){
        return ctx -> ctx.<String>getOrEmpty("user")
                .filter(USER_CATEGORY::containsKey) // check category exists in ctx
                .map(USER_CATEGORY::get) // getting the category
                .map(category -> ctx.put("category", category)) // adding to ctx
                .orElse(Context.empty()); // if category not exist return empty context
    }
}
