package com.trilogy.cloudgamestoreretail.util.messages;

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
    private LocalDate memberDate;

    public LevelUpEntry() {
    }

    public LevelUpEntry(@Digits(integer = 11, fraction = 0) @NotNull int customerId, @Digits(integer = 11, fraction = 0) @NotNull int points, LocalDate memberDate) {
        this.customerId = customerId;
        this.points = points;
        this.memberDate = memberDate;
    }

    public LevelUpEntry(int levelUpId, @Digits(integer = 11, fraction = 0) @NotNull int customerId, @Digits(integer = 11, fraction = 0) @NotNull int points, LocalDate memberDate) {
        this.levelUpId = levelUpId;
        this.customerId = customerId;
        this.points = points;
        this.memberDate = memberDate;
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

    public LocalDate getMemberDate() {
        return memberDate;
    }

    public void setMemberDate(LocalDate memberDate) {
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
