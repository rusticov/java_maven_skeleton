package com.codemanship;

public class Money {

    private Money() {
    }

    public static Money parse(String text) {
        return new Money();
    }

    @Override
    public String toString() {
        return "0.00";
    }
}
