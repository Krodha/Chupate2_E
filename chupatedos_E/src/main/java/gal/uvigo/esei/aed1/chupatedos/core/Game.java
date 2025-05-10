package gal.uvigo.esei.aed1.chupatedos.core;

import gal.uvigo.esei.aed1.chupatedos.iu.IU;

public class Game {

    private final IU iu;
    private Player[] players;
    private DeckOfCards deckOfCards;
    private Table table;
    private int activePlayer;
    private boolean isPreviousCardTwo;
    private boolean isClockWise;

    public Game(IU iu) {
        this.iu = iu;
        this.deckOfCards = new DeckOfCards();
        this.table = new Table();
        this.players = this.createPlayers();
        this.activePlayer = 0;
        this.isPreviousCardTwo = false;
        this.isClockWise = false;
    }

    /**
     * Metodo principal para jugar
     */
    public void play() {
        this.deckOfCards.shuffle();
        shareCards();
        this.table.pushCard(this.deckOfCards.popCard());
        
        if (this.table.getFaceUpCard().getNumber() == 2) {
            this.iu.displayMessage("Vaya! Hoy no es tu dia de suerte " + this.players[this.activePlayer].getName() + ". Chupate DOS!");
        } else if (this.table.getFaceUpCard().getNumber() == 7) {
            this.iu.displayMessage("Vaya! Hoy no es tu dia de suerte " + this.players[this.activePlayer].getName() + ". Perdiste el turno y se cambia el sentido!");
            this.isClockWise = !this.isClockWise;
            this.activePlayer = this.getNextPlayer();
        }

        while (this.getWinner() == -1) {
            this.showGameState();
            this.playersTurn();
            this.activePlayer = this.getNextPlayer();
        }

        this.iu.displayMessage("El ganador es " + this.players[this.getWinner()].getName() + "!");
    }

    private void restartDeckOfCards() {
        Card faceUpCard = this.table.popCard();

        while (!this.table.isEmpty()) {
            this.deckOfCards.addCard(this.table.popCard());
        }

        this.table.pushCard(faceUpCard);
        this.deckOfCards.shuffle();
    }

    private int getWinner() {
        for (int i = 0; i < this.players.length; i++) {
            if (this.players[i].getNumCards() == 0) {
                return i;
            }
        }

        return -1;
    }

    private void playersTurn() {
        Player currentPlayer = this.players[this.activePlayer];
        Card tableTopCard = this.table.getFaceUpCard();

        if (!this.isPreviousCardTwo && tableTopCard.getNumber() == 2) {
            currentPlayer.addCard(this.drawCard());
            currentPlayer.addCard(this.drawCard());
            this.isPreviousCardTwo = true;
            this.iu.displayMessage("Turno de " + currentPlayer.toString());
        } else if (currentPlayer.getPlayableCards(tableTopCard).size() > 0) {
            this.iu.displayMessage("Turno de " + currentPlayer.toString());
            Card selectedCard = this.iu.getSelectedCard(currentPlayer, tableTopCard);

            this.table.pushCard(selectedCard);
            currentPlayer.removeCard(selectedCard);
            
            if (selectedCard.getNumber() == 2) {
                this.iu.displayMessage("El jugador " + currentPlayer.getName() + " obligo a " + this.players[this.getNextPlayer()].getName() + " a comerse un Chupate DOS!");
            } else if (selectedCard.getNumber() == 7) {
                this.iu.displayMessage("Cambio de sentido!");
                this.isClockWise = !this.isClockWise;
            }
            
            if (this.isPreviousCardTwo) this.isPreviousCardTwo = false;
        } else {
            this.iu.displayMessage("Turno de " + currentPlayer.toString());
            Card newCard = this.drawCard();
            
            this.iu.displayMessage("No tienes cartas jugables. Has cogido la carta " + newCard);

            if (newCard.isPlayable(tableTopCard)) {
                this.iu.displayMessage("Se ha jugado la carta");
                this.table.pushCard(newCard);
                
                if (newCard.getNumber() == 2) {
                    this.iu.displayMessage("El jugador " + currentPlayer.getName() + " obligo a " + this.players[this.getNextPlayer()].getName() + " a comerse un Chupate DOS!");
                } else if (newCard.getNumber() == 7) {
                    this.iu.displayMessage("Cambio de sentido!");
                    this.isClockWise = !this.isClockWise;
                }
                
                if (this.isPreviousCardTwo) this.isPreviousCardTwo = false;
            } else {
                this.iu.displayMessage("No se ha podido jugar la carta");
                currentPlayer.addCard(newCard);
            }
        }
    }
    
    private Card drawCard() {
        if (this.deckOfCards.getSize() == 0) {
            this.restartDeckOfCards();
        }
            
        return this.deckOfCards.popCard();
    }

    private Player[] createPlayers() {
        String[] names = iu.getPlayersData();

        Player[] players = new Player[names.length];

        for (int i = 0; i < names.length; i++) {
            players[i] = new Player(names[i]);
        }

        return players;
    }

    public void showGameState() {
        iu.displayMessage("=============================================================> ESTADO DEL JUEGO <=====================================================");
        iu.displayMessage("Carta sobre la mesa: " + this.table.getFaceUpCard());
        iu.displayMessage("Numero de cartas boca arriba: " + table.getNumCardsTable());
        iu.displayMessage("Cartas restantes sin repartir: " + deckOfCards.getSize());
        iu.displayMessage("Cartas de los jugadores:\n" + playersHand());
        iu.displayMessage("======================================================================================================================================");
    }

    public void shareCards() {
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
        if (!this.isClockWise) {
            if (this.activePlayer == 0) {
                return this.players.length - 1;
            }

            return this.activePlayer - 1;
        } else {
            if (this.activePlayer == this.players.length - 1) {
                return 0;
            }
            
            return this.activePlayer + 1;
        }
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
