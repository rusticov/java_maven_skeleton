package com.codemanship;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddItemTest {

    @Test
    void ifSufficientStockAndAddOneOfProductThenHoldOneOfProduct() {
        Product product = new Product(new Product.Id(327), "Ibanez Tube Screamer", 7, 0);
        Order order = new Order(new Inventory(product));
        order.addItem(product.getId(), 1);
        assertEquals(1, product.onHold());
    }

    @Test
    void ifSufficientStockAndAddTwoOfProductThenHoldTwoOfProduct() {
        Product product = new Product(new Product.Id(327), "Ibanez Tube Screamer", 7, 0);
        Order order = new Order(new Inventory(product));
        order.addItem(product.getId(), 2);
        assertEquals(2, product.onHold());
    }

    @Test
    void ifSufficientStockAddOneOfProductThenOrderHasOneOfProduct() {
        Product product = new Product(new Product.Id(327), "Ibanez Tube Screamer", 7, 0);
        Order order = new Order(new Inventory(product));
        order.addItem(product.getId(), 1);
        assertEquals(1, order.quantityOf(product.getId()));
    }

    @Test
    void ifSufficientStockAddTwoOfProductThenOrderHasTwoOfProduct() {
        Product product = new Product(new Product.Id(327), "Ibanez Tube Screamer", 7, 0);
        Order order = new Order(new Inventory(product));
        order.addItem(product.getId(), 2);
        assertEquals(2, order.quantityOf(product.getId()));
    }

    @Test
    void insufficientStockWhenAddTwoOfProductThenThrow() {
        Product product = new Product(new Product.Id(327), "Ibanez Tube Screamer", 1, 0);

        Order order = new Order(new Inventory(product));

        var exception = assertThrows(InsufficientStockException.class, () -> order.addItem(product.getId(), 2));
        assertEquals("Insufficient stock of Ibanez Tube Screamer. Only 1 currently available.", exception.getMessage());
    }

    @Test
    void insufficientStockWhenAddTwoOfProductThenOrderContainsNoItems() {
        Product product = new Product(new Product.Id(327), "Ibanez Tube Screamer", 1, 0);

        Order order = new Order(new Inventory(product));
        assertThrows(InsufficientStockException.class, () -> order.addItem(product.getId(), 2), "when adding more than it in stock");

        assertEquals(0, order.quantityOf(product.getId()), "confirm product was not added to the order");
    }

    @Test
    void insufficientStockWhenAddTwoOfProductThenProductIsNotOnHold() {
        Product product = new Product(new Product.Id(327), "Ibanez Tube Screamer", 1, 0);

        Order order = new Order(new Inventory(product));
        assertThrows(InsufficientStockException.class, () -> order.addItem(product.getId(), 2), "when adding more than it in stock");

        assertEquals(0, product.onHold(), "confirm product on hold has not changed");
    }

    @Test
    @DisplayName("given product has 2 stock and 1 on hold when I add 2 product then throw InsufficientStockException")
    void insufficientNotOnHoldStockWhenAddProductToOrderThenThrow() {
        Product product = new Product(new Product.Id(327), "Ibanez Tube Screamer", 2, 1);

        Order order = new Order(new Inventory(product));

        var exception = assertThrows(InsufficientStockException.class, () -> order.addItem(product.getId(), 2));
        assertEquals("Insufficient stock of Ibanez Tube Screamer. Only 1 currently available.", exception.getMessage());
    }

    @Test
    @DisplayName("given product has 2 stock and 1 on hold when I add 2 product then items not added to order")
    void insufficientNotOnHoldStockWhenAddProductToOrderThenItemsNotAddedToOrder() {
        Product product = new Product(new Product.Id(327), "Ibanez Tube Screamer", 2, 1);

        Order order = new Order(new Inventory(product));
        assertThrows(InsufficientStockException.class, () -> order.addItem(product.getId(), 2), "when adding more than is available");

        assertEquals(0, order.quantityOf(product.getId()), "confirm product was not added to the order");
    }
}
