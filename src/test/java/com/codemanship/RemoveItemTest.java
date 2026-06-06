package com.codemanship;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RemoveItemTest {

    @Test
    void twoProductItemsOnHoldWhenProductRemovedFromOrderThenItemsNotOnHold() {
        Product product = new Product(new Product.Id(327), "Ibanez Tube Screamer", 7, 2);
        Order order = new Order(new Order.Entry(new Product.Id(327), 2));

        order.removeProduct(product);

        assertEquals(0, product.onHold());
    }

    @Test
    void twoProductItemsOnHoldWhenProductRemovedFromOrderThenProductRemovedFromOrder() {
        Product product = new Product(new Product.Id(327), "Ibanez Tube Screamer", 7, 2);
        Order order = new Order(new Order.Entry(new Product.Id(327), 2));

        order.removeProduct(product);

        assertEquals(0, order.quantityOf(product));
    }
}
