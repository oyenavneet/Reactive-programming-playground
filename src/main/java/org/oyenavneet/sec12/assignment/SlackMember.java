package org.oyenavneet.sec12.assignment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;

public class SlackMember {

    private static final Logger logger = LoggerFactory.getLogger(SlackMember.class);

    private final String name;

    private Consumer<String> messageConsumer;

    public SlackMember(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void says(String message) {
        messageConsumer.accept(message);
    }

    void setMessageConsumer(Consumer<String> messageConsumer) {
        this.messageConsumer = messageConsumer;
    }

    void receiveMessage(String message) {
        logger.info(message);
//        messageConsumer.accept(message);
    }
}
