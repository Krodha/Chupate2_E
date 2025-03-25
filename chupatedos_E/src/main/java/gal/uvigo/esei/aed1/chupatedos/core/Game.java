package gal.uvigo.esei.aed1.chupatedos.core;

import gal.uvigo.esei.aed1.chupatedos.iu.IU;


public class Game {
    private final IU iu;
    private int numOfPlayers;
    private Player[] players;
    
    
    public Game(IU iu) {
        this.iu = iu;
        this.numOfPlayers = 0;
        this.players = null;
  }
  
   /**
   * Metodo principal para jugar
   */
  public void play() {
      do{
      this.numOfPlayers = iu.readNumber("Cuantos jugadores van a jugar? (Entre 2 y 5)");
      }while(this.numOfPlayers < 2 || this.numOfPlayers > 5);
      players = new Player[this.numOfPlayers];
      for(int i = 0; i <this.numOfPlayers; i++){
          Player player = new Player(iu.readString("Nombre del jugador " + (i+1) + " :"));
          this.players[i] = player;
      }

      this.deckOfCards.shuffle();
      
      for (int i = 0; i < this.numOfPlayers; i++) {
          for (int j = 0; j < 7; j++) {
              this.players[i].addCard(this.deckOfCards.popCard());
          }          
      }
      
      iu.displayMessage("Carta sobre la mesa: " + deckOfCards.getTopCard());
        iu.displayMessage("Número de cartas boca arriba: " + table.getNumCardsTable());
        iu.displayMessage("Cartas restantes sin repartir: " + table.remainingCards());
        iu.displayMessage("Cartas de los jugadores: " + table.playersHand());

      // this.deckOfCards.getTopCard(); ToDo

  }

    public int getNumOfPlayers() {
        return numOfPlayers;
    }

    public Player[] getPlayers() {
        return players;
    }
    
    public Player getPlayer(int pos){
        return players[pos];
    }

 

}
