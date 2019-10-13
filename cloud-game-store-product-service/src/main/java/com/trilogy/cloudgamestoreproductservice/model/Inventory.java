package com.trilogy.cloudgamestoreproductservice.model;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class Inventory {

    private int inventoryId;
    @Digits(integer = 11, fraction = 0)
    @NotNull
    private int productId;
    @Digits(integer = 11, fraction = 0)
    @NotNull
    private int quantity;

    public Inventory() {
    }

    public Inventory(@Digits(integer = 11, fraction = 0) @NotNull int productId, @Digits(integer = 11, fraction = 0) @NotNull int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Inventory(int inventoryId, @Digits(integer = 11, fraction = 0) @NotNull int productId, @Digits(integer = 11, fraction = 0) @NotNull int quantity) {
        this.inventoryId = inventoryId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inventory inventory = (Inventory) o;
        return getInventoryId() == inventory.getInventoryId() &&
                getProductId() == inventory.getProductId() &&
                getQuantity() == inventory.getQuantity();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInventoryId(), getProductId(), getQuantity());
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "inventoryId=" + inventoryId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}
