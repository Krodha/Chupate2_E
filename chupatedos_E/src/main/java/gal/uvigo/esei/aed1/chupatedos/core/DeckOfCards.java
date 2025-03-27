package gal.uvigo.esei.aed1.chupatedos.core;

import es.uvigo.esei.aed1.tads.stack.LinkedStack;
import es.uvigo.esei.aed1.tads.stack.Stack;

public class DeckOfCards {

    private Stack<Card> deckOfCards = new LinkedStack<>();

    public DeckOfCards() {
        for (int i = 0; i < Card.values().length; i++) {
            deckOfCards.push(Card.values()[i]);
        }
    }

    public void shuffle() {
        Card[] cards = new Card[this.deckOfCards.size()];
        int index = 0;

        while (!this.deckOfCards.isEmpty()) {
            cards[index++] = this.deckOfCards.pop();
        }

        for (int i = 0; i < cards.length; i++) {
            Card aux = cards[i];
            int j = (int)Math.round(Math.random() * (cards.length - 1));
            cards[i] = cards[j];
            cards[j] = aux;
        }

        for (int i = 0; i < cards.length; i++) {
            this.deckOfCards.push(cards[i]);

        }
    }

//    public Card popCard(Card card) {
//        return deckOfCards.pop();
//    }
    //CAMBIO: No tiene parámetros.
    
    public Card popCard(){
        return deckOfCards.pop();
    }

    public void addCard(Card newCard) {
        deckOfCards.push(newCard);
    }

    public Card getTopCard() {
        return deckOfCards.top();
    }
    
    public int getSize() {
        return this.deckOfCards.size();
    }

}