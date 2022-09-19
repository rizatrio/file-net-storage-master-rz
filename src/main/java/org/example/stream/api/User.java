package org.example.stream.api;

import java.util.List;

public class User {

    public String name;
    public final List<Order> orders;

    public User(String name, List<Order> orders) {
        this.name = name;
        this.orders = orders;
    }

    public String getName() {
        return name;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setName(String name) {
        this.name = name;
    }
}
