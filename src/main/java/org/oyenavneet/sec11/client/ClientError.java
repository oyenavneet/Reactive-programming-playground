package org.oyenavneet.sec11.client;

public class ClientError extends RuntimeException {
    public ClientError() {
        super("bad request");
    }
}
