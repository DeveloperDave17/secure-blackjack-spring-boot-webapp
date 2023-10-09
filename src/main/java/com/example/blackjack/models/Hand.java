package com.example.blackjack.models;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hands")
public class Hand {

    @Id
    @GeneratedValue
    private long id; // JPA ID
    @OneToMany(cascade = CascadeType.ALL)
    private final List<Card> cards = new ArrayList<>(); // Cards in the hand

    /**
     * Creates a hand. This constructor is only used by JPA.
     */
    protected Hand() {}

    /**
     * Flips the last card in the hand.
     */
    public void flipLastCard() {
        cards.get(cards.size() - 1).flip();
    }

    /**
     * Checks if the hand is a blackjack.
     * @return true if the hand is a blackjack, false otherwise
     */
    public boolean isBlackjack() {
        for (Card card : cards) {
            Rank rank = card.getRank();

            if (rank != Rank.ACE && rank != Rank.TEN) {
                return false;
            }
        }

        return true;
    }

    /**
     * Gets the cards in the hand.
     * @return the cards in the hand
     */
    public List<Card> getCards() {
        return cards;
    }

    /**
     * Gets the total value of the cards in the hand.
     * @return the value of the hand
     */
    public int getValue() {
        int value = 0;
        int numAces = 0;

        for (Card card : cards) {
            if (!card.isFlipped()) { continue; }
            Rank rank = card.getRank();

            if (rank == Rank.ACE) { numAces++; }
            value += rank.getValue();
        }

        while (value > 21 && numAces > 0) {
            value -= 10;
            numAces--;
        }

        return value;
    }
}
