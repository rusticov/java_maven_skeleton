package com.codemanship;

public class Inventory {
    private final Product product;

    public Inventory(Product... product) {
        this.product = product[0];
    }

    public Product getProduct(Product.Id id) {
        return product;
    }

    public void placeProductItemsOnHold(Product.Id id, int quantity) {
        var inventoryProduct = getProduct(id);
        inventoryProduct.placeOnHold(quantity);
    }

    void releaseProductItemsFromOnHold(Product.Id id, int currentQuantity) {
        var inventoryProduct = getProduct(id);
        inventoryProduct.releaseFromOnHold(currentQuantity);
    }
}
