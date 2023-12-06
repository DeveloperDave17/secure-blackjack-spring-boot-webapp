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

    @Test
    void tieTC129() {
        try {
            game.tie();
        } catch(Exception e) {
            // This method execution should not cause an exception.
            fail();
        }
    }

    @Test
    void tieTC130() {
        tieTC129();
    }

    @Test
    void dealAgainTC131() {
        try {
            double bet = 100.0;
            game.dealAgain(bet);
        } catch(Exception e) {
            // This method execution should not cause an exception.
            fail();
        }
    }

    @Test
    void dealAgainTC132() {
       dealAgainTC131();
    }

    @Test
    void setMessageTC165() {
        setMessageTC121();
    }

    @Test
    void dealAgainTC165() {
        dealAgainTC131();
    }

    @Test
    void winBetTC191() {
        game.winBet();
        String expectedMoney = "$100.00";
        String actualMoney = game.getMoney();
        assert(actualMoney).equals(expectedMoney);
        assert(!game.isOngoing());
    }

    @Test
    void winBetTC192() {
        game.tie();
        System.out.println("Game tied to ensure ongoing gets set to false and money is not modified.");
        game.winBet();
        String expectedMoney = "$100.00";
        String actualMoney = game.getMoney();
        assert(actualMoney).equals(expectedMoney);
        assert(!game.isOngoing());
    }

    @Test
    void winBetTC194() {
        try {
            game = new Game(Double.MAX_VALUE + 1);
            game.winBet();
            // We are expecting a failure before this point.
            fail();
        } catch (Exception e) {
            assertNotNull(e);
        }
    }

    @Test
    void winBetTC195() {
        game = new Game(Double.MAX_VALUE);
        game.winBet();
        String  expectedOutput = String.format("$%.2f", Double.MAX_VALUE);
        String actualOutput = game.getMoney();
        assert(actualOutput).equals(expectedOutput);
    }

    @Test
    void winBetTC196() {
        game = new Game(Double.MAX_VALUE - 1);
        game.winBet();
        String  expectedOutput = String.format("$%.2f", Double.MAX_VALUE - 1);
        String actualOutput = game.getMoney();
        assert(actualOutput).equals(expectedOutput);
    }

    @Test
    void winBetTC197() {
        try {
            double bet = 0.0;
            game = new Game(bet);
            game.winBet();
            // We are expecting a failure before this point.
            fail();
        } catch (Exception e) {
            assertNotNull(e);
        }
    }

    @Test
    void winBetTC198() {
        double bet = 0.00001;
        game = new Game(bet);
        game.winBet();
        String  expectedOutput = String.format("$%.2f", bet);
        String actualOutput = game.getMoney();
        assert(actualOutput).equals(expectedOutput);
    }

    @Test
    void winBetTC199() {
        double bet = 1.0;
        game = new Game(bet);
        game.winBet();
        String  expectedOutput = String.format("$%.2f", bet);
        String actualOutput = game.getMoney();
        assert(actualOutput).equals(expectedOutput);
    }

    @Test
    void winBlackjack200() {
        double bet = 100.0;
        game = new Game(bet);
        game.winBlackjack();
        String  expectedOutput = String.format("$%.2f", bet + 50.0);
        String actualOutput = game.getMoney();
        assert(actualOutput).equals(expectedOutput);
        assert(!game.isOngoing());
    }

    @Test
    void winBlackjack201() {
        double bet = 100.0;
        game = new Game(bet);
        game.tie();
        System.out.println("Game tied to ensure ongoing gets set to false and money is not modified.");
        game.winBlackjack();
        String  expectedOutput = String.format("$%.2f", bet + 50.0);
        String actualOutput = game.getMoney();
        assert(actualOutput).equals(expectedOutput);
        assert(!game.isOngoing());
    }

    @Test
    void winBlackjack202() {
        try {
            double bet = Double.MAX_VALUE + 1;
            game = new Game(bet);
            game.winBlackjack();
            // Expected to fail before this point
            fail();
        } catch (Exception e) {
            assertNotNull(e);
        }
    }

    @Test
    void winBlackjack203() {
        try {
            double bet = Double.MAX_VALUE;
            game = new Game(bet);
            game.winBlackjack();
            // Expected to fail before this point
            fail();
        } catch (Exception e) {
            assertNotNull(e);
        }
    }

    @Test
    void winBlackjack204() {
        try {
            double bet = Double.MAX_VALUE - 1.0;
            game = new Game(bet);
            game.winBlackjack();
            // Expected to fail before this point
            fail();
        } catch (Exception e) {
            assertNotNull(e);
        }
    }

    @Test
    void winBlackjack205() {
        try {
            double bet = 0.0;
            game = new Game(bet);
            game.winBlackjack();
            // Expected to fail before this point
            fail();
        } catch (Exception e) {
            assertNotNull(e);
        }
    }

    @Test
    void winBlackjack206() {
        double bet = 0.00001;
        game = new Game(bet);
        game.tie();
        System.out.println("Game tied to ensure ongoing gets set to false and money is not modified.");
        game.winBlackjack();
        String  expectedOutput = String.format("$%.2f", bet + 0.000005);
        String actualOutput = game.getMoney();
        assert(actualOutput).equals(expectedOutput);
        assert(!game.isOngoing());
    }

    @Test
    void winBlackjack207() {
        double bet = 1.0;
        game = new Game(bet);
        game.tie();
        System.out.println("Game tied to ensure ongoing gets set to false and money is not modified.");
        game.winBlackjack();
        String  expectedOutput = String.format("$%.2f", bet + 0.50);
        String actualOutput = game.getMoney();
        assert(actualOutput).equals(expectedOutput);
        assert(!game.isOngoing());
    }

    @Test
    void winBlackjack208() {
        try {
            double bet = Double.MAX_VALUE + 1.0;
            game = new Game(bet);
            game.winBet();
            System.out.println("Game won to set money to max value + 1");
            game.dealAgain(1.0);
            System.out.println("dealAgain called to reset the bet amount to 1.0");
            game.winBlackjack();
            // Expected to fail before this point
            fail();
        } catch (Exception e) {
            assertNotNull(e);
        }
    }

    @Test
    void winBlackjack209() {
        try {
            double bet = Double.MAX_VALUE;
            game = new Game(bet);
            game.winBet();
            System.out.println("Game won to set money to max value");
            game.dealAgain(1.0);
            System.out.println("dealAgain called to reset the bet amount to 1.0");
            game.winBlackjack();
            // Expected to fail before this point
            fail();
        } catch (Exception e) {
            assertNotNull(e);
        }
    }

    @Test
    void winBlackjack210() {
        try {
            double bet = Double.MAX_VALUE - 1;
            game = new Game(bet);
            game.winBet();
            System.out.println("Game won to set money to max value - 1");
            game.dealAgain(1.0);
            System.out.println("dealAgain called to reset the bet amount to 1.0");
            game.winBlackjack();
            // Expected to fail before this point
            fail();
        } catch (Exception e) {
            assertNotNull(e);
        }
    }

    @Test
    void winBlackjack211() {
        try {
            double bet = Double.MIN_VALUE - 1;
            game = new Game(bet);
            game.winBet();
            System.out.println("Game won to set money to min value - 1");
            game.dealAgain(1.0);
            System.out.println("dealAgain called to reset the bet amount to 1.0");
            game.winBlackjack();
            // Expected to fail before this point
            fail();
        } catch (Exception e) {
            assertNotNull(e);
        }
    }

    @Test
    void winBlackjack212() {
        double bet = Double.MIN_VALUE;
        game = new Game(bet);
        game.winBet();
        System.out.println("Game won to set money to min value");
        game.dealAgain(1.0);
        System.out.println("dealAgain called to reset the bet amount to 1.0");
        game.winBlackjack();
        String  expectedOutput = String.format("$%.2f", bet + 1.5);
        String actualOutput = game.getMoney();
        assert(actualOutput).equals(expectedOutput);
        assert(!game.isOngoing());
    }

    @Test
    void winBlackjack213() {
        double bet = Double.MIN_VALUE + 1.0;
        game = new Game(bet);
        game.winBet();
        System.out.println("Game won to set money to min value + 1");
        game.dealAgain(1.0);
        System.out.println("dealAgain called to reset the bet amount to 1.0");
        game.winBlackjack();
        String  expectedOutput = String.format("$%.2f", bet + 1.5);
        String actualOutput = game.getMoney();
        assert(actualOutput).equals(expectedOutput);
        assert(!game.isOngoing());
    }

    @Test
    void loseBet214() {
        double bet = 1000.0;
        game = new Game(bet);
        game.winBet();
        System.out.println("Game won to set money to 1000.0");
        game.dealAgain(100.0);
        System.out.println("dealAgain called to reset the bet amount to 100.0");
        game.loseBet();
        String  expectedOutput = String.format("$%.2f", bet - 100.0);
        String actualOutput = game.getMoney();
        assert(actualOutput).equals(expectedOutput);
        assert(!game.isOngoing());
    }
}
