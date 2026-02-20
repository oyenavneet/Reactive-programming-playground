package org.oyenavneet.sec09;

import org.oyenavneet.common.Utils;
import org.oyenavneet.sec09.helper.Kayak;

public class Lec06MergeUseCase {

    public static void main(String[] args) {

        Kayak.getFlights()
                .subscribe(Utils.subscriber());

        Utils.sleepSeconds(3);
    }
}
