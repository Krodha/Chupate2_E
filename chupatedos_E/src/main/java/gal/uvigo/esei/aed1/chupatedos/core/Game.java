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
        this.players = null;
        this.deckOfCards = new DeckOfCards();
        this.table = new Table();
    }

    /**
     * Metodo principal para jugar
     */
    public void play() {
        do {
            this.numOfPlayers = iu.readNumber("Cuantos jugadores van a jugar? (Entre 2 y 5): ");
        } while (this.numOfPlayers < 2 || this.numOfPlayers > 5);
        players = new Player[this.numOfPlayers];
        for (int i = 0; i < this.numOfPlayers; i++) {
            Player player = new Player(iu.readString("Nombre del jugador " + (i + 1) + " : "));
            this.players[i] = player;
        }

        this.deckOfCards.shuffle();

        for (int i = 0; i < this.numOfPlayers; i++) {
            for (int j = 0; j < 7; j++) {
                this.players[i].addCard(this.deckOfCards.popCard());
            }
        }

        this.table.setFaceUpCard(this.deckOfCards.popCard());

        iu.displayMessage("Carta sobre la mesa: " + this.table.getFaceUpCard());
        iu.displayMessage("Número de cartas boca arriba: " + table.getNumCardsTable());
        iu.displayMessage("Cartas restantes sin repartir: " + deckOfCards.getSize());
        iu.displayMessage("Cartas de los jugadores:\n" + playersHand());
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
