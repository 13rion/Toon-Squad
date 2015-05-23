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

	/**
	 * This constructor will instantiate Player, Enemy, and Board, as well as
	 */
	public GameEngine() {
		board = new Board();
		player = new Player();
		R = new Random();
		arr = new Enemy[6];
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
	
	public void resetPlayer(){
		board.setPlayer(8, 0, player.getPlayer());
	}

	public void enemyMove() {
		for (int i = 0; i < arr.length; i++) {
			kill(arr[i]);
		}
		for (int i = 0; i < arr.length; i++) {
			int rndm = R.nextInt(4);
			randomEnemy(arr[i],rndm,0);
		}
	}

	public void randomEnemy(Enemy arr2, int x, int stop) {
		if(stop == 4){
			
		}else{
			switch(x){
				//Up
				case 0:
					if (board.enemyMoveCheck(arr2.getEnemyX() - 1, arr2.getEnemyY()) == true) {
						board.enemyUp(arr2.getEnemyX(), arr2.getEnemyY(), arr2.getEnemy());
						arr2.setEnemyX(arr2.getEnemyX() - 1);
						arr2.setEnemyY(arr2.getEnemyY());
					}else{
						x = R.nextInt(4 - 1) + 1;
						randomEnemy(arr2, x, stop+1);
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
						randomEnemy(arr2,x,stop+1);
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
						randomEnemy(arr2,x,stop+1);
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
						randomEnemy(arr2, x,stop+1);
					}
					break;
				default:
					break;
			}
		}
	}

	public void kill(Enemy arr2){
		
		if(board.killPlayerUp(arr2.getEnemyX()-1,arr2.getEnemyY()) == true){
			player.subLives();
			resetPlayer();
		}else if(board.killPlayerDown(arr2.getEnemyX()+1,arr2.getEnemyY()) == true){
			player.subLives();
			resetPlayer();
		}else if(board.killPlayerRight(arr2.getEnemyX(),arr2.getEnemyY()+1) == true){
			player.subLives();
			resetPlayer();
		}else if(board.killPlayerLeft(arr2.getEnemyX(),arr2.getEnemyY()-1) == true){
			player.subLives();
			resetPlayer();
		}
	}
	
	public String start(String x) {
		String s = "";
		switch(x) {
		case "1": 
		case "start":
		case "s":
			s = "start";
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
			break;
		default:
			break;
		}
		return s;

	}
	
	public String action(String x) {
		String s = "";
		switch(x) {
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
		case "save":
			s = "save";
			break;
		default:
			break;
		}
		return s;

	}

	public void move(String x) {
		switch(x) {
		case "1":
		case "up":
		case "u":
			if (board.playerMoveCheck(board.getPlayerX() - 1, board.getPlayerY()) == true) {
				board.playerUp(player.getPlayer());
				enemyMove();
			}
			break;
		case "2":
		case "left":
		case "l":
			if (board.playerMoveCheck(board.getPlayerX(), board.getPlayerY() - 1) == true) {
				board.playerLeft(player.getPlayer());
				enemyMove();
			}
			break;
		case "3":
		case "right":
		case "r":
			if (board.playerMoveCheck(board.getPlayerX(), board.getPlayerY() + 1) == true) {
				board.playerRight(player.getPlayer());
				enemyMove();
			}
			break;
		case "4":
		case "down":
		case "d":
			if (board.playerMoveCheck(board.getPlayerX() + 1, board.getPlayerY()) == true) {
				board.playerDown(player.getPlayer());
				enemyMove();
			}
			break;
		default:
			break;
		}
	}
	
	public void shoot(String x) {
		switch(x) {
		case "1":
		case "up":
		case "u":
			if(player.shoot() == true) {
				board.shootUp(board.getPlayerY());
				enemyMove();
			}
			break;
		case "2":
		case "left":
		case "l":
			if(player.shoot() == true) {
				board.shootLeft(board.getPlayerX());
				enemyMove();
			}
			break;
		case "3":
		case "right":
		case "r":
			if(player.shoot() == true) {
				board.shootRight(board.getPlayerX());
				enemyMove();
			}
			break;
		case "4":
		case "down":
		case "d":
			if(player.shoot() == true) {
				board.shootDown(board.getPlayerY());
				enemyMove();
			}
			break;
		default:
			break;
		}
	}

	/**
	 * This method will let the user look at any direction
	 */
	public void look() {

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
}
