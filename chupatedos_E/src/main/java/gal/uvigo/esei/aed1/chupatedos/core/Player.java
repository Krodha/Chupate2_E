package gal.uvigo.esei.aed1.chupatedos.core;

import java.util.Stack;
import es.uvigo.esei.aed1.tads.stack.LinkedStack;//CAMBIO



public class Player {
    private String name;
    private Stack <Card> cards;
    
    
 
    public Player(String name){
        this.name = name;
        this.cards = new LinkedStack<>();
    }

    public Stack<Card> getCards() {
        return cards;
    }

    public String getName() {
        return name;
    }
    
    
    public void addCard(Card card){
        if(cards.size() >= 7){
            throw new IllegalArgumentException("The stack is full. Can't add more cards");
        } 
        cards.push(card);
    }
    
    
}
