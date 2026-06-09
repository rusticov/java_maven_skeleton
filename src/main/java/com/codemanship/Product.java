package com.codemanship;

public record Product(Id id, String description, int stock, int onHold) {

    public record Id(int id) {
    }

    public Product releaseFromOnHold(int currentQuantity) {
        return new Product(id, description, stock, onHold - currentQuantity);
    }

    public Product placeOnHold(int quantity) {
        checkIfStockIsAvailable(quantity);
        return new Product(id, description, stock, onHold + quantity);
    }

    private void checkIfStockIsAvailable(int quantity) {
        var availableStock = stock - onHold;

        if (quantity > availableStock) {
            var message = "Insufficient stock of %s. Only %d currently available.".formatted(description, availableStock);
            throw new InsufficientStockException(message);
        }
    }
}
