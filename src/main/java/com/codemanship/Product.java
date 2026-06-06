package com.codemanship;

public class Product {

    public Id getId() {
        return id;
    }

    public record Id(int id) {
    }

    private final Id id;
    private final String description;
    private final int stock;
    private int onHold;

    public Product(Id id, String description, int stock, int onHold) {
        this.id = id;
        this.description = description;
        this.stock = stock;
        this.onHold = onHold;
    }

    public int onHold() {
        return onHold;
    }

    public void placeOnHold(int quantity) {
        if (quantity > stock) {
            var message = "Insufficient stock of %s. Only %d currently available.".formatted(description, stock);
            throw new InsufficientStockException(message);
        }
        onHold += quantity;
    }
}
