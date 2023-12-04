package com.example.blackjack;

import com.example.blackjack.models.Game;
import com.example.blackjack.models.Hand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTests {

    Game game;

    @BeforeEach
    void setup() {
        double bet = 100.0;
        game = new Game(bet);
    }

    @Test
    void setMessageTC37() {
        try {
            game.setMessage("Player wins!");
        } catch(Exception e) {
            // The test fails if an exception is thrown
            fail();
        }
    }

    @Test
    void setMessageTC38() {
        try {
            game.setMessage(null);
            // Fails if the method executes with throwing an Exception
            fail();
        } catch(Exception e) {
            assertNotNull(e);
        }
    }

    @Test
    void dealAgainTC40() {
        double bet = 4.20;
        game.dealAgain(bet);
        boolean actualOngoing = game.isOngoing();
        // check to see if onGoing is true;
        assert(actualOngoing);

        // Checks to see if both the players hand and the dealers hand are reset by checking the size
        Hand playerHand = game.getPlayerHand();
        int actualPlayerHandSize = playerHand.getCards().size();
        int expectedHandSize = 2;
        assertEquals(actualPlayerHandSize, expectedHandSize);
        Hand dealerHand = game.getDealerHand();
        int actualDealerHandSize = dealerHand.getCards().size();
        assertEquals(actualDealerHandSize, expectedHandSize);
    }

    @Test
    void dealAgainTC43() {
        try {
            double bet = -420;
            game.dealAgain(bet);
            // The method should not execute fully and reach here.
            fail();
        } catch(Exception e) {
            assertNotNull(e);
        }
    }

    @Test
    void setMessageTC121() {
        try {
            game.setMessage("Dealer busts! Player wins!");
        } catch(Exception e) {
            // This method execution should not cause an exception.
            fail();
        }
    }

    @Test
    void setMessageTC122() {
        setMessageTC121();
    }

    @Test
    void winBetTC123() {
        try {
            game.winBet();
        } catch(Exception e) {
            // This method execution should not cause an exception.
            fail();
        }
    }

    @Test
    void winBetTC124() {
        winBetTC123();
    }

    @Test
    void winBlackjackTC125() {
        try {
            game.winBlackjack();
        } catch(Exception e) {
            // This method execution should not cause an exception.
            fail();
        }
    }

    @Test
    void winBlackjackTC126() {
        winBlackjackTC125();
    }

    @Test
    void loseBetTC127() {
        try {
            game.loseBet();
        } catch(Exception e) {
            // This method execution should not cause an exception.
            fail();
        }
    }

    @Test
    void loseBetTC128() {
        loseBetTC127();
    }
    
}
