
package gal.uvigo.esei.aed1.chupatedos.iu;

import gal.uvigo.esei.aed1.chupatedos.core.Game;


public class Main {

  public static void main(String[] args) {
    IU iu = new IU();
    Game chupateDos = new Game(iu);
    chupateDos.play();
  }
}
