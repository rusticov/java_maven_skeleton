package com.codemanship;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CancelItemTest {

    @Test
    void givenAnOpenOrderThenOrderStatusIsOpen() {
        Inventory inventory = new Inventory(
            new Product(new Product.Id(327), "Ibanez Tube Screamer", 7, 2),
            new Product(new Product.Id(811), "Fender Deluxe Reverb", 2, 1)
        );

        Order order = new Order(
            inventory,
            new Order.Entry(new Product.Id(327), 2),
            new Order.Entry(new Product.Id(811), 1)
        );

        assertEquals(Order.Status.Open, order.getStatus());
    }
}
