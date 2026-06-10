package com.codemanship;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculateOrderTotalTest {

    @Test
    @DisplayName("given empty order then total excluding shipping is 0.00")
    void emptyOrder() {
        var inventory = new Inventory();
        var order = new Order(inventory);
        assertEquals(Money.parse("0.00"), order.totalExcludingShipping());
    }

    @Test
    void oneItemOrderThenTotalIsItemPrice() {
        Inventory inventory = new Inventory(
            new Product(new Product.Id(327), "Ibanez Tube Screamer", Money.parse("159.95"), 7, 1)
        );
        Order order = new Order(inventory, new Order.Entry(new Product.Id(327), 1));

        assertEquals(Money.parse("159.95"), order.totalExcludingShipping());
    }
}
