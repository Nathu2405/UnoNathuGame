package com.uno.uno_backend;

import java.util.*;

public class Player {
    private final String name;
    private final List<Card> hand = new ArrayList<>();

    public Player(String name){
        this.name = name;
    }

    public List<Card> getHand() {
        return hand;
    }

    public void drawCard(Card card) {
        if (card != null) hand.add(card);
    }

    public String getName(){ return name; }

    public boolean playCard(Card card) {
        return hand.remove(card);
    }
}
