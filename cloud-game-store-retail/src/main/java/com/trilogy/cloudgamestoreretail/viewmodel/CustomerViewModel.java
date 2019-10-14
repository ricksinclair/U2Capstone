package com.trilogy.cloudgamestoreretail.viewmodel;

import com.trilogy.cloudgamestoreretail.model.Customer;
import com.trilogy.cloudgamestoreretail.model.LevelUp;

import java.util.Objects;

public class CustomerViewModel {

    private Customer customer;
    private LevelUp levelUp;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LevelUp getLevelUp() {
        return levelUp;
    }

    public void setLevelUp(LevelUp levelUp) {
        this.levelUp = levelUp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerViewModel that = (CustomerViewModel) o;
        return getCustomer().equals(that.getCustomer()) &&
                getLevelUp().equals(that.getLevelUp());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCustomer(), getLevelUp());
    }
}
