package com.example.blackjack.models;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
@Table(name = "decks")
public class Deck {

    @Id
    @GeneratedValue
    private long id; // JPA ID
    @OneToMany(cascade = CascadeType.ALL)
    private final List<Card> cards; // Cards in the deck

    /**
     * Creates a deck.
     */
    public Deck() {
        this.cards = new ArrayList<>();

        for (String suit : new String[]{"♣", "♦", "♥", "♠"}) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(rank, suit));
            }
        }
    }

    /**
     * Deals a card face up to the hand.
     * @param hand the hand to deal to
     */
    public void dealFaceUp(Hand hand) {
        hand.getCards().add(cards.remove(new Random().nextInt(cards.size())).flip());
    }

    /**
     * Deals a card face down to the hand.
     * @param hand the hand to deal to
     */
    public void dealFaceDown(Hand hand) {
        hand.getCards().add(cards.remove(new Random().nextInt(cards.size())));
    }
}
