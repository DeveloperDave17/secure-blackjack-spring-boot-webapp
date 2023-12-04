package com.example.blackjack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class BlackjackControllerTests {

    @Autowired
    BlackjackController blackjackController;

    @BeforeEach
    public void setup() {}

    Model model;

    @Test
    void dealTC1() {
        String expectedOutput = "redirect:/games/1";
        String output = blackjackController.deal(15);
        assert (output).equals(expectedOutput);
    }

    @Test
    void dealTC4() {
        try {
            blackjackController.deal(-100);
            // Expected to fail before this point
            fail();
        } catch (Exception e) {
            assertNotNull(e);
        }
    }

    @Test
    void gameTC5() {
        blackjackController.deal(15);
        String expectedOutput = "game";
        String output = blackjackController.game(1L, new ConcurrentModel());
        assert(output).equals(expectedOutput);
    }

    @Test
    void gameTC9() {
        try {
            blackjackController.deal(15);
            blackjackController.game(0, new ConcurrentModel());
            // Expected to fail before this point
            fail();
        } catch (Exception e) {
            assertNotNull(e);
        }
    }

    @Test
    void dealAgainTC13() {
        // Create the 37th game
        for (int i = 0; i < 37; i++) {
            blackjackController.deal(1.11);
        }
        try {
            long id = 37;
            double bet = 1.11;
            String expectedOutput = "redirect:/games/37";
            String output = blackjackController.dealAgain(id, bet);
            assert(output).equals(expectedOutput);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void dealAgainTC17() {
        try {
            long id = -52;
            double bet = 1.11;
            blackjackController.dealAgain(id, bet);
            // Expected to fail before this point
            fail();
        } catch (Exception e) {
            assertNotNull(e);
        }
    }

    @Test
    void dealAgainTC20() {
        // Create the 37th game
        for (int i = 0; i < 37; i++) {
            blackjackController.deal(1.11);
        }
        try {
            long id = 37L;
            double bet = -420;
            blackjackController.dealAgain(id, bet);
            // Expected to fail before this point
            fail();
        } catch (Exception e) {
            assertNotNull(e);
        }
    }

    @Test
    void hitTC21() {
        // Create the 42nd game
        for (int i = 0; i < 42; i++) {
            blackjackController.deal(1.11);
        }
        try {
            long id = 42L;
            String expectedOutput = "redirect:/games/42";
            String actualOutput = blackjackController.hit(id);
            assert(actualOutput).equals(expectedOutput);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
    }


}
