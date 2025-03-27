package gal.uvigo.esei.aed1.chupatedos.core;

import es.uvigo.esei.aed1.tads.stack.Stack;
import es.uvigo.esei.aed1.tads.stack.LinkedStack;//CAMBIO

public class Player {

    private String name;
    private Stack<Card> cards;

    public Player(String name) {
        this.name = name;
        this.cards = new LinkedStack<>();
    }

    public Stack<Card> getCards() {
        return cards;
    }

    public String getName() {
        return name;
    }

    public void addCard(Card card) {
        if (cards.size() >= 7) {
            throw new IllegalArgumentException("The stack is full. Can't add more cards");
        }
        cards.push(card);
    }

    public String showCards() {
        Stack<Card> aux = new LinkedStack<>();
        StringBuilder sb = new StringBuilder();

        while (!cards.isEmpty()) {
            aux.push(cards.pop());
        }

        while (!aux.isEmpty()) {
            sb.append(aux.top().toString()).append(", ");
            cards.push(aux.pop());
        }

        return sb.toString();
    }

}
