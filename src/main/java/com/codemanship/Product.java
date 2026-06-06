package com.codemanship;

public class Product {

    public Id getId() {
        return id;
    }

    public record Id(int id) {
    }

    private final Id id;
    private int onHold;

    public Product(Id id, int stock, int onHold) {
        this.id = id;
        this.onHold = onHold;
    }

    public int onHold() {
        return onHold;
    }

    public void placeOnHold(int quantity) {
        onHold += quantity;
    }
}
