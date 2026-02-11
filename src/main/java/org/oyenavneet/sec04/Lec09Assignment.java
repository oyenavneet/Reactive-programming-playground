package org.oyenavneet.sec04;

import org.oyenavneet.common.Utils;
import org.oyenavneet.sec04.assignment.FileReaderServiceImpl;

import java.nio.file.Path;

public class Lec09Assignment {

    public static void main(String[] args) {
            var path = Path.of("src/main/resources/sec04/file.txt");
            var fileReaderService = new FileReaderServiceImpl();
            fileReaderService.read(path)
                    .takeUntil(s->s.split(" ")[0].equalsIgnoreCase("when"))
                    .subscribe(Utils.subscriber());
    }
}
