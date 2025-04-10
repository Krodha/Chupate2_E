package gal.uvigo.esei.aed1.chupatedos.core;

import gal.uvigo.esei.aed1.chupatedos.iu.IU;

public class Game {

    private final IU iu;
    private Player[] players;
    private DeckOfCards deckOfCards;
    private Table table;
    private int activePlayer;

    public Game(IU iu) {
        this.iu = iu;
        this.deckOfCards = new DeckOfCards();
        this.table = new Table();
        this.players = this.createPlayers();
        this.activePlayer = 0;
    }

    /**
     * Metodo principal para jugar
     */
    public void play() {
        this.deckOfCards.shuffle();
        shareCards();
        this.table.pushCard(this.deckOfCards.popCard());
        
        while (this.getWinner() == -1) {
            this.showGameState();
            this.playersTurn();
            this.activePlayer = this.getNextPlayer();
        }
        
        this.iu.displayMessage("El ganador es " + this.players[this.getWinner()].getName() + "!");
    }
    
    private int getWinner() {
        for (int i = 0; i < this.players.length; i++) {
            if (this.players[i].getNumCards() == 0) return i;
        }
        
        return -1;
    }
    
    private void playersTurn() {
        Player currentPlayer = this.players[this.activePlayer];
        Card tableTopCard = this.table.getFaceUpCard();
        
        this.iu.displayMessage(currentPlayer.toString());
        
        if (currentPlayer.getPlayableCards(tableTopCard).size() > 0) {
            Card selectedCard = this.iu.getSelectedCard(currentPlayer, tableTopCard);
            
            this.table.pushCard(selectedCard);
            currentPlayer.removeCard(selectedCard);
        } else {
            Card newCard = this.deckOfCards.popCard();
            
            if (newCard.isPlayable(tableTopCard)) {
                this.table.pushCard(newCard);
            } else {
                currentPlayer.addCard(newCard);
            }
        }
        
        if (this.deckOfCards.getSize() == 0) {
            Card faceUpCard = this.table.popCard();
            
            while (!this.table.isEmpty()) {
                this.deckOfCards.addCard(this.table.popCard());
            }
            
            this.table.pushCard(faceUpCard);
            this.deckOfCards.shuffle();
        }
    }
    
    public Player[] createPlayers() {
        String[] names = iu.getPlayersData();
        
        Player[] players = new Player[names.length];
        
        for (int i = 0; i < names.length; i++) {
            players[i] = new Player(names[i]);
        }
        
        return players;
    }
    
    public void showGameState(){
        iu.displayMessage("=============================================================> ESTADO DEL JUEGO <=====================================================");
        iu.displayMessage("Carta sobre la mesa: " + this.table.getFaceUpCard());
        iu.displayMessage("Numero de cartas boca arriba: " + table.getNumCardsTable());
        iu.displayMessage("Cartas restantes sin repartir: " + deckOfCards.getSize());
        iu.displayMessage("Cartas de los jugadores:\n" + playersHand());
        iu.displayMessage("======================================================================================================================================");
    }

    public void shareCards(){
        for (int i = 0; i < players.length; i++) {
            for (int j = 0; j < 7; j++) {
                this.players[i].addCard(this.deckOfCards.popCard());
            }
        }
    }
    
    
    public int getNumOfPlayers() {
        return players.length;
    }

    public Player getPlayer(int pos) {
        return players[pos];
    }
    
    private int getNextPlayer() {
        if (this.activePlayer == 0) {
            return this.players.length - 1;
        }
        
        return this.activePlayer - 1;
    }

    public String playersHand() {

        StringBuilder cadena = new StringBuilder();

        for (int i = 0; players.length > i; i++) {

            cadena.append(getPlayer(i))
                    .append("\n");
        }

        return cadena.toString();
    }

}
