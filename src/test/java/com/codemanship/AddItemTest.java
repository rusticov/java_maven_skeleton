package com.codemanship;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddItemTest {

    @Test
    void ifSufficientStockAndAddOneOfProductThenHoldOneOfProduct() {
        Product.Id productId = new Product.Id(327);
        Product product = new Product(productId, "Ibanez Tube Screamer", 7, 0);
        Inventory inventory = new Inventory(product);
        Order order = new Order(inventory);
        order.addItem(productId, 1);
        assertEquals(1, inventory.getProduct(productId).onHold());
    }

    @Test
    void ifSufficientStockAndAddTwoOfProductThenHoldTwoOfProduct() {
        Product.Id productId = new Product.Id(327);
        Product product = new Product(productId, "Ibanez Tube Screamer", 7, 0);
        Inventory inventory = new Inventory(product);
        Order order = new Order(inventory);
        order.addItem(productId, 2);
        assertEquals(2, inventory.getProduct(productId).onHold());
    }

    @Test
    void ifSufficientStockAddOneOfProductThenOrderHasOneOfProduct() {
        Product.Id productId = new Product.Id(327);
        Product product = new Product(productId, "Ibanez Tube Screamer", 7, 0);
        Inventory inventory = new Inventory(product);
        Order order = new Order(inventory);
        order.addItem(productId, 1);
        assertEquals(1, order.quantityOf(productId));
    }

    @Test
    void ifSufficientStockAddTwoOfProductThenOrderHasTwoOfProduct() {
        Product.Id productId = new Product.Id(327);
        Product product = new Product(productId, "Ibanez Tube Screamer", 7, 0);
        Inventory inventory = new Inventory(product);
        Order order = new Order(inventory);
        order.addItem(productId, 2);
        assertEquals(2, order.quantityOf(productId));
    }

    @Test
    void insufficientStockWhenAddTwoOfProductThenThrow() {
        Product.Id productId = new Product.Id(327);
        Product product = new Product(productId, "Ibanez Tube Screamer", 1, 0);

        Inventory inventory = new Inventory(product);
        Order order = new Order(inventory);

        var exception = assertThrows(InsufficientStockException.class, () -> order.addItem(productId, 2));
        assertEquals("Insufficient stock of Ibanez Tube Screamer. Only 1 currently available.", exception.getMessage());
    }

    @Test
    void insufficientStockWhenAddTwoOfProductThenOrderContainsNoItems() {
        Product.Id productId = new Product.Id(327);
        Product product = new Product(productId, "Ibanez Tube Screamer", 1, 0);

        Inventory inventory = new Inventory(product);
        Order order = new Order(inventory);
        assertThrows(InsufficientStockException.class, () -> order.addItem(productId, 2), "when adding more than it in stock");

        assertEquals(0, order.quantityOf(productId), "confirm product was not added to the order");
    }

    @Test
    void insufficientStockWhenAddTwoOfProductThenProductIsNotOnHold() {
        Product.Id productId = new Product.Id(327);
        Product product = new Product(productId, "Ibanez Tube Screamer", 1, 0);

        Inventory inventory = new Inventory(product);
        Order order = new Order(inventory);
        assertThrows(InsufficientStockException.class, () -> order.addItem(productId, 2), "when adding more than it in stock");

        assertEquals(0, inventory.getProduct(productId).onHold(), "confirm product on hold has not changed");
    }

    @Test
    @DisplayName("given product has 2 stock and 1 on hold when I add 2 product then throw InsufficientStockException")
    void insufficientNotOnHoldStockWhenAddProductToOrderThenThrow() {
        Product.Id productId = new Product.Id(327);
        Product product = new Product(productId, "Ibanez Tube Screamer", 2, 1);

        Inventory inventory = new Inventory(product);
        Order order = new Order(inventory);

        var exception = assertThrows(InsufficientStockException.class, () -> order.addItem(productId, 2));
        assertEquals("Insufficient stock of Ibanez Tube Screamer. Only 1 currently available.", exception.getMessage());
    }

    @Test
    @DisplayName("given product has 2 stock and 1 on hold when I add 2 product then items not added to order")
    void insufficientNotOnHoldStockWhenAddProductToOrderThenItemsNotAddedToOrder() {
        Product.Id productId = new Product.Id(327);
        Product product = new Product(productId, "Ibanez Tube Screamer", 2, 1);

        Inventory inventory = new Inventory(product);
        Order order = new Order(inventory);
        assertThrows(InsufficientStockException.class, () -> order.addItem(productId, 2), "when adding more than is available");

        assertEquals(0, order.quantityOf(productId), "confirm product was not added to the order");
    }
}
