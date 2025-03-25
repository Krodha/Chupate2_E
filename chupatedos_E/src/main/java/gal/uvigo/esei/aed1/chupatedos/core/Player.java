package gal.uvigo.esei.aed1.chupatedos.core;

import java.util.Stack;



public class Player {
    private String nombre;
    private Stack <Card> cards;
    
 
    public Player(String nombre){
        this.nombre = nombre;
        this.cards = new LinkedStack<>();
    }

    public Stack<Card> getCards() {
        return cards;
    }
    
    public void addCard(Card card){
        if(cards.size() >= 7){
            throw new IllegalArgumentException("The stack is full. Can't add more cards");
        } 
        cards.push(card);
    }
    
    
}
