package com.codemanship;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddItemTest {

    @Test
    void ifSufficientStockAndAddOneOfProductThenHoldOneOfProduct() {
        Product product = new Product(new Product.Id(327), "Ibanez Tube Screamer", 7, 0);
        Order order = new Order();
        order.addItem(product, 1);
        assertEquals(1, product.onHold());
    }

    @Test
    void ifSufficientStockAndAddTwoOfProductThenHoldTwoOfProduct() {
        Product product = new Product(new Product.Id(327), "Ibanez Tube Screamer", 7, 0);
        Order order = new Order();
        order.addItem(product, 2);
        assertEquals(2, product.onHold());
    }

    @Test
    void ifSufficientStockAddOneOfProductThenOrderHasOneOfProduct() {
        Product product = new Product(new Product.Id(327), "Ibanez Tube Screamer", 7, 0);
        Order order = new Order();
        order.addItem(product, 1);
        assertEquals(1, order.quantityOf(product));
    }

    @Test
    void ifSufficientStockAddTwoOfProductThenOrderHasTwoOfProduct() {
        Product product = new Product(new Product.Id(327), "Ibanez Tube Screamer", 7, 0);
        Order order = new Order();
        order.addItem(product, 2);
        assertEquals(2, order.quantityOf(product));
    }

    @Test
    void insufficientStockWhenAddTwoOfProductThenThrow() {
        Product product = new Product(new Product.Id(327), "Ibanez Tube Screamer", 1, 0);

        Order order = new Order();

        var exception = assertThrows(InsufficientStockException.class, () -> order.addItem(product, 2));
        assertEquals("Insufficient stock of Ibanez Tube Screamer. Only 1 currently available.", exception.getMessage());
    }

    @Test
    void insufficientStockWhenAddTwoOfProductThenOrderContainsNoItems() {
        Product product = new Product(new Product.Id(327), "Ibanez Tube Screamer", 1, 0);

        Order order = new Order();
        assertThrows(InsufficientStockException.class, () -> order.addItem(product, 2), "when adding more than it in stock");

        assertEquals(0, order.quantityOf(product), "confirm product was not added to the order");
    }
}
