package gal.uvigo.esei.aed1.chupatedos.core;

import java.util.Stack;



public class DeckOfCards {
    private Stack<Card> stack;
    
    public void shuffle() {
        Card[] cards = new Card[this.stack.size()];
        int index = 0;
        
        while (!this.stack.isEmpty()) {
            cards[index++] = this.stack.pop();
        }
        
        for (int i = 0; i < cards.length; i++) {
            cards[i] = cards[(int)Math.random() * cards.length - 1];
        }
        
        for (int i = 0; i < cards.length; i++) {
            this.stack.push(cards[i]);
            
        }
    }


}
