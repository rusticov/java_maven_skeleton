package com.codemanship;

import java.util.HashMap;
import java.util.Map;

public class Order {


    public enum Status {
        Canceled, Confirmed, Open;
    }

    public record Entry(Product.Id id, int quantity) {
    }

    private final Inventory inventory;
    private Status status;

    private final Map<Product.Id, Integer> productQuantities = new HashMap<>();

    public Order(Inventory inventory, Entry... entries) {
        this.inventory = inventory;
        for (var entry : entries) {
            productQuantities.put(entry.id, entry.quantity);
        }

        status = Status.Open;
    }

    public Status getStatus() {
        return status;
    }

    public void addItem(Product.Id id, int quantity) {
        inventory.placeProductItemsOnHold(id, quantity);
        productQuantities.put(id, quantity);
    }

    public int quantityOf(Product.Id id) {
        return productQuantities.getOrDefault(id, 0);
    }

    public Money totalExcludingShipping() {
        return productQuantities.entrySet().stream().
            map((entry) -> {
                Product.Id id = entry.getKey();
                int quantity = entry.getValue();

                return inventory.getProduct(id).price().multiply(quantity);
            }).
            reduce(Money.parse("0.00"), Money::add);
    }

    public Money shippingCost(Country country) {
        if (totalExcludingShipping().compareTo(Money.parse("100.00")) >= 0) {
            return Money.parse("0.00");
        }

        if (country == Country.UNITED_KINGDOM) {
            return Money.parse("5.99");
        }
        return Money.parse("9.99");
    }

    public void removeProduct(Product.Id id) {
        var currentQuantity = quantityOf(id);
        inventory.releaseProductItemsFromOnHold(id, currentQuantity);
        productQuantities.remove(id);
    }

    public void cancel() {
        productQuantities.forEach(inventory::releaseProductItemsFromOnHold);
        status = Status.Canceled;
    }

    public void confirm() {
        productQuantities.forEach(inventory::releaseProductItemsFromOnHold);
        productQuantities.forEach(inventory::removeStock);
        status = Status.Confirmed;
    }
}
