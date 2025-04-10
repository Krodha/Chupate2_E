package gal.uvigo.esei.aed1.chupatedos.core;

import es.uvigo.esei.aed1.tads.stack.LinkedStack;
import es.uvigo.esei.aed1.tads.stack.Stack;

public class Table {
    private Stack<Card> cards;

    public Table() {
        this.cards = new LinkedStack<>();
    }

    public boolean isEmpty() {
        return this.cards.size() == 0;
    }

    public int getNumCardsTable() {
        return this.cards.size();
    }

    public void pushCard(Card card) {
        this.cards.push(card);
    }
    
    public Card popCard() {
        return this.cards.pop();
    }

    public Card getFaceUpCard() {
        return this.cards.top();
    }
}
