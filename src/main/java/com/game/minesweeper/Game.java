package com.game.minesweeper;

import java.util.Random;

public class Game {
	public String[][] tabCache;
	public String[][] tabAffiche; 
	public Boolean isDone;
	public Boolean isWin;
	private String unknown;
	private String mine;
	private String empty;

	public Game(int xy){
		tabCache = new String[xy][xy];
		tabAffiche = new String[xy][xy];
		isDone = false;
		isWin = false;
		unknown = "?";
		mine = "*";
		empty = " ";
	}
	
	public void resetGame(int nbrMine) {
		for(int x = 0; x < tabCache.length; x++){
			for(int y = 0; y < tabCache[0].length; y++){
				tabCache[x][y] = unknown;
				tabAffiche[x][y] = unknown;
			}
		}
		generateMines(nbrMine);
	}

	public static void drawGame(String[][] str){
		for(int i = 0; i < str.length; i++){
			System.out.print(" | ");
			for(int j = 0; j < str[0].length; j++){
				System.out.print(str[i][j]);
				System.out.print(" | ");
			}
			System.out.println("");
			System.out.print(" ");
			for(int n = 0; n < str.length; n++){
				System.out.print("----");
			}
			System.out.println("");
		}
		System.out.println("");
	}

	public void afficheMatrice(){
		drawGame(tabAffiche);
	}
	
	public void rejouer(int nbrMine) {
		resetGame(nbrMine);
		afficheMatrice();
		isDone = false;
		isWin = false;
	}

	public void generateMines(int nbrMine){
		for(int i = 0; i < nbrMine; i++){
			Boolean minePose = false;
			while(!minePose){
				int x, y = 0;
				Random r = new Random();
				x = r.nextInt(tabCache.length - 1);
				y = r.nextInt(tabCache[0].length - 1);
				if(!tabCache[x][y].equals(mine)){
					tabCache[x][y] = mine;
					minePose = true;
				}
			}
		}
	}
	
	public void checkMine(int x, int y){
		if (tabCache[x][y].equals(unknown)) {
			isDone = false;
			tabCache[x][y] = empty;
			int nbrMine = 0;
			
			//Définir les bornes min max à vérifier dans la matrice
			int xMin = x == 0 ? 0 : x-1;
			int xMax = x == tabCache.length - 1 ? tabCache.length - 1 : x+1;
			int yMin = y == 0 ? 0 : y-1;
			int yMax = y == tabCache[0].length - 1 ? tabCache[0].length - 1 : y+1;
			
			//Calculer le nombre de mines dans les cases voisines
			for (int i = xMin; i <= xMax; i++) {
				for (int j = yMin; j <= yMax; j++) {
					if (tabCache[i][j].equals(mine)) {
						nbrMine++;
					}
				}
			}
			tabAffiche[x][y] = String.valueOf(nbrMine);
		} else if (tabCache[x][y].equals(mine)) {
			isDone = true;
			System.out.println("Perdu!");
		} else {
			isDone = false;
			System.out.println("La case est déjà vérifiée");
		}
	}

	public String getTile(int x, int y){
		return tabCache[x][y];
	}

	public Boolean isVictory(){
		int nbrUnknow = 0;
		for(int i = 0; i < (tabCache.length); i++){
			for(int j = 0; j < (tabCache[0].length); j++){
				if(tabCache[i][j].equals(unknown)) {
					nbrUnknow++;
					break;
				}
				if (nbrUnknow != 0){
					break;
				}
			}
		}
		if(nbrUnknow == 0) {
			isWin = true;
			isDone = true;
		}
		return isWin;
	}

	public Boolean getDone(){
		return isDone;
	}

	public Boolean getWin(){
		return isWin;
	}

	public void onEnd(){
		drawGame(tabCache);
	}
}
