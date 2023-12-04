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
    


}
