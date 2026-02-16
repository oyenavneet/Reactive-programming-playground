package org.oyenavneet.sec06;

import org.oyenavneet.common.Utils;
import org.oyenavneet.sec06.assignment.ExternalServicesClient;
import org.oyenavneet.sec06.assignment.InventoryService;
import org.oyenavneet.sec06.assignment.RevenueService;

public class Lec06Assignment {

    public static void main(String[] args) {


        var client = new ExternalServicesClient();
        var inventoryService = new InventoryService();
        var revenueService = new RevenueService();


        client.orderStream().subscribe(inventoryService::consume);
        client.orderStream().subscribe(revenueService::consume);

        inventoryService.stream()
                .subscribe(Utils.subscriber("inventory"));

        revenueService.stream()
                .subscribe(Utils.subscriber("revenue"));

        Utils.sleepSeconds(30);
    }
}
