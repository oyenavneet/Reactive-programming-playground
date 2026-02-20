package org.oyenavneet.sec09;

import org.oyenavneet.common.Utils;
import org.oyenavneet.sec09.helper.NameGenerator;

public class Lec02StartWithUseCase {

    public static void main(String[] args) {
        var nameGenerator = new NameGenerator();

        nameGenerator.generateNames()
                .take(2)
                .subscribe(Utils.subscriber("sw"));

        nameGenerator.generateNames()
                .take(2)
                .subscribe(Utils.subscriber("ns"));

        nameGenerator.generateNames()
                .take(3)
                .subscribe(Utils.subscriber("sri"));
    }
}
