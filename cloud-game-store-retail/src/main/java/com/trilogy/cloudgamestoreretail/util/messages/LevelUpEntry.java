package com.trilogy.cloudgamestoreretail.util.messages;

import com.trilogy.cloudgamestoreretail.model.LevelUp;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

public class LevelUpEntry {

    private int levelUpId;
    @Digits(integer = 11, fraction = 0)
    @NotNull
    private int customerId;
    @Digits(integer = 11, fraction = 0)
    @NotNull
    private int points;
    private String memberDate;

    public LevelUpEntry() {
    }

    public LevelUpEntry(LevelUp levelUp) {
        this.levelUpId = levelUp.getLevelUpId();
        this.customerId = levelUp.getCustomerId();
        this.memberDate = levelUp.getMemberDate().toString();
        this.points = levelUp.getPoints();
    }


    public int getLevelUpId() {
        return levelUpId;
    }

    public void setLevelUpId(int levelUpId) {
        this.levelUpId = levelUpId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getMemberDate() {
        return memberDate;
    }

    public void setMemberDate(String memberDate) {
        this.memberDate = memberDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LevelUpEntry that = (LevelUpEntry) o;
        return getLevelUpId() == that.getLevelUpId() &&
                getCustomerId() == that.getCustomerId() &&
                getPoints() == that.getPoints() &&
                getMemberDate().equals(that.getMemberDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLevelUpId(), getCustomerId(), getPoints(), getMemberDate());
    }

    @Override
    public String toString() {
        return "LevelUpEntry{" +
                "levelUpId=" + levelUpId +
                ", customerId=" + customerId +
                ", points=" + points +
                ", memberDate=" + memberDate +
                '}';
    }
}
