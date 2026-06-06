package com.codemanship;

import java.util.HashMap;
import java.util.Map;

public class Order {

    public record Entry(Product.Id id, int quantity) {
    }

    private final Map<Product.Id, Integer> productQuantities = new HashMap<>();

    public Order(Entry... entries) {
        for (var entry : entries) {
            productQuantities.put(entry.id, entry.quantity);
        }
    }

    public void addItem(Product product, int quantity) {
        product.placeOnHold(quantity);
        productQuantities.put(product.getId(), quantity);
    }

    public int quantityOf(Product product) {
        return productQuantities.getOrDefault(product.getId(), 0);
    }

    public void removeProduct(Product product) {
        var currentQuantity = quantityOf(product);
        product.releaseFromOnHold(currentQuantity);
        productQuantities.remove(product.getId());
    }
}
