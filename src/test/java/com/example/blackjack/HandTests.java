package com.example.blackjack;

import com.example.blackjack.models.Card;
import com.example.blackjack.models.Game;
import com.example.blackjack.models.Hand;
import com.example.blackjack.models.Rank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

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
        for (int i = 0; i < 100; i++) {
            game = new Game(bet);
            hand = game.getPlayerHand();
            List<Card> cards = hand.getCards();
            if (cards.get(0).getRank() == Rank.THREE || cards.get(1).getRank() == Rank.THREE) {
                assert(!hand.isBlackjack());
            }
        }
    }

    @Test
    void isBlackjackTC136() {

    }
}
