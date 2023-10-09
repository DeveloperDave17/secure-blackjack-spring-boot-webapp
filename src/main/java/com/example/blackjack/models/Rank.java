package com.example.blackjack.models;

public enum Rank {

    ACE(1, "A"),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10, "10"),
    JACK(10, "J"),
    QUEEN(10, "Q"),
    KING(10, "K");

    private final int value; // Value of the rank
    private final String name; // Name of the rank

    /**
     * Creates a rank.
     * @param value the value of the rank
     * @param name the name of the rank
     */
    Rank(int value, String name) {
        this.value = value;
        this.name = name;
    }

    /**
     * Gets the value of the rank.
     * @return the value of the rank
     */
    public int getValue() {
        return value;
    }

    /**
     * Gets the name of the rank with a space to the left.
     * @return the name of the rank
     */
    public String leftPad() {
        return name.length() > 1 ? name : " " + name;
    }

    /**
     * Gets the name of the rank with a space to the right.
     * @return the name of the rank
     */
    public String rightPad() {
        return name.length() > 1 ? name : name + " ";
    }
}
