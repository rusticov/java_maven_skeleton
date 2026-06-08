package com.codemanship;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConfirmOrderTest {

    private final Inventory inventory = new Inventory(
        new Product(new Product.Id(327), "Ibanez Tube Screamer", 7, 2),
        new Product(new Product.Id(811), "Fender Deluxe Reverb", 2, 1)
    );

    private final Order order = new Order(
        inventory,
        new Order.Entry(new Product.Id(327), 2),
        new Order.Entry(new Product.Id(811), 1)
    );

    @Test
    void openOrderWhenConfirmedThenOrderStatusIsConfirmed() {
        order.confirm();

        assertEquals(Order.Status.Confirmed, order.getStatus());
    }

    @Test
    void openOrderWhenConfirmedThenOrderItemsAreReleasedFromOnHold() {
        order.confirm();

        assertEquals(0, inventory.getProduct(new Product.Id(327)).onHold());
        assertEquals(0, inventory.getProduct(new Product.Id(811)).onHold());
    }
}
