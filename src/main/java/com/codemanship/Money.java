package com.codemanship;

import java.math.BigDecimal;

public class Money {

    private final BigDecimal value;

    private Money(BigDecimal bigDecimal) {
        this.value = bigDecimal;
    }

    public static Money parse(String text) {
        return new Money(new BigDecimal(text));
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
