package com.example.blackjack;

import com.example.blackjack.models.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;
import org.springframework.web.context.WebApplicationContext;


import java.util.NoSuchElementException;

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

    @Autowired
    WebApplicationContext wac;

    MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    Model model;

    @Test
    void dealTC1() {
        String expectedOutput = "redirect:/games/1";
        String output = blackjackController.deal(15);
        assert(output).equals(expectedOutput);
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
            this.mockMvc.perform(get("/games/21/hit"));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void standTC26() {
        // Create the 70th game
        for (int i = 0; i < 70; i++) {
            blackjackController.deal(1.11);
        }
        try {
            this.mockMvc.perform(get("/games/70/stand"));
        } catch (Exception e) {
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
            blackjackController.game(Long.MAX_VALUE + 1, new ConcurrentModel());
            // Expected to fail before this point
            fail();
        } catch (Exception e) {
            assertNotNull(e);
        }
    }

    @Test
    void gameTC51() {
        for (int i = 0; i < 1_000_000; i++) {
            blackjackController.deal(15);
        }
        String expectedOutput = "game";
        String output = blackjackController.game(1_000_000L, new ConcurrentModel());
        assert(output).equals(expectedOutput);
    }

    @Test
    void gameTC52() {
        for (int i = 0; i < 1_000_000 - 1; i++) {
            blackjackController.deal(15);
        }
        String expectedOutput = "game";
        String output = blackjackController.game(1_000_000 - 1, new ConcurrentModel());
        assert(output).equals(expectedOutput);
    }

    @Test
    void gameTC53() {
        try {
            blackjackController.deal(15);
            blackjackController.game(0L, new ConcurrentModel());
            // Expected to fail before this point
            fail();
        } catch (Exception e) {
            assertNotNull(e);
        }
    }

    @Test
    void gameTC54() {
        blackjackController.deal(15);
        String expectedOutput = "game";
        String output = blackjackController.game(1L, new ConcurrentModel());
        assert(output).equals(expectedOutput);
    }

    @Test
    void gameTC55() {
        blackjackController.deal(15);
        blackjackController.deal(15);
        String expectedOutput = "game";
        String output = blackjackController.game(2L, new ConcurrentModel());
        assert(output).equals(expectedOutput);
    }

    @Test
    void dealAgainTC56() {
        try {
            blackjackController.deal(15);
            blackjackController.dealAgain(Long.MAX_VALUE + 1, 1.11);
            // Expected to fail before this point
            fail();
        } catch (Exception e) {
            assertNotNull(e);
        }
    }

    @Test
    void dealAgainTC57() {
        for (int i = 0; i < 1_000_000; i++) {
            blackjackController.deal(15);
        }
        String expectedOutput = "redirect:/games/" + 1_000_000;
        String output = blackjackController.dealAgain(1_000_000, 1.11);
        assert(output).equals(expectedOutput);
    }

    @Test
    void dealAgainTC58() {
        for (int i = 0; i < 1_000_000 - 1; i++) {
            blackjackController.deal(15);
        }
        String expectedOutput = "redirect:/games/" + (1_000_000 - 1);
        String output = blackjackController.dealAgain(1_000_000 - 1, 1.11);
        assert(output).equals(expectedOutput);
    }

    @Test
    void dealAgainTC59() {
        try {
            blackjackController.deal(15);
            blackjackController.dealAgain(0L, 1.11);
            // Expected to fail before this point
            fail();
        } catch (Exception e) {
            assertNotNull(e);
        }
    }

    @Test
    void dealAgainTC60() {
        blackjackController.deal(15);
        String expectedOutput = "redirect:/games/" + 1L;
        String output = blackjackController.dealAgain(1L, 1.11);
        assert(output).equals(expectedOutput);
    }

    @Test
    void dealAgainTC61() {
        blackjackController.deal(15);
        blackjackController.deal(15);
        String expectedOutput = "redirect:/games/" + 2L;
        String output = blackjackController.dealAgain(2L, 1.11);
        assert(output).equals(expectedOutput);
    }

    @Test
    void dealAgainTC62() {
        try {
            for (int i = 0; i < 37 - 1; i++) {
                blackjackController.deal(15);
            }
            blackjackController.dealAgain(37L, Double.MAX_VALUE + 1);
            // Expected to fail before this point
            fail();
        } catch (Exception e) {
            assertNotNull(e);
        }
    }

    @Test
    void dealAgainTC63() {
        for (int i = 0; i < 37 - 1; i++) {
            blackjackController.deal(15);
        }
        String expectedOutput = "redirect:/games/" + 37L;
        String output = blackjackController.dealAgain(37L, Double.MAX_VALUE);
        assert(output).equals(expectedOutput);
    }

    @Test
    void dealAgainTC64() {
        for (int i = 0; i < 37 - 1; i++) {
            blackjackController.deal(15);
        }
        String expectedOutput = "redirect:/games/" + 37L;
        String output = blackjackController.dealAgain(37L, Double.MAX_VALUE - 1);
        assert(output).equals(expectedOutput);
    }

    @Test
    void dealAgainTC65() {
        try {
            for (int i = 0; i < 37 - 1; i++) {
                blackjackController.deal(15);
            }
            blackjackController.dealAgain(37L, 0.0);
            // Expected to fail before this point
            fail();
        } catch (Exception e) {
            assertNotNull(e);
        }
    }

    @Test
    void dealAgainTC66() {
        for (int i = 0; i < 37 - 1; i++) {
            blackjackController.deal(15);
        }
        String expectedOutput = "redirect:/games/" + 37L;
        String output = blackjackController.dealAgain(37L, 0.00001);
        assert(output).equals(expectedOutput);
    }

    @Test
    void dealAgainTC67() {
        for (int i = 0; i < 37 - 1; i++) {
            blackjackController.deal(15);
        }
        String expectedOutput = "redirect:/games/" + 37L;
        String output = blackjackController.dealAgain(37L, 1.0);
        assert(output).equals(expectedOutput);
    }

    @Test
    void hitTC68() {
        for (int i = 0; i < 1_000_000; i++) {
            blackjackController.deal(15);
        }
        try {
            this.mockMvc.perform(get("/games/" + (Long.MAX_VALUE + 1) + "/hit"));
            fail();
        } catch (Exception e) {
            assertNotNull(e);
        }
    }

    @Test
    void hitTC69() {
        for (int i = 0; i < 1_000_000; i++) {
            blackjackController.deal(15);
        }
        try {
            this.mockMvc.perform(get("/games/" + 1_000_000 + "/hit"));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void hitTC70() {
        for (int i = 0; i < 1_000_000 - 1; i++) {
            blackjackController.deal(15);
        }
        try {
            this.mockMvc.perform(get("/games/" + (1_000_000 - 1) + "/hit"));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void hitTC71() {
        blackjackController.deal(15);
        try {
            this.mockMvc.perform(get("/games/" + 0L + "/hit"));
            fail();
        } catch (Exception e) {
            assertNotNull(e);
        }
    }

    @Test
    void hitTC72() {
        blackjackController.deal(15);
        try {
            this.mockMvc.perform(get("/games/" + 1L + "/hit"));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void hitTC73() {
        blackjackController.deal(15);
        blackjackController.deal(15);
        try {
            this.mockMvc.perform(get("/games/" + 2L + "/hit"));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void standTC74() {
        for (int i = 0; i < 1_000_000; i++) {
            blackjackController.deal(15);
        }
        try {
            this.mockMvc.perform(get("/games/" + (Long.MAX_VALUE + 1) + "/stand"));
            fail();
        } catch (Exception e) {
            assertNotNull(e);
        }
    }

    @Test
    void standTC75() {
        for (int i = 0; i < 1_000_000; i++) {
            blackjackController.deal(15);
        }
        try {
            this.mockMvc.perform(get("/games/" + 1_000_000 + "/stand"));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void standTC76() {
        for (int i = 0; i < 1_000_000 - 1; i++) {
            blackjackController.deal(15);
        }
        try {
            this.mockMvc.perform(get("/games/" + (1_000_000 - 1) + "/stand"));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void standTC77() {
        blackjackController.deal(15);
        try {
            this.mockMvc.perform(get("/games/" + 0L + "/stand"));
            fail();
        } catch (Exception e) {
            assertNotNull(e);
        }
    }

    @Test
    void standTC78() {
        blackjackController.deal(15);
        try {
            this.mockMvc.perform(get("/games/" + 1L + "/stand"));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void standTC79() {
        blackjackController.deal(15);
        blackjackController.deal(15);
        try {
            this.mockMvc.perform(get("/games/" + 2L + "/stand"));
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Due to the random nature of the method deal in BlackjackController the closest we can get to path coverage is
     * to run the test multiple times in hopes of hitting these different cases. The rarest case is a blackjack which
     * happens roughly 4.83% of the time or 1/20. We are executing the test 250 times to make it highly unlikely a
     * blackjack doesn't occur.
     */
    @RepeatedTest(250)
    void dealTC86ThroughTC93() {
        double bet = 20.0;
        // Create the 10th game
        for (int i = 0; i < 10; i++) {
            blackjackController.deal(bet);
        }
        String expectedOutput = "redirect:/games/11";
        String actualOutput = blackjackController.deal(bet);
        assert(actualOutput).equals(expectedOutput);
    }

    @Test
    void gameTC94() {
        gameTC5();
    }

    @Test
    void gameTC95() {
        gameTC5();
    }

    @Test
    void dealAgainTC96() {
        dealAgainTC13();
    }

    @Test
    void dealAgainTC97() {
        dealAgainTC13();
    }

    /**
     * For Test cases TC98 through TC101 due to the random nature of the method hit in BlackjackController the
     * closest we can get to path coverage is to run the test multiple times in hopes of hitting these different cases.
     * The rarest case is a hit not busting which should happen just under 50% of the time. To be sure we hit our two
     * cases here we are executing the tests 20 times.
     */
    @RepeatedTest(20)
    void hitTC98ThroughTC101() {
        // Create the 17th game
        for (int i = 0; i < 17; i++) {
            blackjackController.deal(20.0);
        }
        try {
            // id is 17
            this.mockMvc.perform(get("/games/17/hit"));
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * For Test cases TC102 through TC108 due to the random nature of the method stand in BlackjackController the
     * closest we can get to path coverage is to run the test multiple times in hopes of hitting these different cases.
     * The rarest case is a tie which should happen just under 9% of the time. To be sure we hit our two
     * cases here we are executing the tests 60 times.
     */
    @RepeatedTest(60)
    void standTC102ThroughTC108() {
        // Create the 21st game
        for (int i = 0; i < 21; i++) {
            blackjackController.deal(20.0);
        }
        try {
            // id is 21
            this.mockMvc.perform(get("/games/21/stand"));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void gameTC154() {
        gameTC94();
    }

    @Test
    void dealAgainTC155() {
        dealAgainTC96();
    }

    @Test
    void gameTC171() {
        double bet = 100.0;
        blackjackController.deal(bet);
        String expectedOutput = "game";
        long id = 1L;
        String output = blackjackController.game(id, new ConcurrentModel());
        assert(output).equals(expectedOutput);
    }

    @Test
    void gameTC172() {
        long id = 1L;
        try {
            blackjackController.game(id, new ConcurrentModel());
            // method should not execute without throwing an NoSuchElementException
            fail();
        } catch (NoSuchElementException e) {
            assertNotNull(e);
        }
    }

    @Test
    void gameTC173() {
        gameTC94();
    }

    @Test
    void gameTC174() {
        gameTC172();
    }

    @Test
    void dealAgainTC175() {
        double bet = 100.0;
        blackjackController.deal(bet);
        String expectedOutput = "redirect:/games/1";
        long id = 1L;
        String output = blackjackController.dealAgain(id, bet);
        assert(output).equals(expectedOutput);
    }

    @Test
    void dealAgainTC176() {
        double bet = 100.0;
        long id = 1L;
        try {
            blackjackController.dealAgain(id, bet);
            // Method shouldn't execute without throwing a NoSuchElementException
            fail();
        } catch (NoSuchElementException e) {
            assertNotNull(e);
        }
    }

    @Test
    void dealAgainTC177() {
        dealAgainTC97();
    }

    @Test
    void dealAgainTC178() {
        double bet = 100.0;
        long id = 37L;
        try {
            blackjackController.dealAgain(id, bet);
            // Method shouldn't execute without throwing a NoSuchElementException
            fail();
        } catch (NoSuchElementException e) {
            assertNotNull(e);
        }
    }

    @Test
    void hitTC179() {
        double bet = 100.0;
        blackjackController.deal(bet);
        try {
            this.mockMvc.perform(get("/games/1/hit"));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void hitTC180() {
        long id = 1L;
        try {
            blackjackController.hit(id);
            // Method shouldn't execute without throwing a NoSuchElementException
            fail();
        } catch (NoSuchElementException e) {
            assertNotNull(e);
        }
    }

    @RepeatedTest(30)
    void hitTC181() {
        double bet = 100.0;
        for (int i = 0; i < 17; i++) {
            blackjackController.deal(bet);
        }
        try {
            this.mockMvc.perform(get("/games/17/hit"));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void hitTC182() {
        long id = 17L;
        try {
            blackjackController.hit(id);
            // Method shouldn't execute without throwing a NoSuchElementException
            fail();
        } catch (NoSuchElementException e) {
            assertNotNull(e);
        }
    }

    @Test
    void standTC183() {
        double bet = 100.0;
        blackjackController.deal(bet);
        try {
            this.mockMvc.perform(get("/games/1/stand"));
        } catch (Exception e) {
            fail();
        }
    }

    @RepeatedTest(30)
    void standTC185() {
        double bet = 100.0;
        blackjackController.deal(bet);
        try {
            this.mockMvc.perform(get("/games/1/stand"));
        } catch (Exception e) {
            fail();
        }
    }

}
