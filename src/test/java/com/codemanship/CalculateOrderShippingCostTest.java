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

    @Test
    @DisplayName("order total is exactly 100.00 and UK customer then shipping is free")
    void orderTotal100_00AndUKCustomerWhenShippingCalculatedThenShippingCostIsFree() {
        Inventory inventory = new Inventory(
            new Product(new Product.Id(327), "Ibanez Tube Screamer", Money.parse("100.00"), 7, 1)
        );
        Order order = new Order(inventory, new Order.Entry(new Product.Id(327), 1));

        assertEquals(Money.parse("0.00"), order.shippingCost(Country.UNITED_KINGDOM));
    }

    @Test
    @DisplayName("order total is greater than 100.00 and UK customer then shipping is free")
    void orderTotal100_01AndUKCustomerWhenShippingCalculatedThenShippingCostIsFree() {
        Inventory inventory = new Inventory(
            new Product(new Product.Id(327), "Ibanez Tube Screamer", Money.parse("100.01"), 7, 1)
        );
        Order order = new Order(inventory, new Order.Entry(new Product.Id(327), 1));

        assertEquals(Money.parse("0.00"), order.shippingCost(Country.UNITED_KINGDOM));
    }

    @Test
    @DisplayName("order total under 100 and German (EU) customer then shipping cost is 9.99")
    void orderTotalUnder100AndGermanCustomerWhenShippingCalculatedThenShippingCostIs9_99() {
        Inventory inventory = new Inventory(
            new Product(new Product.Id(327), "Ibanez Tube Screamer", Money.parse("99.99"), 7, 1)
        );
        Order order = new Order(inventory, new Order.Entry(new Product.Id(327), 1));

        assertEquals(Money.parse("9.99"), order.shippingCost(Country.GERMANY));
    }

    @Test
    @DisplayName("order total at least 100.00 and French (EU) customer then shipping cost is 5.99")
    void orderTotalAtLeast100AndFrenchCustomerWhenShippingCalculatedThenShippingCostIs5_99() {
        Inventory inventory = new Inventory(
            new Product(new Product.Id(327), "Ibanez Tube Screamer", Money.parse("100.00"), 7, 1)
        );
        Order order = new Order(inventory, new Order.Entry(new Product.Id(327), 1));

        assertEquals(Money.parse("5.99"), order.shippingCost(Country.FRANCE));
    }

    @Test
    @DisplayName("order total under 100 and United States (non-EU) customer then shipping cost is 12.99")
    void orderTotalUnder100AndUnitedStatesCustomerWhenShippingCalculatedThenShippingCostIs12_99() {
        Inventory inventory = new Inventory(
            new Product(new Product.Id(327), "Ibanez Tube Screamer", Money.parse("99.99"), 7, 1)
        );
        Order order = new Order(inventory, new Order.Entry(new Product.Id(327), 1));

        assertEquals(Money.parse("12.99"), order.shippingCost(Country.UNITED_STATES));
    }

    @Test
    @DisplayName("order total at least 100 and Japan (non-EU) customer then shipping cost is 9.99")
    void orderTotalAtLeast100AndJapanCustomerWhenShippingCalculatedThenShippingCostIs9_99() {
        Inventory inventory = new Inventory(
            new Product(new Product.Id(327), "Ibanez Tube Screamer", Money.parse("100.00"), 7, 1)
        );
        Order order = new Order(inventory, new Order.Entry(new Product.Id(327), 1));

        assertEquals(Money.parse("9.99"), order.shippingCost(Country.UNITED_STATES));
    }
}
