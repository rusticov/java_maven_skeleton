package com.codemanship;

import java.util.HashMap;
import java.util.Map;

public class Order {

    public record Entry(Product.Id id, int quantity) {
    }

    private final Inventory inventory;

    private final Map<Product.Id, Integer> productQuantities = new HashMap<>();

    public Order(Inventory inventory, Entry... entries) {
        this.inventory = inventory;
        for (var entry : entries) {
            productQuantities.put(entry.id, entry.quantity);
        }
    }

    public void addItem(Product.Id id, int quantity) {
        inventory.placeProductItemsOnHold(id, quantity);
        productQuantities.put(id, quantity);
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
