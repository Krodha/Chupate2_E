package gal.uvigo.esei.aed1.chupatedos.core;

public class Table {

    private int numCardsTable;
    private DeckOfCards deckofcards;
    private int cartasDeJugadores = 0;
    private Game game;

    private int remainingCards() {
        for (int i = 0; game.getNumOfPlayers() > i; i++) {
            cartasDeJugadores = cartasDeJugadores + game.getPlayer(i).getCards().size();
        }
        return 40 - (numCardsTable + cartasDeJugadores);
    }

    public int getNumCardsTable() {
        return numCardsTable;
    }

    public Card getLastCard() {
        return deckofcards.getTopCard();
    }

    public Player playersHand() {
        for (int i = 0; game.getNumOfPlayers() > i; i++) {
            System.out.println(game.getPlayer(i).getName() + ": " + game.getPlayer(i).getCards() + "\n");
        }
    }
}
