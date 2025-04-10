package gal.uvigo.esei.aed1.chupatedos.core;

import es.uvigo.esei.aed1.tads.list.LinkedList;
import es.uvigo.esei.aed1.tads.list.List;


public class Player {

    private String name;
    private List<Card> cards;

    public Player(String name) {
        this.name = name;
        this.cards = new LinkedList<>();
    }

    public String getName() {
        return name;
    }

    public void addCard(Card card) {
        cards.addLast(card);
    }
    
    public List<Card> getPlayableCards(Card cardToPlay){
        List<Card> playableCards = new LinkedList<>();
        
        for(Card crd : cards){
            if (crd.isPlayable(cardToPlay)) {
                playableCards.addLast(crd);
            }
        }
        return playableCards;
    }
    
    public void removeCard(Card card) {
        this.cards.removeValue(card);
    }
    
    public int getNumCards() {
        return this.cards.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(":\n\t");
        
        for(Card crd : cards){
            sb.append(crd.toString());
        }
        
        return sb.toString();
    }

    
}
