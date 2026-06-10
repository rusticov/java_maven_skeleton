package com.codemanship;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyRepresentationTest {

    @Test
    @DisplayName("given Money of 0.00 then toString is 0.00")
    void moneyToString() {
        assertEquals("0.00", Money.parse("0.00").toString());
    }
}
