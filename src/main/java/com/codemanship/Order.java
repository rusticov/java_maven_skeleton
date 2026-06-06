package com.codemanship;

import java.util.HashMap;
import java.util.Map;

public class Order {

    private final Map<Product.Id, Integer> productQuantities = new HashMap<>();

    public void addItem(Product product, int quantity) {
        product.placeOnHold(quantity);
        productQuantities.put(product.getId(), quantity);
    }

    public int quantityOf(Product product) {
        return productQuantities.get(product.getId());
    }
}
