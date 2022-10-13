/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;
import java.util.Scanner;
/**
 *
 * @author Lenovo
 */
public class Player {
    private String Name;
    private int Score;
    Card card[ ]= new Card[11];
    int index=0;

    public Player(String Name, int Score) {
        this.Name = Name;
        this.Score = Score;
    }

    public Player() {
    }

    public String getName() {
        return Name;
    }

    public int getScore() {
        return Score;
    }

    public Card[] getCard() {
        return card;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void Name(){
        System.out.println("Enter Player Name: ");
        this.Name = new Scanner(System.in).next();        
    }
    
    public void setScore(int Score) {
        this.Score = Score;
    }

    public void setCard( Card c) {
        card[index]=c;
        index++;
    }
}