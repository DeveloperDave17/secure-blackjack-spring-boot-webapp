package com.example.blackjack;

import com.example.blackjack.models.Card;
import com.example.blackjack.models.Game;
import com.example.blackjack.models.Hand;
import com.example.blackjack.models.Rank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

public class HandTests {

    Hand hand;
    Game game;
    @BeforeEach
    void setup() {
        double bet = 100.0;
        game = new Game(bet);
        hand = game.getPlayerHand();
    }

    @Test
    void flipLastCardTC133() {
        try {
            hand.flipLastCard();
        } catch(Exception e) {
            fail();
        }
    }

    @Test
    void flipLastCardTC134() {
        flipLastCardTC133();
    }

    @Test
    void isBlackjackTC135() {
        double bet = 100.0;
        game = new Game(bet);
        Hand hand = game.getPlayerHand();
        List<Card> cards = hand.getCards();
        cards.remove(0);
        cards.remove(0);
        cards.add(new Card(Rank.THREE, "♣"));
        cards.get(0).flip();
        assert(!hand.isBlackjack());
    }

    @Test
    void isBlackjackTC136() {
        double bet = 100.0;
        game = new Game(bet);
        Hand hand = game.getPlayerHand();
        List<Card> cards = hand.getCards();
        cards.remove(0);
        cards.remove(0);
        cards.add(new Card(Rank.ACE, "♣"));
        cards.get(0).flip();
        assert(hand.isBlackjack());
    }

    @Test
    void isBlackjackTC137() {
        double bet = 100.0;
        game = new Game(bet);
        Hand hand = game.getPlayerHand();
        List<Card> cards = hand.getCards();
        cards.remove(0);
        cards.remove(0);
        cards.add(new Card(Rank.TEN, "♣"));
        cards.get(0).flip();
        assert(hand.isBlackjack());
    }

    @Test
    void isBlackjackTC138() {
        double bet = 100.0;
        game = new Game(bet);
        hand = game.getPlayerHand();
        // clear the hand
        hand.getCards().remove(0);
        hand.getCards().remove(0);
        assert(!hand.isBlackjack());
    }

    @Test
    void isBlackjackTC139() {
        isBlackjackTC135();
    }

    @Test
    void isBlackjackTC140() {
        double bet = 100.0;
        game = new Game(bet);
        Hand hand = game.getPlayerHand();
        List<Card> cards = hand.getCards();
        cards.remove(0);
        cards.remove(0);
        cards.add(new Card(Rank.TEN, "♣"));
        cards.get(0).flip();
        cards.add(new Card(Rank.TEN, "♣"));
        cards.get(1).flip();
        assert(hand.isBlackjack());
    }

    @Test
    void getValueTC141() {
        double bet = 100.0;
        game = new Game(bet);
        Hand hand = game.getPlayerHand();
        List<Card> cards = hand.getCards();
        cards.remove(0);
        cards.remove(0);
        cards.add(new Card(Rank.THREE, "♣"));
        cards.add(new Card(Rank.THREE, "♣"));
        int expectedValue = 0;
        int actualValue = hand.getValue();
        assert(expectedValue == actualValue);
    }

    @Test
    void getValueTC142() {
        double bet = 100.0;
        game = new Game(bet);
        Hand hand = game.getPlayerHand();
        List<Card> cards = hand.getCards();
        cards.remove(0);
        cards.remove(0);
        cards.add(new Card(Rank.ACE, "♣"));
        cards.get(0).flip();
        cards.add(new Card(Rank.ACE, "♣"));
        cards.get(1).flip();
        int expectedValue = 2;
        int actualValue = hand.getValue();
        assert(expectedValue == actualValue);
    }

    @Test
    void getValueTC143() {
        double bet = 100.0;
        game = new Game(bet);
        Hand hand = game.getPlayerHand();
        List<Card> cards = hand.getCards();
        cards.remove(0);
        cards.remove(0);
        cards.add(new Card(Rank.THREE, "♣"));
        cards.get(0).flip();
        cards.add(new Card(Rank.THREE, "♣"));
        cards.get(1).flip();
        int expectedValue = 6;
        int actualValue = hand.getValue();
        assert(expectedValue == actualValue);
    }

    @Test
    void getValueTC144() {
        double bet = 100.0;
        game = new Game(bet);
        Hand hand = game.getPlayerHand();
        // Remove both cards from the hand
        hand.getCards().remove(0);
        hand.getCards().remove(0);
        int expectedValue = 0;
        int actualValue = hand.getValue();
        assert(expectedValue == actualValue);
    }

    @Test
    void getValueTC145() {
        double bet = 100.0;
            game = new Game(bet);
            Hand hand = game.getPlayerHand();
            // Remove cards from hand
            hand.getCards().remove(0);
            hand.getCards().remove(0);
            hand.getCards().add(new Card(Rank.ACE, "♣"));
            hand.getCards().get(0).flip();
            int expectedValue = 1;
            int actualValue = hand.getValue();
            assert (expectedValue == actualValue);
    }

    @Test
    void getValueTC146() {
        getValueTC142();
    }

    @Test
    void getValueTC147() {
        getValueTC144();
    }

    @Test
    void getValueTC148() {
        getValueTC146();
    }

    @Test
    void getValueTC149() {
        double bet = 100.0;
        game = new Game(bet);
        Hand hand = game.getPlayerHand();
        List<Card> cards = hand.getCards();
        cards.remove(0);
        cards.remove(0);
        cards.add(new Card(Rank.ACE, "♣"));
        cards.get(0).flip();
        cards.add(new Card(Rank.ACE, "♣"));
        cards.get(1).flip();
        cards.add(new Card(Rank.ACE, "♣"));
        cards.get(2).flip();
        int expectedValue = 3;
        int actualValue = hand.getValue();
        assert(expectedValue == actualValue);
    }

    @Test
    void isBlackjackTC166() {
        isBlackjackTC135();
    }

    @Test
    void isBlackjackTC167() {
        isBlackjackTC136();
    }

    @Test
    void getValueTC168() {
        getValueTC142();
    }

    @Test
    void getValueTC169() {
        getValueTC141();
    }

    @Test
    void getValueTC170() {
        getValueTC146();
    }

    @Test
    void flipLastCardTC230() {
        double bet = 100.0;
        game = new Game(bet);
        Hand hand = game.getPlayerHand();
        List<Card> cards = hand.getCards();
        cards.remove(0);
        cards.remove(0);
        cards.add(new Card(Rank.ACE, "♣"));
        cards.get(0).flip();
        cards.add(new Card(Rank.ACE, "♦"));
        hand.flipLastCard();
        assert(cards.get(1).isFlipped());
    }

    @Test
    void flipLastCardTC231() {
        try {
            double bet = 100.0;
            game = new Game(bet);
            Hand hand = game.getPlayerHand();
            List<Card> cards = hand.getCards();
            cards.remove(0);
            cards.remove(0);
            hand.flipLastCard();
            // The test should throw an exception before this point
            fail();
        } catch (Exception e) {
            assertNotNull(e);
        }
    }

    @Test
    void isBlackjackTC233() {
        double bet = 100.0;
        game = new Game(bet);
        Hand hand = game.getPlayerHand();
        List<Card> cards = hand.getCards();
        cards.remove(0);
        cards.remove(0);
        cards.add(new Card(Rank.ACE, "♣"));
        cards.get(0).flip();
        cards.add(new Card(Rank.ACE, "♦"));
        cards.get(1).flip();
        assert(!hand.isBlackjack());
    }

    @Test
    void isBlackjackTC234() {
        double bet = 100.0;
        game = new Game(bet);
        Hand hand = game.getPlayerHand();
        List<Card> cards = hand.getCards();
        cards.remove(0);
        cards.remove(0);
        assert(!hand.isBlackjack());
    }

    @Test
    void isBlackjackTC236() {
        double bet = 100.0;
        game = new Game(bet);
        Hand hand = game.getPlayerHand();
        List<Card> cards = hand.getCards();
        cards.remove(0);
        cards.remove(0);
        cards.add(new Card(Rank.ACE, "♣"));
        cards.get(0).flip();
        cards.add(new Card(Rank.ACE, "♦"));
        cards.get(1).flip();
        int expectedOutput = 2;
        int actualOutput = hand.getValue();
        assert(expectedOutput == actualOutput);
    }

    @Test
    void isBlackjackTC237() {
        double bet = 100.0;
        game = new Game(bet);
        Hand hand = game.getPlayerHand();
        List<Card> cards = hand.getCards();
        cards.remove(0);
        cards.remove(0);
        int expectedOutput = 0;
        int actualOutput = hand.getValue();
        assert(expectedOutput == actualOutput);
    }

    @Test
    void alphaOmegaTC243() {
        hand.getCards();
        hand.getValue();
        hand.isBlackjack();
        hand.flipLastCard();
    }
}
