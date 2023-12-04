package com.example.blackjack;

import com.example.blackjack.models.Game;
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

    @Autowired
    private BlackjackRepository repository; // Repository for games

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
    

    @Test
    void dealTC44() {
        try {
            blackjackController.deal(Double.MAX_VALUE + 1);
            // Expected to fail before this point
            fail();
        } catch (Exception e) {
            assertNotNull(e);
        }
    }

    @Test
    void dealTC45() {
        String expectedOutput = "redirect:/games/1";
        String output = blackjackController.deal(Double.MAX_VALUE);
        assert (output).equals(expectedOutput);
    }

    @Test
    void dealTC46() {
        String expectedOutput = "redirect:/games/1";
        String output = blackjackController.deal(Double.MAX_VALUE - 1);
        assert (output).equals(expectedOutput);
    }

    @Test
    void dealTC47() {
        try {
            blackjackController.deal(0.0D);
            // Expected to fail before this point
            fail();
        } catch (Exception e) {
            assertNotNull(e);
        }
    }

    @Test
    void dealTC48() {
        String expectedOutput = "redirect:/games/1";
        String output = blackjackController.deal(0.00001D);
        assert (output).equals(expectedOutput);
    }

    @Test
    void dealTC49() {
        String expectedOutput = "redirect:/games/1";
        String output = blackjackController.deal(1.0D);
        assert (output).equals(expectedOutput);
    }

    @Test
    void gameTC50() {
        try {
            blackjackController.deal(15);
            Game game = repository.findById(1L).orElseThrow();
            game.setId(Long.MAX_VALUE + 1);
            repository.save(game);
            blackjackController.game(Long.MAX_VALUE + 1, new ConcurrentModel());
            // Expected to fail before this point
            fail();
        } catch (Exception e) {
            assertNotNull(e);
        }
    }

    @Test
    void gameTC51() {
        blackjackController.deal(15);
        Game game = repository.findById(1L).orElseThrow();
        game.setId(Long.MAX_VALUE);
        repository.save(game);
        String expectedOutput = "game";
        String output = blackjackController.game(Long.MAX_VALUE, new ConcurrentModel());
        assert(output).equals(expectedOutput);
    }

    @Test
    void gameTC52() {
        blackjackController.deal(15);
        Game game = repository.findById(1L).orElseThrow();
        game.setId(Long.MAX_VALUE);
        repository.save(game);
        String expectedOutput = "game";
        String output = blackjackController.game(Long.MAX_VALUE - 1, new ConcurrentModel());
        assert(output).equals(expectedOutput);
    }

}
