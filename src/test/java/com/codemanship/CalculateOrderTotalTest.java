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

    @Test
    @DisplayName("order with one item of two products of prices 159.95 and 1799.00 has totals 1958.95")
    void orderWithTwoProductsOfOneItemThenTotalIsSumOfProductPrices() {
        Inventory inventory = new Inventory(
            new Product(new Product.Id(327), "Ibanez Tube Screamer", Money.parse("159.95"), 7, 1),
            new Product(new Product.Id(811), "Fender Deluxe Reverb", Money.parse("1799.00"), 2, 1)
        );
        Order order = new Order(
            inventory,
            new Order.Entry(new Product.Id(327), 1),
            new Order.Entry(new Product.Id(811), 1)
        );

        assertEquals(Money.parse("1958.95"), order.totalExcludingShipping());
    }

    @Test
    @DisplayName("order with two items of one product of price 159.95 has total excl shipping of 319.90")
    void orderWithTwoItemsOfOneProductThenTotalIsItemPriceTimesTwo() {
        Inventory inventory = new Inventory(
            new Product(new Product.Id(327), "Ibanez Tube Screamer", Money.parse("159.95"), 7, 2)
        );
        Order order = new Order(inventory, new Order.Entry(new Product.Id(327), 2));

        assertEquals(Money.parse("319.90"), order.totalExcludingShipping());
    }
}
