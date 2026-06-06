package com.codemanship;

public class Product {

    public Id getId() {
        return id;
    }

    public record Id(int id) {
    }

    private final Id id;
    private final int stock;
    private int onHold;

    public Product(Id id, String description, int stock, int onHold) {
        this.id = id;
        this.stock = stock;
        this.onHold = onHold;
    }

    public int onHold() {
        return onHold;
    }

    public void placeOnHold(int quantity) {
        if (quantity > stock) {
            throw new InsufficientStockException();
        }
        onHold += quantity;
    }
}
