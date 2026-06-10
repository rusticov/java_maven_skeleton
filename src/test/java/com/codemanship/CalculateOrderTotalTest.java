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
}
