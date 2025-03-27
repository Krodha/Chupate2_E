package gal.uvigo.esei.aed1.chupatedos.core;

import es.uvigo.esei.aed1.tads.stack.LinkedStack;
import es.uvigo.esei.aed1.tads.stack.Stack;

public class DeckOfCards {

    Stack<Card> deckOfCards = new LinkedStack<>();

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
//            int j = (int)(Math.random() * cards.length - 1); CAMBIO:
//Si ponemos entre 0 y cards.length, se genera valor entre 0 y cartas.length-1 automáticamente, el segundo
//número ya no viene incluído.
              int j = (int)(Math.random() * cards.length);
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

}
