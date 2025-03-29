package gal.uvigo.esei.aed1.chupatedos.core;

public class Table {

    private int numCardsTable;
    private Card faceUpCard;

    public Table() {
        this.numCardsTable = 0;
        this.faceUpCard = null;
    }

    public boolean isEmpty() {
        return this.numCardsTable == 0;
    }

    public int getNumCardsTable() {
        return numCardsTable;
    }

    public void setFaceUpCard(Card card) {
        this.faceUpCard = card;
    }

    public Card getFaceUpCard() {
        return this.faceUpCard;
    }
}
