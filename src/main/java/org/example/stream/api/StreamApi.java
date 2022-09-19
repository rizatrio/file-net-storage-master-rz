package org.example.stream.api;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamApi {
    public static void main(String[] args) {
//        List<String> mList = Arrays.asList("aa1", "cc2", "aa2", "bb1");
//        mList.stream()
//                .filter(s -> {
//                    System.out.println("Filter:" + s);
//                    return s.startsWith("a");
//                })
//                .map(s -> {
//                    System.out.println("Map:" + s);
//                    return s.toUpperCase();
//                })
//                .sorted()
//                .forEach(System.out::println);

        User user = new User("Rizat", Arrays.asList(createOrder(100), createOrder(101)));
        User firstUser = new User("Ivan", Arrays.asList(createOrder(1000), createOrder(1010)));
        User secondUser = new User("David", Arrays.asList(createOrder(10)));

        List<User> userList = new ArrayList<>();
        userList.add(user);
        userList.add(firstUser);
        userList.add(secondUser);

        userList.stream()
                .filter(username -> username.getName().length() > 4)
                .peek(username -> {username.setName(username.getName() + " peek");})
                .parallel()
                .collect(Collectors.toMap(
                        User::getName, u -> u.getOrders().size()
                ))
                .forEach((key, value) -> {
                    System.out.println(key + " - " + value);
        });

        System.out.println("-------------");
        List<String> collect = userList.stream()
                .map(User::getName)
                .filter(name -> name.length() > 4)
                .collect(Collectors.toList());

        collect.forEach(System.out::println);

        System.out.println("--------------");

        BigDecimal totalAmount = userList.stream()
                .flatMap(username -> username.getOrders().stream())
                .map(order -> order.amount)
                .reduce(BigDecimal::add)
                .orElse(null);

        System.out.println("Total amount: " + totalAmount);

        long count = userList.stream()
                .flatMap(username -> username.getOrders().stream())
                .map(order -> order.amount)
                .map(BigDecimal::intValue)
                .count();
        System.out.println(count);

        IntStream.range(0,10)
                .forEach(System.out::println);


    }

    public static Order createOrder(long value) {
        return new Order(BigDecimal.valueOf(value));
    }
}
