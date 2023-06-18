package com.wileyedge.model;

import java.math.BigDecimal;

import com.wileyedge.util.Coin;

public class Change {
    private int quarters;
    private int dimes;
    private int nickels;
    private int pennies;

    public Change(BigDecimal amountInPennies) {
        calculateCoins(amountInPennies);
    }

    private void calculateCoins(BigDecimal amountInPennies) {
        int remainingAmount = amountInPennies.intValue();

        quarters = remainingAmount / Coin.QUARTER.getValue();
        remainingAmount %= Coin.QUARTER.getValue();

        dimes = remainingAmount / Coin.DIME.getValue();
        remainingAmount %= Coin.DIME.getValue();

        nickels = remainingAmount / Coin.NICKEL.getValue();
        remainingAmount %= Coin.NICKEL.getValue();

        pennies = remainingAmount;
    }

    // Getters and setters

    public int getQuarters() {
        return quarters;
    }

    public void setQuarters(int quarters) {
        this.quarters = quarters;
    }

    public int getDimes() {
        return dimes;
    }

    public void setDimes(int dimes) {
        this.dimes = dimes;
    }

    public int getNickels() {
        return nickels;
    }

    public void setNickels(int nickels) {
        this.nickels = nickels;
    }

    public int getPennies() {
        return pennies;
    }

    public void setPennies(int pennies) {
        this.pennies = pennies;
    }
    
    
}
