package com.codemanship;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculateOrderShippingCostTest {

    @Test
    @DisplayName("order total under 100 and UK customer then shipping cost is 5.99")
    void orderTotalUnder100AndUKCustomerWhenShippingCalculatedThenShippingCostIs5_99() {
        Inventory inventory = new Inventory(
            new Product(new Product.Id(327), "Ibanez Tube Screamer", Money.parse("99.99"), 7, 1)
        );
        Order order = new Order(inventory, new Order.Entry(new Product.Id(327), 1));

        assertEquals(Money.parse("5.99"), order.shippingCost(Country.UNITED_KINGDOM));
    }
}
