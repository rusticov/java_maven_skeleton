package com.codemanship;

public class Product {
    private int onHold;

    public Product(int stock, int onHold) {
        this.onHold = onHold;
    }

    public int onHold() {
        return onHold;
    }

    public void placeOnHold(int quantity) {
        onHold += quantity;
    }
}
