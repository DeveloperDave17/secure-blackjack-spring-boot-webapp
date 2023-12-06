package com.example.blackjack;

import com.example.blackjack.models.Deck;
import com.example.blackjack.models.Game;
import com.example.blackjack.models.Hand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

public class DeckTests {

    Deck deck;

    @BeforeEach
    void setup() {
        deck = new Deck();
    }

    @Test
    void dealFaceUpTC31() {
        // Getting a playerHand object (since no constructor is available)
        Game game = new Game(100.0);
        Hand playerHand = game.getPlayerHand();
        // Dealing to the playerHand
        deck.dealFaceUp(playerHand);
    }

    @Test
    void dealFaceUpTC33() {
        try {
            deck.dealFaceUp(null);
            // Method should not execute without throwing an exception
            fail();
        } catch (Exception e) {
            assertNotNull(e);
        }
    }

    @Test
    void dealFaceDownTC34() {
        // Getting a playerHand object (since no constructor is available)
        Game game = new Game(100.0);
        Hand playerHand = game.getPlayerHand();
        // Dealing to the playerHand
        deck.dealFaceDown(playerHand);
    }

    @Test
    void dealFaceDownTC36() {
        try {
            deck.dealFaceDown(null);
            // Method should not execute without throwing an exception
            fail();
        } catch (Exception e) {
            assertNotNull(e);
        }
    }

    @Test
    void dealFaceUpTC115() {
        dealFaceUpTC31();
    }

    @Test
    void dealFaceUpTC116() {
        dealFaceUpTC31();
    }

    @Test
    void dealFaceDownTC117() {
        dealFaceDownTC34();
    }

    @Test
    void dealFaceDownTC118() {
        dealFaceDownTC34();
    }

    @Test
    void deckTC119() {
        try {
            Deck anotherDeck = new Deck();
            assertNotNull(anotherDeck);
        } catch(Exception e) {
            // This method execution should not cause an exception to be thrown
            fail();
        }
    }

    @Test
    void deckTC120() {
        deckTC119();
    }

    @Test
    void dealFaceUpTC162() {
        dealFaceUpTC31();
    }

    @Test
    void dealFaceDownTC163() {
        dealFaceDownTC34();
    }

    @Test
    void deckTC164() {
        deckTC119();
    }
}
