package com.codemanship;

public class Order {
    public void addItem(Product product, int quantity) {
        product.placeOnHold(quantity);
    }

    public int quantityOf(Product product) {
        return 1;
    }
}
