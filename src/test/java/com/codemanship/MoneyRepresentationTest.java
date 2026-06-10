package com.codemanship;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyRepresentationTest {

    @Test
    @DisplayName("given Money of 0.00 then toString is 0.00")
    void zeroMoneyToString() {
        assertEquals("0.00", Money.parse("0.00").toString());
    }

    @Test
    @DisplayName("given Money of 12.34 then toString is 12.34")
    void otherMoneyToString() {
        assertEquals("12.34", Money.parse("12.34").toString());
    }

    @Test
    @DisplayName("given Money of 0.00 is equal to another Money of 0.00")
    void givenMoney0_00WhenTestedForEqualityThenIsEqual() {
        assertEquals(Money.parse("0.00"), Money.parse("0.00"));
    }

    @Test
    @DisplayName("adding Moneys 10.30 + 2.04 then return Money 12.34")
    void givenMoneyWhenAddingMoreMoneyThenReturnSumOfMoney() {
        assertEquals(Money.parse("12.34"), Money.parse("10.30").add(Money.parse("2.04")));
    }

    @Test
    @DisplayName("Money of 12.34 x 2 is Money of 24.68")
    void givenMoneyWhenMultiplyBy2ThenValueOfMoneyDoubles() {
        assertEquals(Money.parse("24.68"), Money.parse("12.34").multiply(2));
    }
}
