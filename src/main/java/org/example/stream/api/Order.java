package org.example.stream.api;

import java.math.BigDecimal;

public class Order {
    public final BigDecimal amount;

    public Order(BigDecimal amount) {
        this.amount = amount;
    }
}
