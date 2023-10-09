package com.example.blackjack.models;

import jakarta.persistence.*;

@Entity
@Table(name = "cards")
public class Card {

    @Id
    @GeneratedValue
    private long id; // JPA ID
    private Rank rank; // Rank of the card
    private boolean flipped; // Whether the card is flipped
    private String face; // String representation of the card

    /**
     * Creates a card. This constructor is only used by JPA.
     */
    protected Card() {}

    /**
     * Creates a card with the given rank and suit.
     * @param rank the rank of the card
     * @param suit the suit of the card
     */
    public Card(Rank rank, String suit) {
        this.rank = rank;
        this.flipped = false;

        this.face = String.format("""
                ┌───────────┐
                │ %s      %s │
                │           │
                │           │
                │     %s     │
                │           │
                │           │
                │ %s      %s │
                └───────────┘""", rank.rightPad(), suit, suit, suit, rank.leftPad());
    }

    /**
     * Gets the rank of the card.
     * @return the rank of the card
     */
    public Rank getRank() {
        return rank;
    }

    /**
     * Checks if the card is flipped.
     * @return true if the card is flipped, false otherwise
     */
    public boolean isFlipped() {
        return flipped;
    }

    /**
     * Flips the card.
     * @return the card
     */
    public Card flip() {
        flipped = !flipped;
        return this;
    }

    /**
     * Gets the string representation of the card.
     * @return the string representation of the card
     */
    @Override
    public String toString() {
        if (flipped) { return face; }

        return """
                ┌───────────┐
                │ ░░░░░░░░░ │
                │ ░░░░░░░░░ │
                │ ░░░░░░░░░ │
                │ ░░░░░░░░░ │
                │ ░░░░░░░░░ │
                │ ░░░░░░░░░ │
                │ ░░░░░░░░░ │
                └───────────┘""";
    }
}
