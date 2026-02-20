package org.oyenavneet.sec09.applications;

public record Order(Integer userId,
                    String productName,
                    Integer price) {
}
