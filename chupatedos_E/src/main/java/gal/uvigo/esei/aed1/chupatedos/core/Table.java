package gal.uvigo.esei.aed1.chupatedos.core;

public class Table {

    private int numCardsTable;
    private DeckOfCards deckOfCards;
//    private int cartasDeJugadores = 0; (CAMBIO)
    private Game game;
    
    //CAMBIO:
    public Table (Game game, DeckOfCards deckOfCards){
        
        this.game = game;
        this.deckOfCards = deckOfCards;
        this.numCardsTable = 0;
        
    }
    //CAMBIO:
    public boolean isEmpty(){
        return this.numCardsTable == 0;
    }
    //CAMBIO:
    public void addCardTable(Card card){
        this.numCardsTable++;
    }

    public int remainingCards() {//CAMBIO: PONER EL MÉTODO PUBLIC, ya que se llamará en la cabecera
        int cartasDeJugadores = 0;//CAMBIO A VARIABLE LOCAL
        for (int i = 0; game.getNumOfPlayers() > i; i++) {
            cartasDeJugadores = cartasDeJugadores + game.getPlayer(i).getCards().size();
        }
        return 40 - (numCardsTable + cartasDeJugadores);
    }

    public int getNumCardsTable() {
        return numCardsTable;
    }

    public Card getLastCard() {
        return deckOfCards.getTopCard();
    }

//    public Player playersHand() {
//        for (int i = 0; game.getNumOfPlayers() > i; i++) {
//            System.out.println(game.getPlayer(i).getName() + ": " + game.getPlayer(i).getCards() + "\n");
//        }
//    }
    //El método dice que devuelve Player, pero no devuelve nada. Lo cambio por método toString:
    
    public String playersHand(){
        
        StringBuilder cadena = new StringBuilder ();
        
        for (int i=0; game.getNumOfPlayers() > i; i++){
            
            cadena.append(game.getPlayer(i).getName())
            .append(": ")
            .append(game.getPlayer(i).getCards())
            .append("\n");
        }
        
        return cadena.toString();
    }
  //No hay que hacer ningún cambio al llamarlo
}
