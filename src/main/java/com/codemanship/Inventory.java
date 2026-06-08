package com.codemanship;

public class Inventory {
    private final Product product;

    public Inventory(Product product) {
        this.product = product;
    }

    public Product getProduct(Product.Id id) {
        return product;
    }

    public void placeProductItemsOnHold(Product.Id id, int quantity, Order order) {
        var inventoryProduct = getProduct(id);
        inventoryProduct.placeOnHold(quantity);
    }
}
