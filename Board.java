/**
 * 
 */
package edu.cpp.cs.cs141.teamproject;

import java.io.Serializable;
import java.util.Random;

/**
 * @author Toon Squad
 *
 */
public class Board implements Serializable {
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

	private int[] pos = new int[2];
	
	private int lookUp;
	private int lookDown;
	private int lookLeft;
	private int lookRight;
	
	private String mode;
	
	private boolean radar;

	/**
	 * This constructor will set up the game board including the rooms,
	 * power-ups, and the briefcase.
	 */
	public Board() {
		R = new Random();
		board = new char[9][9];
		set();
		s = "";
		mode = "";
		radar = false;
	}
	
	public void set() {
		setBoard();
		setRooms();
		setBullet();
		setInvin();
		setRadar();
		setCase();
		setLook();
	}

	public void setBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int k = 0; k < board[i].length; k++) {
				board[i][k] = ' ';
			}
		}
	}

	public void setRooms() {
		for (int i = 0; i < board.length; i++) {
			for (int k = 0; k < board[i].length; k++) {
				if (i == 1 || i == 4 || i == 7) {
					board[i][1] = ROOMCHAR;
					board[i][4] = ROOMCHAR;
					board[i][7] = ROOMCHAR;
				}
			}
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
		int rndmX = R.nextInt(8);
		int rndmY = R.nextInt(8);
		if ((rndmX == 1 || rndmX == 4 || rndmX == 7)
				&& (rndmY == 1 || rndmY == 4 || rndmY == 7)) {
			caseX = rndmX;
			caseY = rndmY;
		} else {
			setCase();
		}
	}
	
	public void setLook() {
		lookUp = 1;
		lookDown = 1;
		lookLeft = 1;
		lookRight = 1;
	}
	
	public void setPlayer(int i, int k, char x) {
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
	
	public void printCase() {
		board[caseX][caseY] = CASECHAR;
	}
	
	public void printRoom() {
		board[caseX][caseY] = ROOMCHAR;
	}

	/**
	 * This method will print out the board.
	 */
	public String toString() {
		if(mode.equals("debug") || radar == true) {
			printCase();
		} else {
			printRoom();
		}
		s = "";
		for (int i = 0; i < board.length; i++) {
			for (int k = 0; k < board[i].length; k++) {
				if ((i == playerX && k == playerY)
						|| ((i >= playerX - lookUp && i < playerX) && k == playerY)
						|| ((i <= playerX + lookDown && i > playerX) && k == playerY)
						|| (i == playerX && (k <= playerY + lookRight && k > playerY))
						|| (i == playerX && (k >= playerY - lookLeft && k < playerY))) {
					s += "[" + board[i][k] + "]";
				} else if (board[i][k] == ROOMCHAR || board[i][k] == CASECHAR) {
					s += "[" + board[i][k] + "]";
				} else if(mode.equals("normal")) {
					s += "[" + "*" + "]";
				} else if(mode.equals("debug")) {
					s += "[" + board[i][k] + "]";
				}
			}
			if(i == 0){
				s += "   LEGEND";
			}
			if(i ==2){
				s += "   P: Player";
			}
			if(i ==3){
				s += "   E: Enemy";
			}
			if(i==4){
				s+= "   X: Rooms";
			}
			if(i==5){
				s+= "   B: Bullet";
			}
			if(i==6){
				s+= "   I: Invincibility";
			}
			if(i==7){
				s+= "   R: Radar";
			}
			s = s + "\n";
		}
		setLook();
		return s;
	}
	
	public boolean lookUp() {
		boolean x = false;
		if(playerX > 1) {	
			if(board[playerX-1][playerY] != ROOMCHAR && board[playerX - 2][playerY] != ROOMCHAR) {
				if(playerX-2 >= 1) {
					if(board[playerX-2][playerY] == 'E' || board[playerX-3][playerY] == 'E') {
						x = true;
					}
					lookUp+=2;
				}else if(playerX-2 == 0) {
					if(board[playerX-1][playerY] == 'E' || board[playerX-2][playerY] == 'E') {
						x = true;
					}
					lookUp+=1;
				}
			}
			if(board[playerX-1][playerY] == 'E') {
				x = true;	
			}
		}
		return x;
	}

	public boolean lookLeft() {
		boolean x = false;
		if(playerY > 1) {
			if(board[playerX][playerY-1] != ROOMCHAR && board[playerX][playerY-2] != ROOMCHAR) {
				if(playerY-2 >= 1) {
					if(board[playerX][playerY-2] == 'E' || board[playerX][playerY-3] == 'E') {										
						x = true;
					}
					lookLeft+=2;
				}else if(playerY-2 == 0) {
					if(board[playerX][playerY-1] == 'E' || board[playerX][playerY-2] == 'E') {
						x = true;
					}
					lookLeft+=1;
				}
			}
			if(board[playerX][playerY-1] == 'E') {
				x = true;	
			}
		}
		return x;
	}

	public boolean lookRight() {
		boolean x = false;
		if(playerY < 7) {
			if(board[playerX][playerY+1] != ROOMCHAR && board[playerX][playerY+2] != ROOMCHAR) {
				if(playerY+2 <= 7) {
					if(board[playerX][playerY+2] == 'E' || board[playerX][playerY+3] == 'E') {
														
						x = true;
					}
					lookRight+=2;
				}else if(playerY+2 == 8) {
					if(board[playerX][playerY+1] == 'E' || board[playerX][playerY+2] == 'E') {
						x = true;
					}
					lookRight+=1;
				}
			}
			if(board[playerX][playerY+1] == 'E') {
				x = true;
			}
		}
		return x;
	}

	public boolean lookDown() {
		boolean x = false;
		if(playerX < 7) {
			if(board[playerX+1][playerY] != ROOMCHAR && board[playerX +2][playerY] != ROOMCHAR) {
				if(playerX+2 <= 7) {
					if(board[playerX+2][playerY] == 'E' || board[playerX+3][playerY] == 'E') {										
						x = true;
					}
					lookDown+=2;
				}else if(playerX+2 == 8) {
					if(board[playerX+1][playerY] == 'E' || board[playerX+2][playerY] == 'E') {
						x = true;
					}
					lookDown+=1;
				}
			}
			if(board[playerX+1][playerY] == 'E') {
			x = true;
			}
		}
		return x;
	}
	
	public char[][] getBoard() {
		return board;
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
	
	public boolean checkRoom() {
		boolean x = false;
		if(playerX + 1 == caseX && playerY == caseY) {
			x = true;
		}
		return x;
	}
	
	public boolean win() {
		boolean x = false;
		if(checkRoom() == true) {
			x = true;
		}
		return x;
	}

	public boolean checkChar(int x, int y, char z){
		boolean check = false;
		if(board[x][y] == z){
			 check = true;
		}
		return check;
	}	
	
	public boolean enemyCheck(int i, int k) {
		boolean x = true;
		if (i > 4 && k < 4) {
			x = false;
		}
		return x;
	}
	
	public String enemyRowCheck(int enemyX2, int enemyY2) {
		String s = ""; 
		boolean up = true;
		boolean down = true;
		boolean right = true;
		boolean left = true;
		
		for(int i = enemyX2; i < board.length; i++){
			if(board[i][enemyY2] == ROOMCHAR || board[i][enemyY2] == CASECHAR){
				down = false;
			}
			if(board[i][enemyY2] == 'P'){
				if(down == true){
					s = "down";
				}
			}
		}
		
		for(int i = enemyX2; i >= 0; i--){
			if(board[i][enemyY2] == ROOMCHAR || board[i][enemyY2] == CASECHAR){
				up = false;
			}
			if(board[i][enemyY2] == 'P'){
				if(up == true){
					s = "up";
				}
			}
		}
		for(int i = enemyY2; i < board.length; i++){
			if(board[enemyX2][i] == ROOMCHAR || board[enemyX2][i] == CASECHAR){
				right = false;
			}
			if(board[enemyX2][i] == 'P'){
				if(right == true){
					s = "right";
				}
			}
		}
	
		for(int i = enemyY2; i >= 0; i--){
			if(board[enemyX2][i] == ROOMCHAR || board[enemyX2][i] == CASECHAR){
				left = false;
			}
			if(board[enemyX2][i] == 'P'){
				if(left == true){
					s = "left";
				}
			}
		}
		return s;
	}
	public boolean avaliableMove(){
		boolean x = false;
		if(playerMoveCheck(playerX+1, playerY) == true || playerMoveCheck(playerX-1, playerY) == true
		||playerMoveCheck(playerX, playerY+1) == true||playerMoveCheck(playerX, playerY-1) == true){
			x = true;
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

	public void checkDeath(Enemy enemies) {
		if (enemies.getEnemyX() > 4 && enemies.getEnemyY() < 4) {
			int rndmX = R.nextInt(9);
			int rndmY = R.nextInt(9);
			if (check(rndmX, rndmY) == true && enemyCheck(rndmX, rndmY) == true) {
				board[enemies.getEnemyX()][enemies.getEnemyY()] = ' ';
				enemies.setEnemyX(rndmX);
				enemies.setEnemyY(rndmY);
				board[rndmX][rndmY] = enemies.getEnemy();
			} else {
				checkDeath(enemies);
			}
		}

	}

	public boolean killPlayerUp(int i, int k) {
		boolean x = false;
		if (i < 0) {
			x = false;
		} else {
			if (board[i][k] == 'P') {
				board[i][k] = ' ';
				x = true;
			}
		}
		return x;
	}

	public boolean killPlayerLeft(int i, int k) {
		boolean x = false;
		if (k < 0) {
			x = false;
		} else {
			if (board[i][k] == 'P') {
				board[i][k] = ' ';
				x = true;
			}
		}
		return x;
	}

	public boolean killPlayerRight(int i, int k) {
		boolean x = false;
		if (k > 8) {
			x = false;
		} else {
			if (board[i][k] == 'P') {
				board[i][k] = ' ';
				x = true;
			}
		}
		return x;
	}

	public boolean killPlayerDown(int i, int k) {
		boolean x = false;
		if (i > 8) {
			x = false;
		} else {
			if (board[i][k] == 'P') {
				board[i][k] = ' ';
				x = true;
			}
		}
		return x;
	}

	public void enemyUp(int X, int Y, char k) {
		board[X][Y] = ' ';
		board[X - 1][Y] = k;
	}

	public void enemyLeft(int X, int Y, char k) {
		board[X][Y] = ' ';
		board[X][Y - 1] = k;
	}

	public void enemyRight(int X, int Y, char k) {
		board[X][Y] = ' ';
		board[X][Y + 1] = k;
	}

	public void enemyDown(int X, int Y, char k) {
		board[X][Y] = ' ';
		board[X + 1][Y] = k;
	}

	public int[] position() {
		return pos;
	}

	public void shootUp() {
		int i = playerX - 1;
		int k = playerY;

		while (i >= 0 && (board[i][k] != ROOMCHAR && board[i][k] != CASECHAR)) {

			if (board[i][k] == 'E') {
				board[i][k] = ' ';
				pos[0] = i;
				pos[1] = k;
				i = 0;
			}
			i--;
		}
	}

	public void shootLeft() {
		int i = playerX;
		int k = playerY - 1;

		while (k >= 0 && board[i][k] != ROOMCHAR) {
			if (board[i][k] == 'E') {
				board[i][k] = ' ';
				pos[0] = i;
				pos[1] = k;
				k = 0;
			}
			k--;
		}
	}

	public void shootRight() {
		int i = playerX;
		int k = playerY + 1;

		while (k <= 8 && board[i][k] != ROOMCHAR) {
			if (board[i][k] == 'E') {
				board[i][k] = ' ';
				pos[0] = i;
				pos[1] = k;
				k = 8;
			}
			k++;
		}
	}

	public void shootDown() {
		int i = playerX + 1;
		int k = playerY;

		while (i <= 8 && board[i][k] != ROOMCHAR) {
			if (board[i][k] == 'E') {
				board[i][k] = ' ';
				pos[0] = i;
				pos[1] = k;
				i = 8;
			}
			i++;
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

	public void setMode(String x) {
		mode = x;
	}
	
	public void setRadar(boolean x) {
		radar = x;
	}
	
	// GETTERS
	public String getMode() {
		return mode;
	}
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
