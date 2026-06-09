package com.codemanship;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private final Map<Product.Id, Product> productsById;

    public Inventory(Product... products) {
        this.productsById = new HashMap<>();
        for (var product : products) {
            this.productsById.put(product.getId(), product);
        }
    }

    public Product getProduct(Product.Id id) {
        return productsById.get(id);
    }

    public void placeProductItemsOnHold(Product.Id id, int quantity) {
        var inventoryProduct = getProduct(id);
        productsById.put(id, inventoryProduct.placeOnHold(quantity));
    }

    void releaseProductItemsFromOnHold(Product.Id id, int currentQuantity) {
        var inventoryProduct = getProduct(id);
        var updatedProduct = inventoryProduct.releaseFromOnHold(currentQuantity);
        productsById.put(id, updatedProduct);
    }
}
