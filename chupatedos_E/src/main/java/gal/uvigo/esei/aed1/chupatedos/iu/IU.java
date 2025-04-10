package gal.uvigo.esei.aed1.chupatedos.iu;

import es.uvigo.esei.aed1.tads.list.List;
import es.uvigo.esei.aed1.tads.stack.LinkedStack;
import gal.uvigo.esei.aed1.chupatedos.core.Card;
import gal.uvigo.esei.aed1.chupatedos.core.DeckOfCards;
import gal.uvigo.esei.aed1.chupatedos.core.Player;
import gal.uvigo.esei.aed1.chupatedos.core.Suit;
import gal.uvigo.esei.aed1.chupatedos.core.Table;
import java.util.Collection;
import java.util.Scanner;

public class IU {

    private final Scanner keyboard;

    public IU() {
        keyboard = new Scanner(System.in);   
    }

    /**
     * Lee un num. de teclado
     *
     * @param msg El mensaje a visualizar.
     * @return El num., como entero
     */
    public int readNumber(String msg) {
        boolean repeat;
        int toret = 0;

        do {
            repeat = false;
            System.out.print(msg);
            try {
                toret = Integer.parseInt(keyboard.nextLine());
            } catch (NumberFormatException exc) {
                repeat = true;
            }
        } while (repeat);

        return toret;
    }

    /**
     * Lee un string de teclado
     *
     * @param msg mensaje a mostrar antes de la lectura
     * @return el string leido
     */
    public String readString(String msg) {
        String toret;
        System.out.print(msg);
        toret = keyboard.nextLine();
        return toret;
    }

    /**
     * muestra un mensaje por pantalla
     *
     * @param msg el mensaje a mostrar
     */
    public void displayMessage(String msg) {
        System.out.println(msg);
    }
    
    public int numPlayers(){
        int numOfPlayers;
        
        do {
            numOfPlayers = readNumber("Cuantos jugadores van a jugar? (Entre 2 y 5): ");
        } while (numOfPlayers < 2 || numOfPlayers > 5);
        
        return numOfPlayers; 
    }
            
    public String[] getPlayersData(){
        String[] names = new String[numPlayers()];
        
        for (int i= 0; i < names.length; i++) {
            names[i] = readString("Nombre del jugador " + (i + 1) + " : ");
        }
        return names;
    } 
    
    public void showPlayableCards(Player player, Card card){
        displayMessage("Cartas jugables del jugador" + player.getName() + " :");
        List <Card> card1 = player.getPlayableCards(card);
        for(int i = 0; i < card1.size(); i++){
            displayMessage((i+1) + ".- " + card1.get(i) );
        }
        
    }
    
    public Card getSelectedCard(Player player, Card topCard){
        
        showPlayableCards(player,topCard);
        
        List <Card> playableCards = player.getPlayableCards(topCard);
        
        if (playableCards.isEmpty()){
            System.out.println("No tienes cartas jugables. Debes robar o pasar turno");
            return null;
        }
        
        int op;
        
        do{
            op = readNumber ("Selecciona la carta que quieres jugar");
           
        }while (op > 1 || op < playableCards.size());
        
        return playableCards.get(op - 1);
      
    }
}
