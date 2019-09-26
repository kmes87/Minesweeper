package com.game.minesweeper;

import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
    	int nbrMine = 0, rejouer, coordonne = 0; 
    	System.out.print("Choisissez le carré de la matrice (min 4) : ");
        Scanner scan = new Scanner(System.in);
        while (!scan.hasNextInt() || scan.nextInt() < 3) {
    		scan.next();
    	}
        coordonne = scan.nextInt();
        System.out.print("Choisissez le nombre de mine compris entre "+coordonne+" et "+(coordonne+coordonne)+" : "); 
    	while (!scan.hasNextInt()) {
    		scan.next();
    	}
    	nbrMine = scan.nextInt();
        while (true) {
	        if (nbrMine >= coordonne && nbrMine <= coordonne+coordonne){
	        	break;
	        }
	        System.out.print("Choisissez le nombre de mine compris entre "+coordonne+" et "+(coordonne+coordonne)+" : ");
        	while (!scan.hasNextInt()) {
        		scan.next();
        	}
        	nbrMine = scan.nextInt();
        }
        Game game = new Game(coordonne);
        game.resetGame(nbrMine);
        game.afficheMatrice();
        
        while(true){
        	int x, y = 0;
          if(game.getDone() == true && game.getWin() == true){
        	System.out.println("***** Bravo, vous avez gagné ! ****");
            game.onEnd();
        	System.out.print("Voulez vous Rejouez ? O = 1 ou N = 0 : ");
        	while (!scan.hasNextInt()) {
        		scan.next();
        	}
        	rejouer = scan.nextInt();
        	if (rejouer == 1) {
        		game.rejouer(nbrMine);
        	}
        	else {
        		break;
        	}
          }else if(game.getDone() == true){
            game.onEnd(); 
            System.out.print("Voulez vous Rejouez ? O = 1 ou N = 0 : ");
            while (!scan.hasNextInt()) {
        		scan.next();
        	}
            rejouer = scan.nextInt();
        	if (rejouer == 1) {
        		game.rejouer(nbrMine);
        	}
        	else {
        		break;
        	}
          }else if(game.getDone() == false){
            System.out.print("Choisissez la position verticale : ");
            while (!scan.hasNextInt()) {
        		scan.next();
        	}
            y = scan.nextInt();
            System.out.print("Choisissez la position horizontale : ");
            while (!scan.hasNextInt()) {
        		scan.next();
        	}
            x = scan.nextInt();
            if (x <= 0 || x > coordonne || y <= 0 || y > coordonne) {
            	System.out.println("le choix des positions est limité entre 1 et "+coordonne);
            	continue;
            }
            game.checkMine(x-1,y-1);
            game.isVictory();
            game.afficheMatrice();
          } 
        }
        scan.close();
    }
}
