/**
 * 
 */
package edu.cpp.cs.cs141.teamproject;

import java.util.Random;

/**
 * @author Toon Squad
 *
 */
public class Board {
	/**
	 * A 2D array that will be the game board.
	 */
	private char[][] board;

	private int playerX;
	private int playerY;
	
	private int enemyX;
	private int enemyY;

	/**
	 * The additional bullet power-up
	 */
	private final char BULLETCHAR = 'B';
	/**
	 * The invincibility power-up.
	 */
	private final char INVINCHAR = 'I';
	/**
	 * The radar power-up
	 */
	private final char RADARCHAR = 'R';
	/**
	 * The briefcase
	 */
	private final char CASECHAR = 'C';
	
	private int caseX;
	private int caseY;
	/**
	 * The rooms where the briefcase will be.
	 */
	private final char ROOMCHAR = 'X';

	private String s;

	private Random R;

	/**
	 * This constructor will set up the game board including the rooms,
	 * power-ups, and the briefcase.
	 */
	public Board() {
		R = new Random();
		board = new char[9][9];
		s = "";
		setBoard();
		setRooms();
		setBullet();
		setInvin();
		setRadar();
		setCase();
	}

	public void setBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = ' ';
			}
		}
	}

	public void setRooms() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (i == 1 || i == 4 || i == 7) {
					board[i][1] = ROOMCHAR;
					board[i][4] = ROOMCHAR;
					board[i][7] = ROOMCHAR;
				}
			}
		}
	}

	public void setPlayer(int i, int k, char x) {
		// NEED CHECK METHOD IN GAMEENGINE
		board[i][k] = x;
		playerX = i;
		playerY = k;
	}

	public void setEnemy(char x) {
		int rndmX = R.nextInt(9);
		int rndmY = R.nextInt(9);
		if (check(rndmX, rndmY) == true && enemyCheck(rndmX, rndmY)) {
			board[rndmX][rndmY] = x;
			enemyX = rndmX;
			enemyY = rndmY;
		} else {
			setEnemy(x);
		}
	}

	public void itemSet(char x) {
		int rndmX = R.nextInt(9);
		int rndmY = R.nextInt(9);
		if (check(rndmX, rndmY) == true) {
			board[rndmX][rndmY] = x;
		} else if (check(rndmX, rndmY) == false) {
			itemSet(x);
		}
	}

	public void setBullet() {
		itemSet(BULLETCHAR);
	}

	public void setInvin() {
		itemSet(INVINCHAR);
	}

	public void setRadar() {
		itemSet(RADARCHAR);
	}

	public void setCase() {
		int rndmX = R.nextInt(7);
		int rndmY = R.nextInt(7);
		if ((rndmX == 1 || rndmX == 4 || rndmX == 7) && (rndmY == 1 || rndmY == 4 || rndmY == 7)) {
			caseX = rndmX;
			caseY = rndmY;
//			board[rndmX][rndmY] = briefcase;
		} else {
			setCase();
		}
	}

	/**
	 * This method will print out the board.
	 */
	public String toString() {
		s = "";
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				s += "[" + board[i][j] + "]";
			}
			s = s + "\n";
		}
		return s;
	}

	/**
	 * This method will check to see if a player is able to step in the
	 * direction that they chose.
	 * 
	 * @return true if they can step in that direction, return false if they
	 *         cannot.
	 */
	public boolean check(int i, int k) {
		boolean x = false;
		if (board[i][k] == ' ') {
			x = true;
		}
		return x;
	}
	
	public boolean enemyCheck(int i, int k) {
		boolean x = true;
		if (i > 4 && k < 3) {
			x = false;
		}
		return x;
	}
	
	public boolean playerMoveCheck(int i, int k) {
		boolean x = false;
		if (i < 0 || i > 8 || k < 0 || k > 8) {
			x = false;
		} else {
			switch (board[i][k]) {
			case ' ':
				x = true;
				break;
			case BULLETCHAR:
				x = true;
				break;
			case RADARCHAR:
				x = true;
				break;
			case INVINCHAR:
				x = true;
				break;
			default:
				break;
			}
		}
		return x;
	}

	public boolean enemyMoveCheck(int i, int k) {
		boolean x = false;
		if (i < 0 || i > 8 || k < 0 || k > 8) {
			x = false;
		} else {
			switch (board[i][k]) {
			case ' ':
				x = true;
				break;
			default:
				break;
			}
		}
		return x;
	}
	
	public boolean killPlayerUp(int i, int k){
		boolean x = false;
		if(i < 0){
			x = false;
		}else{
			if(board[i][k] == 'P'){
				board[i][k] = ' ';
				x = true;
			}
		}
		return x;
	}
	
	public boolean killPlayerLeft(int i, int k){
		boolean x = false;
		if(k < 0){
			x = false;
		}else{
			if(board[i][k] == 'P'){
				board[i][k] = ' ';
				x = true;
			}
		}
		return x;
	}
	
	public boolean killPlayerRight(int i, int k){
		boolean x = false;
		if(k > 8){
			x = false;
		}else{
			if(board[i][k] == 'P'){
				board[i][k] = ' ';
				x = true;
			}
		}
		return x;
	}
	
	public boolean killPlayerDown(int i, int k){
		boolean x = false;
		if(i > 8){
			x = false;
		}else{
			if(board[i][k] == 'P'){
			board[i][k] = ' ';
			x = true;
			}
		}
		return x;
	}
		
	public void enemyUp(int X, int Y, char k) {
		board[X][Y] = ' ';
		board[X-1][Y] = k;
	}

	public void enemyLeft(int X, int Y, char k) {
		board[X][Y] = ' ';
		board[X][Y-1]= k;
	}

	public void enemyRight(int X, int Y, char k) {
		board[X][Y] = ' ';
		board[X][Y+1]= k;
	}

	public void enemyDown(int X, int Y, char k) {
		board[X][Y] = ' ';
		board[X+1][Y]= k;
	}
	
	
	public int[] killPosition(int i, int k) {
		int[] A = {i,k};
		return A;
	}
	
	public void shootUp(int k) {
		for (int i = playerX; i > -1; --i) {
			if(board[i][k] == 'E') {
				board[i][k] = ' ';
				killPosition(i, k);
			}
		}
	}
	public void shootLeft(int i) {
		for (int k = playerY; k > -1; --k) {
			if(board[i][k] == 'E') {
				board[i][k] = ' ';
				killPosition(i, k);
			}
		}
	}
	public void shootRight(int i) {
		for (int k = playerY; k < board.length; ++k) {
			if(board[i][k] == 'E') {
				board[i][k] = ' ';
				killPosition(i, k);
			}
		}
	}
	public void shootDown(int k) {
		for (int i = playerX; i < board.length; ++i) {
			if(board[i][k] == 'E') {
				board[i][k] = ' ';
				killPosition(i, k);
			}
		}
	}

	public void playerUp(char x) {
		board[playerX][playerY] = ' ';
		playerX -= 1;
		setPlayer(playerX, playerY, x);
	}

	public void playerLeft(char x) {
		board[playerX][playerY] = ' ';
		playerY -= 1;
		setPlayer(playerX, playerY, x);
	}

	public void playerRight(char x) {
		board[playerX][playerY] = ' ';
		playerY += 1;
		setPlayer(playerX, playerY, x);
	}

	public void playerDown(char x) {
		board[playerX][playerY] = ' ';
		playerX += 1;
		setPlayer(playerX, playerY, x);
	}

	
	
	
	
	/**
	 * This method will search for an enemy in the direction the gun of the
	 * player was fired. This will only function if the player has ammo to
	 * shoot.
	 * 
	 * @return true if enemy is in line of sight of bullet, return false if not.
	 */
	public boolean searchShot() {
		return false;
	}

	/**
	 * This method will check if the player will pick up the bullet power-up in
	 * the next step
	 * 
	 * @return
	 */
	public boolean bullet(char[][] board) {
		return false;
	}

	/**
	 * This method will check if the player will pick up the invincibility
	 * power-up in the next step
	 * 
	 * @return
	 */
	public boolean invincibility(char[][] board) {
		return false;

	}

	/**
	 * This method will check if the player will pick up the radar power-up in
	 * the next step
	 * 
	 * @return
	 */
	public boolean radar(char[][] board) {
		return false;
	}

	/**
	 * This method will check if the player will pick up the briefcase in the
	 * next step
	 * 
	 * @return
	 */
	public boolean briefcase(char[][] board) {
		return false;
	}
	
	
	//GETTERS
	public int getPlayerX() {
		return playerX;
	}

	public int getPlayerY() {
		return playerY;
	}
	
	public int getEnemyX() {
		return enemyX;
	}

	public int getEnemyY() {
		return enemyY;
	}
}
