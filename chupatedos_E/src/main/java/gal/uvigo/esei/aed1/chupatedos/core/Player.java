package gal.uvigo.esei.aed1.chupatedos.core;

import es.uvigo.esei.aed1.tads.stack.Stack;
import es.uvigo.esei.aed1.tads.stack.LinkedStack;//CAMBIO

public class Player {

    private String name;
    private Card[] cards;
    private int numCards;

    public Player(String name) {
        this.name = name;
        this.cards = new Card[7];
        this.numCards = 0;
    }

    public String getName() {
        return name;
    }

    public void addCard(Card card) {
        if (this.numCards >= 7) {
            throw new IllegalArgumentException("The array is full. Can't add more cards");
        }
        
        cards[this.numCards++] = card;
    }

    public String showCards() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < this.numCards; i++) {
            sb.append(this.cards[i].toString()).append(", ");
        }

        return sb.toString();
    }

}
