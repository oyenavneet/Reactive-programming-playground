package org.oyenavneet.sec12.assignment;

public record SlackMessage(
        String sender,
        String message
) {

    //Sender -> Receiver : Message
    private static final String MESSAGE_FORMATE = "[%s -> %s] : %s";

    public String formatForDelivery(String receiver) {
        return MESSAGE_FORMATE.formatted(sender, receiver, message);
    }

}
