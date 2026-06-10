package com.codemanship;

public record Product(Id id, String description, Money price, int stock, int onHold) {

    public record Id(int id) {
    }

    public Product removeStock(int quantity) {
        return new Product(id, description, Money.parse("159.95"), stock - quantity, onHold);
    }

    public Product releaseFromOnHold(int currentQuantity) {
        return new Product(id, description, Money.parse("159.95"), stock, onHold - currentQuantity);
    }

    public Product placeOnHold(int quantity) {
        checkIfStockIsAvailable(quantity);
        return new Product(id, description, Money.parse("159.95"), stock, onHold + quantity);
    }

    private void checkIfStockIsAvailable(int quantity) {
        var availableStock = stock - onHold;

        if (quantity > availableStock) {
            var message = "Insufficient stock of %s. Only %d currently available.".formatted(description, availableStock);
            throw new InsufficientStockException(message);
        }
    }
}
