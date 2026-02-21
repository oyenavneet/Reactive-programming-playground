package org.oyenavneet.sec10.assignment.groupby;

import org.oyenavneet.common.Utils;

public record PurchaseOrder(String item,
                            String category,
                            Integer price) {


    public static PurchaseOrder create(){
        var commerce = Utils.faker().commerce();

        return new PurchaseOrder(
                commerce.productName(),
                commerce.department(),
                Utils.faker().random().nextInt(50,500)
        );
    }

}
