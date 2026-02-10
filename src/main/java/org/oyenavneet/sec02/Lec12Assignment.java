package org.oyenavneet.sec02;

import org.oyenavneet.common.Utils;
import org.oyenavneet.sec02.assignment.FIleServiceImpl;

public class Lec12Assignment {
    public static void main(String[] args) {
        var fileService = new FIleServiceImpl();

        fileService.write("file.txt","Hello World from reactive progrming")
                        .subscribe(Utils.subscriber());

        fileService.read("file.txt")
                .subscribe(Utils.subscriber());

//        fileService.delete("file.txt")
//                .subscribe(Utils.subscriber());
    }
}
