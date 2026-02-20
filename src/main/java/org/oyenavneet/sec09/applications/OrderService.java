package org.oyenavneet.sec09.applications;

import org.oyenavneet.common.Utils;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderService {

    public static final Map<Integer, List<Order>> ordersTable = Map.of(
            1, List.of(
                    new Order(1, Utils.faker().commerce().productName(), Utils.faker().random().nextInt(100, 1000)),
                    new Order(1, Utils.faker().commerce().productName(), Utils.faker().random().nextInt(100, 1000))

            ),
            2, List.of(
                    new Order(2, Utils.faker().commerce().productName(), Utils.faker().random().nextInt(100, 1000)),
                    new Order(2, Utils.faker().commerce().productName(), Utils.faker().random().nextInt(100, 1000)),
                    new Order(2, Utils.faker().commerce().productName(), Utils.faker().random().nextInt(100, 1000))

            ),
            3, List.of()
    );


    public static Flux<Order> getUserOrders(Integer userId) {
        return Flux.fromIterable(ordersTable.get(userId))
                .delayElements(Duration.ofMillis(500))
                .transform(Utils.fluxLogger("order for user"+userId));
    }
}
