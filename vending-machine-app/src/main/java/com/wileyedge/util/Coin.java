package com.wileyedge.util;

public enum Coin {
    QUARTER(25),
    DIME(10),
    NICKEL(5),
    PENNY(1);

    private int value;

    Coin(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
