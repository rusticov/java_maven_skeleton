package com.codemanship;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RemoveItemTest {

    @Test
    void twoProductItemsOnHoldWhenProductRemovedFromOrderThenItemsNotOnHold() {
        Product.Id productId = new Product.Id(327);
        Product product = new Product(productId, "Ibanez Tube Screamer", 7, 2);
        Inventory inventory = new Inventory(product);
        Order order = new Order(inventory, new Order.Entry(productId, 2));

        order.removeProduct(productId);

        assertEquals(0, inventory.getProduct(productId).onHold());
    }

    @Test
    void twoProductItemsOnHoldWhenProductRemovedFromOrderThenProductRemovedFromOrder() {
        Product.Id productId = new Product.Id(327);
        Product product = new Product(productId, "Ibanez Tube Screamer", 7, 2);
        Inventory inventory = new Inventory(product);
        Order order = new Order(inventory, new Order.Entry(productId, 2));

        order.removeProduct(productId);

        assertEquals(0, order.quantityOf(productId));
    }
}
