/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import java.util.Random;
import java.util.Scanner;
/**
 *
 * @author Lenovo
 */
public class Game {
    Card card[ ]= new Card[52];
    int highScore=-1,handSum=0;
    Player[] player=new Player[4];
    int winner=0;
    
    public void Generate_carddeck(){
        int c=0;
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 13 ;j++){
                if(j<10){
                    card[c] = new Card (i, j,j+1);
                    c++;
                }
                else{
                    card[c] = new Card (i, j,10);
                    c++;
                }
            }
        }
    }
    
    public Card drawcard(){
        Card the_card=new Card();
        Random rand = new Random();
        int value=0;
        while(true){
            int randomchoice = rand.nextInt(52);
            if(card[randomchoice] != null){
                the_card= card[randomchoice];
                value =card[randomchoice].getValue();
                System.out.println("value of card drawn is"+value);
                handSum=handSum+value;
                card[randomchoice]=null;
                return the_card;
            }
        } 
    }
    
    public void setInformation(){

        for(int i=0; i<4;i++){
            player[i] = new Player();
            if(i!=3){
            player[i].Name();
            handSum=0;
            }
            if(i==3){
               player[i].setName("Dealer");
            }
            for(int j=0; j<2;j++){
             Card c=  drawcard();
            player[i].setCard(c);
            }
            player[i].setScore(handSum);
            handSum=0;
            System.out.println(player[i].getName()+" SCORE IS " + player[i].getScore());
        }
    }
   
    public void update_maxscore(){
        for(int i=0;i<4;i++){
            if(i!=3){
                handSum=0;
                for(int j=0;j<3;j++){
                    if(player[i].getScore()>highScore && player[i].getScore() <= 21){
                        highScore=player[i].getScore();
                        winner=j;
                    }
                }
            }
        }
    }
    
    public void hit_stand(GUI gui){
        boolean m;
        Scanner scn = new Scanner(System.in);
        for(int i=0;i<3;i++){
            m=true;
            System.out.println("PLAYER ( "+player[i].getName()+" ) TURN");
            while(m){
                System.out.println("Enter 1 to Hit");
                System.out.println("Enter 2 to Stand");
                int x=scn.nextInt();
                if(x==1){
                    handSum=0;
                   Card c=drawcard();
                   gui.updatePlayerHand(c,i);
                   player[i].setScore(player[i].getScore() +handSum);
                   System.out.println(player[i].getName()+" NEW SCORE IS " + player[i].getScore());
                        for (int k = 0; k < 3; k++) {
                            if (player[i] == player[k] && i != k) {
                                System.out.println(player[i].getName()+" & "+player[k].getName()+" PUSH");
                            }
                        }
                    if(player[i].getScore() > 21){
                       System.out.println(player[i].getName()+" BUSTED WITH SCORE "+player[i].getScore());
                       m=false;
                    }
                   else if (player[i].getScore() == 21){
                       System.out.println(player[i].getName()+" GOT BLACKJACK");
                       m=false;
                    }   
                }
                else if(x==2){
                    m=false;
                    handSum=0;
                }
            }
        }
    }
    
    public void dealer(GUI gui){
        
        if(player[3].getScore() == 21){
            System.out.println("DEALER GOT BLACKJACK " +player[3].getScore()); 
        }
        else if(player[3].getScore()> highScore && player[3].getScore()<=21 ){
            System.out.println("DEALER WON WITH SCORE " +player[3].getScore());
        }
        else if(player[3].getScore() == highScore){
            System.out.println(player[3].getName()+" & "+player[winner].getName()+" PUSH");
        }
        else if(player[3].getScore()< highScore){
            while(highScore > player[3].getScore()){
                handSum=0;
               Card d= drawcard();
               gui.updateDealerHand(d,card);
                player[3].setScore(player[3].getScore() +handSum);
            }
            if(player[3].getScore()<=21 && player[3].getScore() > highScore){
                System.out.println("DEALER WON WITH SCORE " +player[3].getScore()); 
            }
            else {
                System.out.println(player[3].getName()+" BUSTED WITH SCORE "+player[3].getScore());
                System.out.println(player[winner].getName()+" WIN with Score "+highScore);
            }
        }
    }
}