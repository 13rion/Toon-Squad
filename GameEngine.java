/**
 * 
 */
package edu.cpp.cs.cs141.teamproject;

import java.util.Random;

/**
 * @author Toon Squad
 *
 */
public class GameEngine {

	/**
	 * 
	 */
	private Player player;
	/**
	 * 
	 */
	private Board board;

	private Random R;
	private Enemy[] arr;
	
	private boolean kill;

	private boolean invincibility;
	
	private int invin = 0;

	/**
	 * This constructor will instantiate Player, Enemy, and Board, as well as
	 */
	public GameEngine() {
		board = new Board();
		player = new Player();
		R = new Random();
		arr = new Enemy[6];
		kill = false;
		invincibility = false;
		set();
	}


	public void set() {
		board.setPlayer(8, 0, player.getPlayer());
		for (int i = 0; i < arr.length; i++) {
			arr[i] = new Enemy();
			board.setEnemy(arr[i].getEnemy());
			arr[i].setEnemyX(board.getEnemyX());
			arr[i].setEnemyY(board.getEnemyY());
		}
	}
	
	public void resetPlayer() {
		board.setPlayer(8, 0, player.getPlayer());
	}

	public void enemyMove() {
		if(invincibility == true){
			invin++;
			if(invin == 5){
				invincibility = false;
			}
		}else{
			for (int i = 0; i < arr.length; i++) {
				if (arr[i].alive() == true) {
					kill(arr[i]);
				}
			}
		}
		for (int j = 0; j < arr.length; j++) {
			if (kill == false) {
				if (arr[j].alive() == true) {
					int rndm = R.nextInt(4);
					randomEnemy(arr[j], rndm);
				}
			}
		}
		kill = false;
	}

	public void randomEnemy(Enemy arr2, int x) {
		if((board.enemyMoveCheck(arr2.getEnemyX() - 1, arr2.getEnemyY()) == false) &&
			(board.enemyMoveCheck(arr2.getEnemyX(), arr2.getEnemyY() - 1) == false)	&&
			(board.enemyMoveCheck(arr2.getEnemyX(), arr2.getEnemyY() + 1) == false) &&
			(board.enemyMoveCheck(arr2.getEnemyX() + 1, arr2.getEnemyY()) == false)) {
			
		} else {
			switch(x){
				//Up
				case 0:
					if (board.enemyMoveCheck(arr2.getEnemyX() - 1, arr2.getEnemyY()) == true) {
						board.enemyUp(arr2.getEnemyX(), arr2.getEnemyY(), arr2.getEnemy());
						arr2.setEnemyX(arr2.getEnemyX() - 1);
						arr2.setEnemyY(arr2.getEnemyY());
					}else{
						x = R.nextInt(4 - 1) + 1;
						randomEnemy(arr2, x);
					}
					break;
				//Left
				case 1:
					if (board.enemyMoveCheck(arr2.getEnemyX(), arr2.getEnemyY() - 1) == true) {
						board.enemyLeft(arr2.getEnemyX(), arr2.getEnemyY(), arr2.getEnemy());
						arr2.setEnemyX(arr2.getEnemyX());
						arr2.setEnemyY(arr2.getEnemyY() - 1);
					}else{
						while(x == 1) {
							x = R.nextInt(4);
						}
						randomEnemy(arr2,x);
					}
					break;
				//Right
				case 2:
					if (board.enemyMoveCheck(arr2.getEnemyX(), arr2.getEnemyY() + 1) == true) {
						board.enemyRight(arr2.getEnemyX(), arr2.getEnemyY(),arr2.getEnemy());
						arr2.setEnemyX(arr2.getEnemyX());
						arr2.setEnemyY(arr2.getEnemyY() + 1);
					}else{
						x = R.nextInt(4);
						while(x == 2) {
							x = R.nextInt(4);
						}
						randomEnemy(arr2,x);
					}
					break;
				//Down
				case 3:
					if (board.enemyMoveCheck(arr2.getEnemyX() + 1, arr2.getEnemyY()) == true) {
						board.enemyDown(arr2.getEnemyX(), arr2.getEnemyY(), arr2.getEnemy());
						arr2.setEnemyX(arr2.getEnemyX() + 1);
						arr2.setEnemyY(arr2.getEnemyY());
					} else {
						x = R.nextInt(4 - 1);
						randomEnemy(arr2, x);
					}
					break;
				default:
					break;
			}
		}
	}

	public boolean kill(Enemy arr2) {
		if (board.killPlayerUp(arr2.getEnemyX() - 1, arr2.getEnemyY()) == true) {
			player.subLives();
			for (int i = 0; i < arr.length; i++) {
				board.checkDeath(arr[i]);
			}
			resetPlayer();
			kill = true;
		} else if (board.killPlayerDown(arr2.getEnemyX() + 1, arr2.getEnemyY()) == true) {
			player.subLives();
			for (int i = 0; i < arr.length; i++) {
				board.checkDeath(arr[i]);
			}
			resetPlayer();
			kill = true;
		} else if (board
				.killPlayerRight(arr2.getEnemyX(), arr2.getEnemyY() + 1) == true) {
			player.subLives();
			for (int i = 0; i < arr.length; i++) {
				board.checkDeath(arr[i]);
			}
			resetPlayer();
			kill = true;
		} else if (board.killPlayerLeft(arr2.getEnemyX(), arr2.getEnemyY() - 1) == true) {
			player.subLives();
			for (int i = 0; i < arr.length; i++) {
				board.checkDeath(arr[i]);
			}
			resetPlayer();
			kill = true;
		}
		return kill;
	}
	
	public String start(String t) {
		String s = "";
		switch(t) {
		case "1": 
		case "start":
		case "s":
			s = "start";
			board.setMode("normal");
			break;
		case "2": 
		case "load":
		case "l":
			s = "load";
			break;
		case "3": 
		case "debug":
		case "d":
			s = "debug";
			board.setMode("debug");
			break;
		default:
			break;
		}
		return s;

	}
	
	public String action(String t) {
		String s = "";
		switch(t) {
		case "1":
		case "move":
		case "m":
			s = "move";
			break;
		case "2":
		case "shoot":
		case "s":
			s = "shoot";
			break;
		case "3":
		case "look":
		case "l":
			s = "look";
			break;
		case "4":
		case "check":
		case "c":
			s = "check";
			break;
		case "5":
		case "save":
			s = "save";
			break;
		case "6":
		case "debug":
		case "d":
			if(board.getMode() == "normal" || board.getMode() == "") {
				s = "debug";
				board.setMode("debug");
			} else if(board.getMode() == "debug") {
				s = "normal";
				board.setMode("normal");
			}
			break;
		default:
			break;
		}
		return s;

	}
	
	public boolean checkRoom() {
		return board.checkRoom();
	}

	public String move(String x) {
		String s = "";
		switch (x) {
		case "1":
		case "up":
			if (board.playerMoveCheck(board.getPlayerX() - 1,board.getPlayerY()) == true) {
				if(board.checkChar(board.getPlayerX()-1,board.getPlayerY(),'B') == true){
					player.giveAmmo();
				}
				else if(board.checkChar(board.getPlayerX()-1,board.getPlayerY(),'I') == true){
					invincibility = true;
				}
				else if(board.checkChar(board.getPlayerX()-1,board.getPlayerY(),'R') == true){
					board.printCase();
				}
				board.playerUp(player.getPlayer());
				s = "";
				enemyMove();
			}
			break;
		case "2":
		case "left":
			if (board.playerMoveCheck(board.getPlayerX(),board.getPlayerY() - 1) == true) {
				if(board.checkChar(board.getPlayerX(),board.getPlayerY()-1,'B') == true){
					player.giveAmmo();
				}
				else if(board.checkChar(board.getPlayerX(),board.getPlayerY()-1,'I') == true){
					invincibility = true;
				}
				else if(board.checkChar(board.getPlayerX(),board.getPlayerY()-1,'R') == true){
					board.printCase();
				}
				board.playerLeft(player.getPlayer());
				s = "";
				enemyMove();
			}
			break;
		case "3":
		case "right":
			if (board.playerMoveCheck(board.getPlayerX(),board.getPlayerY() + 1) == true) {
				if(board.checkChar(board.getPlayerX(),board.getPlayerY()+1,'B') == true){
					player.giveAmmo();
				}
				else if(board.checkChar(board.getPlayerX(),board.getPlayerY()+1,'I') == true){
					invincibility = true;
				}
				else if(board.checkChar(board.getPlayerX(),board.getPlayerY()+1,'R') == true){
					board.printCase();
				}
				board.playerRight(player.getPlayer());
				s = "";
				enemyMove();
			}
			break;
		case "4":
		case "down":
			if (board.playerMoveCheck(board.getPlayerX() + 1,board.getPlayerY()) == true) {
				if(board.checkChar(board.getPlayerX()+1,board.getPlayerY(),'B') == true){
					player.giveAmmo();
				}
				else if(board.checkChar(board.getPlayerX()+1,board.getPlayerY(),'I') == true){
					invincibility = true;
				}
				else if(board.checkChar(board.getPlayerX()+1,board.getPlayerY(),'R') == true){
					board.printCase();
				}
				board.playerDown(player.getPlayer());
				s = "";
				enemyMove();
			}
		case "5":
		case "options":
			s = "move";

			break;
		default:
			break;
		}
		return s;
	}
	
	public String shoot(String x) {
		String s ="";
		switch(x) {
		case "1":
		case "up":
			if(player.shoot() == true) {
				board.shootUp(0);
				int[] location = board.position();
				for(int i = 0; i < arr.length; i ++){
					for(int j = 1; j < location.length; j+=2){
						if(arr[i].getEnemyY() == location[j]){
							for(int k = 0; k < location.length;k++){
								if(arr[i].getEnemyX() == location[k]){
									arr[i].subhealth();
									arr[i].setEnemyChar(' ');
								}
							}
							
						}
					}
					
				}
		
			}
			s = "";
			enemyMove();
			break;
		case "2":
		case "left":
			if(player.shoot() == true) {
				board.shootLeft(0);
				int[] location = board.position();
				for(int i = 0; i < arr.length; i ++){
					for(int k = 0; k < location.length; k+=2){
						if(arr[i].getEnemyX() == location[k]){
							for(int j = 0; j < location.length;j++){
								if(arr[i].getEnemyY() == location[j]){
									arr[i].subhealth();
									arr[i].setEnemyChar(' ');
								}
							}
							
						}
					}
					
				}
		
			}
			s ="";
			enemyMove();
			break;
		case "3":
		case "right":
			if(player.shoot() == true) {
				board.shootRight(0);
				int[] location = board.position();
				for(int i = 0; i < arr.length; i ++){
					for(int k = 0; k < location.length; k+=2){
						if(arr[i].getEnemyX() == location[k]){
							for(int j = 0; j < location.length;j++){
								if(arr[i].getEnemyY() == location[j]){
									arr[i].subhealth();
									arr[i].setEnemyChar(' ');
								}
							}
							
						}
					}
					
				}
		
			}
			s = "";
			enemyMove();
			break;
		case "4":
		case "down":
			if(player.shoot() == true) {
				board.shootDown(0);
				int[] location = board.position();
				for(int i = 0; i < arr.length; i ++){
					for(int j = 1; j < location.length; j+=2){
						if(arr[i].getEnemyY() == location[j]){
							for(int k = 0; k < location.length;k++){
								if(arr[i].getEnemyX() == location[k]){
									arr[i].subhealth();
									arr[i].setEnemyChar(' ');
								}
							}
							
						}
					}
					
				}
		
			}
			s = "";
			enemyMove();
			break;
		case "5":
		case "options":
			s = "options";
		default:
			break;
		}
		return s;
	}

	/**
	 * This method will let the user look at any direction
	 */
	public String look(String t) {
		String s ="";
		switch(t) {
		case "1":
		case "up":
				board.lookUp();
				s = "";
			break;
		case "2":
		case "left":
				board.lookLeft();
				s = "";
			break;
		case "3":
		case "right":
				board.lookRight();
				s = "";
			break;
		case "4":
		case "down":
				board.lookDown();
				s = "";
			break;
		case "5":
		case "options":
			s = "options";
			break;
		default:
			break;
		}
		return s;
	}

	/**
	 * This method will return true if player shoots an enemy, else return false
	 * 
	 * @return
	 */
	public boolean shoot() {
		return false;

	}

	public String getBoard() {
		return board.toString();
	}
	
	public char[][] board() {
		return board.getBoard();
	}
}
