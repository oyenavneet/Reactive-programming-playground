package org.oyenavneet.sec02.assignment;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class FIleServiceImpl implements FileService {

    private static final Logger logger = LoggerFactory.getLogger(FIleServiceImpl.class);
    private static  final Path PATH = Path.of("./src/main/resources/sec02");


    @Override
    public Mono<String> read(String fileName) {
        return Mono.fromCallable(()->Files.readString(PATH.resolve(fileName)));
    }

    @Override
    public Mono<Void> write(String fileName, String content) {
        return Mono.fromRunnable(()->this.writeFle(fileName, content));
    }

    @Override
    public Mono<Void> delete(String fileName) {
        return Mono.fromRunnable(()->this.deleteFle(fileName));
    }

    private void writeFle(String fileName, String content) {
        try {
            Files.writeString(PATH.resolve(fileName), content);
            logger.info("File {} written", fileName);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteFle(String fileName) {
        try {
            Files.delete(PATH.resolve(fileName));
            logger.info(fileName + " deleted");
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
