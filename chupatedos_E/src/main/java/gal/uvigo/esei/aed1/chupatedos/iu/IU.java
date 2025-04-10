package gal.uvigo.esei.aed1.chupatedos.iu;

import gal.uvigo.esei.aed1.chupatedos.core.Card;
import gal.uvigo.esei.aed1.chupatedos.core.DeckOfCards;
import gal.uvigo.esei.aed1.chupatedos.core.Player;
import gal.uvigo.esei.aed1.chupatedos.core.Suit;
import gal.uvigo.esei.aed1.chupatedos.core.Table;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
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
    
    public Card getSelectedCard(Player player, Card[] playableCards){
        displayMessage("Cartas jugables: ");
        
        for (int i = 0; i< playableCards.length; i++){
            displayMessage((i + 1) + "." + playableCards[i]);
            
        }
        int op;
        
        do{
            op = readNumber("Selecciona la carta que quieres jugar: ");
        }while (op < 1 || op > playableCards.length);
        
        return playableCards [op - 1];
        
    }

}
