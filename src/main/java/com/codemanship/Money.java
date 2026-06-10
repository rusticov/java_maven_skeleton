package com.codemanship;

import java.math.BigDecimal;
import java.util.Objects;

public class Money {

    private final BigDecimal value;

    private Money(BigDecimal bigDecimal) {
        this.value = bigDecimal;
    }

    public static Money parse(String text) {
        return new Money(new BigDecimal(text));
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return Objects.equals(value, money.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
