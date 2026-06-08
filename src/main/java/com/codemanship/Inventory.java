package com.codemanship;

public class Inventory {
    private final Product product;

    public Inventory(Product product) {
        this.product = product;
    }

    public Product getProduct(Product.Id id) {
        return product;
    }
}
