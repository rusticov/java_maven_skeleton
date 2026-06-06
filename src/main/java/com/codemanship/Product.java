package com.codemanship;

public class Product {

    public record Id(int id) {
    }

    private int onHold;

    public Product(Id id, int stock, int onHold) {
        this.onHold = onHold;
    }

    public int onHold() {
        return onHold;
    }

    public void placeOnHold(int quantity) {
        onHold += quantity;
    }
}
