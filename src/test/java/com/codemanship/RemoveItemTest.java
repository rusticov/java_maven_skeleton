package com.codemanship;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RemoveItemTest {

    @Test
    void twoProductItemsOnHoldWhenProductRemovedFromOrderThenItemsNotOnHold() {
        Product.Id productId = new Product.Id(327);

        Inventory inventory = new Inventory(
            new Product(productId, "Ibanez Tube Screamer", Money.parse("159.95"), 7, 2)
        );
        Order order = new Order(inventory, new Order.Entry(productId, 2));

        order.removeProduct(productId);

        assertEquals(0, inventory.getProduct(productId).onHold());
    }

    @Test
    void twoProductItemsOnHoldWhenProductRemovedFromOrderThenProductRemovedFromOrder() {
        Product.Id productId = new Product.Id(327);

        Inventory inventory = new Inventory(
            new Product(productId, "Ibanez Tube Screamer", Money.parse("159.95"), 7, 2)
        );
        Order order = new Order(inventory, new Order.Entry(productId, 2));

        order.removeProduct(productId);

        assertEquals(0, order.quantityOf(productId));
    }
}
