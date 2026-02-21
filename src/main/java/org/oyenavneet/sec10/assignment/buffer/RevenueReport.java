package org.oyenavneet.sec10.assignment.buffer;

import java.time.LocalTime;
import java.util.Map;

public record RevenueReport(LocalTime localTime,
                            Map<String, Integer> revenue) {
}
