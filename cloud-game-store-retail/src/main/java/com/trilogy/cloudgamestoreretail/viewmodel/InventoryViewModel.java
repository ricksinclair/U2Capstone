package com.trilogy.cloudgamestoreretail.viewmodel;

import com.trilogy.cloudgamestoreretail.model.Inventory;
import com.trilogy.cloudgamestoreretail.model.Product;

import java.util.Objects;

public class InventoryViewModel {
    private Inventory inventory;
    private Product product;

    public InventoryViewModel() {
    }

    public InventoryViewModel(Inventory inventory) {
        this.inventory = inventory;
    }

    public InventoryViewModel(Product product) {
        this.product = product;
    }

    public InventoryViewModel(Inventory inventory, Product product) {
        this.inventory = inventory;
        this.product = product;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InventoryViewModel that = (InventoryViewModel) o;
        return getInventory().equals(that.getInventory()) &&
                getProduct().equals(that.getProduct());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInventory(), getProduct());
    }
}
