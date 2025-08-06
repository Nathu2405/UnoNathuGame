package com.uno.uno_backend;

import java.util.*;

public class Deck {
    private final Stack<Card> cards = new Stack<>();

    public Deck() {
        List<Card> tempCards = new ArrayList<>();

        for (Card.Color color : Card.Color.values()) {
            for(Card.Value value : Card.Value.values()) {
                if(value == Card.Value.WILD) continue;

                tempCards.add(new Card(color, value));
                tempCards.add(new Card(color, value));
            }
        }

        //for the wild cards
        for(int i = 0; i < 4; i++){
            tempCards.add(new Card(null, Card.Value.WILD));
        }

        Collections.shuffle(tempCards);
        cards.addAll(tempCards);
    }

    public Card draw() {
        return cards.isEmpty() ? null : cards.pop();
    }

    public boolean isEmpty() { return cards.isEmpty(); }

    public void shuffle() { Collections.shuffle(cards); }
}
