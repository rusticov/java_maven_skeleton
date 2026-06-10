package com.codemanship;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CancelOrderTest {

    private final Inventory inventory = new Inventory(
        new Product(new Product.Id(327), "Ibanez Tube Screamer", Money.parse("159.95"), 7, 2),
        new Product(new Product.Id(811), "Fender Deluxe Reverb", Money.parse("159.95"), 2, 1)
    );

    private final Order order = new Order(
        inventory,
        new Order.Entry(new Product.Id(327), 2),
        new Order.Entry(new Product.Id(811), 1)
    );

    @Test
    void givenAnOpenOrderThenOrderStatusIsOpen() {
        assertEquals(Order.Status.Open, order.getStatus());
    }

    @Test
    void givenAnOpenOrderWhenOrderCanceledThenOrderStatusIsCanceled() {
        order.cancel();

        assertEquals(Order.Status.Canceled, order.getStatus());
    }

    @Test
    void givenAnOpenOrderWhenOrderIsCanceledThenHeldItemsAreReleased() {
        order.cancel();

        assertEquals(0, inventory.getProduct(new Product.Id(327)).onHold());
        assertEquals(0, inventory.getProduct(new Product.Id(811)).onHold());
    }
}
