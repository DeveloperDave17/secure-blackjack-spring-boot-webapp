package com.example.blackjack.models;

import jakarta.persistence.*;
import java.util.Random;

@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id; // JPA ID
    private double bet; // Bet amount
    private double money; // Player's money
    private boolean ongoing; // Game status
    private String message; // Message to display at end of game
    @OneToOne(cascade = CascadeType.ALL)
    private Deck deck; // Deck of cards
    @OneToOne(cascade = CascadeType.ALL)
    private Hand playerHand; // Player's hand
    @OneToOne(cascade = CascadeType.ALL)
    private Hand dealerHand; // Dealer's hand

    /**
     * Creates a game. This constructor is only used by JPA.
     */
    protected Game() {}

    /**
     * Creates a game with the given bet.
     * @param bet the amount of money to bet
     */
    public Game(double bet) {
        this.bet = bet;
        this.money = 0D;
        this.ongoing = true;
        this.deck = new Deck();
        this.playerHand = new Hand();
        this.dealerHand = new Hand();

        deck.dealFaceUp(playerHand);
        deck.dealFaceUp(dealerHand);
        deck.dealFaceUp(playerHand);
        deck.dealFaceDown(dealerHand);
    }

    /**
     * Gets the id of the game.
     * @return the id of the game
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the id of the game.
     * @param id the id of the game
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets the player's money.
     * @return the player's money
     */
    public String getMoney() {
        return (money >= 0 ? "$" : "-$") + String.format("%.2f", Math.abs(money));
    }

    /**
     * Gets the deck of cards.
     * @return the deck of cards
     */
    public Deck getDeck() {
        return deck;
    }

    /**
     * Gets the player's hand.
     * @return the player's hand
     */
    public Hand getPlayerHand() {
        return playerHand;
    }

    /**
     * Gets the dealer's hand.
     * @return the dealer's hand
     */
    public Hand getDealerHand() {
        return dealerHand;
    }

    /**
     * Gets the current message.
     * @return the current message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the current message.
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Adds the bet to the player's money.
     */
    public void winBet() {
        money += bet;
        ongoing = false;
    }

    /**
     * Adds the bet plus half the bet to the player's money.
     */
    public void winBlackjack() {
        money += bet * 1.5;
        ongoing = false;
    }

    /**
     * Subtracts the bet from the player's money.
     */
    public void loseBet() {
        money -= bet;
        ongoing = false;
    }

    /**
     * Sets the ongoing status to false.
     */
    public void tie() {
        ongoing = false;
    }

    /**
     * Checks if the game is ongoing.
     * @return true if the game is ongoing, false otherwise
     */
    public boolean isOngoing() {
        return ongoing;
    }

    /**
     * Deals another game.
     * @param bet the amount of money to bet
     * @return the game
     */
    public Game dealAgain(double bet) {
        this.bet = bet;
        ongoing = true;

        deck = new Deck();
        playerHand = new Hand();
        dealerHand = new Hand();

        deck.dealFaceUp(playerHand);
        deck.dealFaceUp(dealerHand);
        deck.dealFaceUp(playerHand);
        deck.dealFaceDown(dealerHand);

        return this;
    }
}
