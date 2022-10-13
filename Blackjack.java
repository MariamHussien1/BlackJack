/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

/**
 *
 * @author Lenovo
 */
public class Blackjack {

    /**
     * @param args the command line arguments
     */
        
    static Game game = new Game();
    
    public static void main(String[] args) {
        GUI gui = new GUI();
        game.Generate_carddeck();
        game.setInformation();
        gui.runGUI( game.card, game.player[0].getCard(), game.player[1].getCard(), game.player[2].getCard(),game.player[3].getCard());
        game.hit_stand(gui);
        game.update_maxscore();
        game.dealer(gui);
    }       
}