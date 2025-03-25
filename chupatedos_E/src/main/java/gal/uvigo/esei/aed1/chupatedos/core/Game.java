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
