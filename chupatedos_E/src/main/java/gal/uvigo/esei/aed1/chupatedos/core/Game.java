package gal.uvigo.esei.aed1.chupatedos.core;

import gal.uvigo.esei.aed1.chupatedos.iu.IU;

public class Game {

    private final IU iu;
    private int numOfPlayers;
    private Player[] players;
    private DeckOfCards deckOfCards;
    private Table table;

    public Game(IU iu) {
        this.iu = iu;
        this.numOfPlayers = 0;
        this.deckOfCards = new DeckOfCards();
        this.table = new Table();
        this.players = this.createPlayers();
    }

    /**
     * Metodo principal para jugar
     */
    public void play() {
        this.deckOfCards.shuffle();
        shareCards();
        this.table.pushCard(this.deckOfCards.popCard());
        showGameState();
    }
    
    public Player[] createPlayers() {
        String[] names = iu.getPlayersData();
        this.numOfPlayers = names.length;
        
        Player[] players = new Player[this.numOfPlayers];
        
        for (int i = 0; i < this.numOfPlayers; i++) {
            this.players[i] = new Player(names[i]);
        }
        
        return players;
    }
    
    public void showGameState(){
        iu.displayMessage("Carta sobre la mesa: " + this.table.getFaceUpCard());
        iu.displayMessage("Número de cartas boca arriba: " + table.getNumCardsTable());
        iu.displayMessage("Cartas restantes sin repartir: " + deckOfCards.getSize());
        iu.displayMessage("Cartas de los jugadores:\n" + playersHand());
    }

    public void shareCards(){
        for (int i = 0; i < this.numOfPlayers; i++) {
            for (int j = 0; j < 7; j++) {
                this.players[i].addCard(this.deckOfCards.popCard());
            }
        }
    }
    
    public int getNumOfPlayers() {
        return numOfPlayers;
    }

    public Player getPlayer(int pos) {
        return players[pos];
    }

    public String playersHand() {

        StringBuilder cadena = new StringBuilder();

        for (int i = 0; numOfPlayers > i; i++) {

            cadena.append(getPlayer(i).getName())
                    .append(": ")
                    .append(getPlayer(i).showCards())
                    .append("\n");
        }

        return cadena.toString();
    }

}
