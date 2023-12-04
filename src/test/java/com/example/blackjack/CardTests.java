package com.example.blackjack;

import com.example.blackjack.models.Card;
import com.example.blackjack.models.Rank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CardTests {

    Card card;

    @BeforeEach
    void setup() {
        card = new Card(Rank.EIGHT, "♣");
    }

    @Test
    void flipTC109() {
        Card expectedCard = card;
        Card actualCard = card.flip();
        assert(actualCard).equals(expectedCard);
    }

    @Test
    void flipTC110() {
        flipTC109();
    }

    @Test
    void toStringTC111() {
        card.flip();
        String suit = "♣";
        String expectedOutput = String.format("""
                ┌───────────┐
                │ %s      %s │
                │           │
                │           │
                │     %s     │
                │           │
                │           │
                │ %s      %s │
                └───────────┘""", card.getRank().rightPad(), suit, suit, suit, card.getRank().leftPad());
        String actualOutput = card.toString();
        assert(actualOutput).equals(expectedOutput);
    }

    @Test
    void toStringTC112() {
        String expectedOutput = """
                ┌───────────┐
                │ ░░░░░░░░░ │
                │ ░░░░░░░░░ │
                │ ░░░░░░░░░ │
                │ ░░░░░░░░░ │
                │ ░░░░░░░░░ │
                │ ░░░░░░░░░ │
                │ ░░░░░░░░░ │
                └───────────┘""";
        String actualOutput = card.toString();
        assert(actualOutput).equals(expectedOutput);
    }
}
