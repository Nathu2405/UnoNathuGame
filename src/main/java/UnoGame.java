package com.uno.uno_backend;

import java.util.*;

public class UnoGame {
    private Deck deck;
    private Card topCard;
    private List<Player> players;
    private int currentPlayerIndex;
    private boolean isReversed, gameOver;
    private String winnerName;

    public UnoGame() {
        deck = new Deck();
        deck.shuffle();

        players = new ArrayList<>();
        players.add(new Player("Player 1"));
        players.add(new Player("Player 2"));

        currentPlayerIndex = 0;
        isReversed = false;
        gameOver = false;
        winnerName = null;

        for(Player player: players) {
            for(int i = 0; i < 7; i++){
                player.drawCard(deck.draw());
            }
        }

        topCard = deck.draw();
    }

    public Player getCurrentPlayer() { return players.get(currentPlayerIndex); }

    public Card getTopCard() { return topCard; }

    public boolean playCard(Card card) {
        if(gameOver) return false;

        Player current = getCurrentPlayer();

        if(!current.getHand().contains(card)) {
            System.out.println("Card not in hand!");
            return false;
        }

        if(!isPlayable(card, topCard)) {
            System.out.println("Card not playable on top card: " + topCard);
            return false;
        }


        boolean removed = current.playCard(card);

        if(!removed) return false;

        topCard = card;

        if(card.getValue() == Card.Value.SKIP || card.getValue() == Card.Value.REVERSE) {
            nextTurn(); // Since this is two-player, skip and reverse mean the same
        }

        if(current.getHand().isEmpty()) {
            gameOver = true;
            winnerName = current.getName();
        }

        nextTurn();
        return true;
    }

    public void drawCardForCurrentPlayer() {
        if(gameOver) return;

        Card drawn = deck.draw();
        getCurrentPlayer().drawCard(drawn);
    }

    private boolean isPlayable(Card newCard, Card topCard) {
        return newCard.getColor() == topCard.getColor() || newCard.getValue() == topCard.getValue() || newCard.getValue() == Card.Value.WILD;
    }

    public List<Card> getCurrentPlayerHand() {
        return getCurrentPlayer().getHand();
    }

    public void nextTurn() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();

    }

    public boolean isGameOver() {
        return gameOver;
    }

    public String getWinnerName() {
        return winnerName;
    }

    public String getCurrentPlayerName() {
        return getCurrentPlayer().getName();
    }

}
