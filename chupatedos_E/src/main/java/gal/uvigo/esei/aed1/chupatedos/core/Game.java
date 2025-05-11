package gal.uvigo.esei.aed1.chupatedos.core;

import gal.uvigo.esei.aed1.chupatedos.iu.IU;

public class Game {

    private final IU iu;
    private Player[] players;
    private DeckOfCards deckOfCards;
    private Table table;
    private int activePlayer;
    private boolean isClockwise;

    public Game(IU iu) {
        this.iu = iu;
        this.deckOfCards = new DeckOfCards();
        this.table = new Table();
        this.players = this.createPlayers();
        this.activePlayer = 0;
        this.isClockwise = false;
    }

    /**
     * Metodo principal para jugar
     */
    public void play() {
        this.deckOfCards.shuffle();
        this.shareCards();
        this.table.pushCard(this.deckOfCards.popCard());
        

        if (this.table.getFaceUpCard().getNumber() == 2) {
            this.iu.displayMessage("\n\t\t\t\tVaya! Hoy no es tu dia de suerte " + this.players[this.activePlayer].getName() + ". Chupate DOS!");
            this.players[this.activePlayer].addCard(this.drawCard());
            this.players[this.activePlayer].addCard(this.drawCard());
            this.activePlayer = this.getNextPlayer();
        } else if (this.table.getFaceUpCard().getNumber() == 7) {
            this.iu.displayMessage("\n\t\t\t\tVaya! Hoy no es tu dia de suerte " + this.players[this.activePlayer].getName() + ". Perdiste el turno y se cambia el sentido!");
            this.isClockwise = !this.isClockwise;
            this.activePlayer = this.getNextPlayer();
        }

        while (this.getWinner() == -1) {
            this.showGameState();
            this.playersTurn();
            this.activePlayer = this.getNextPlayer();
            this.iu.displayMessage("\n\n\n\n");
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

        if (currentPlayer.getPlayableCards(tableTopCard).size() > 0) {
            this.iu.displayMessage("\nTurno de " + currentPlayer.toString());
            Card selectedCard = this.iu.getSelectedCard(currentPlayer, tableTopCard);

            this.table.pushCard(selectedCard);
            currentPlayer.removeCard(selectedCard);

            if (selectedCard.getNumber() == 2) {
                this.iu.displayMessage("\n\t\t\t\tEl jugador " + currentPlayer.getName() + " obligo a " + this.players[this.getNextPlayer()].getName() + " a comerse un Chupate DOS!");
                activePlayer = this.getNextPlayer();
                currentPlayer = this.players[activePlayer];
                currentPlayer.addCard(this.drawCard());
                currentPlayer.addCard(this.drawCard());
            } else if (selectedCard.getNumber() == 7) {
                this.iu.displayMessage("\n\t\t\t\tCambio de sentido!");
                this.isClockwise = !this.isClockwise;
            }
        } else {
            this.iu.displayMessage("Turno de " + currentPlayer.toString());
            Card newCard = this.drawCard();

            this.iu.displayMessage("\n\t\t\t\tNo tienes cartas jugables. Has cogido la carta " + newCard);

            if (newCard.isPlayable(tableTopCard)) {
                this.iu.displayMessage("\n\t\t\t\tSe ha jugado la carta");
                this.table.pushCard(newCard);

                if (newCard.getNumber() == 2) {
                    this.iu.displayMessage("\n\t\t\t\tEl jugador " + currentPlayer.getName() + " obligo a " + this.players[this.getNextPlayer()].getName() + " a comerse un Chupate DOS!");
                    activePlayer = this.getNextPlayer();
                    currentPlayer = this.players[activePlayer];
                    currentPlayer.addCard(this.drawCard());
                    currentPlayer.addCard(this.drawCard());
                } else if (newCard.getNumber() == 7) {
                    this.iu.displayMessage("\n\t\t\t\tCambio de sentido!");
                    this.isClockwise = !this.isClockwise;
                }
            } else {
                this.iu.displayMessage("\n\t\t\t\tNo se ha podido jugar la carta");
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
        if (!this.isClockwise) {
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
