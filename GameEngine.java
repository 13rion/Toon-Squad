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
			board.enemySet(arr[i].getEnemy());
			arr[i].setEnemyX(board.getEnemyX());
			arr[i].setEnemyY(board.getEnemyY());
		}
	}

	public void enemyMove() {
		for (int i = 0; i < arr.length; i++) {
			int rndm = R.nextInt(3);
			randomEnemy(arr[i],rndm);

		}
	}

	public void randomEnemy(Enemy arr2, int x) {
		switch(x){
			case 0:
				if (board.enemyMoveCheck(arr2.getEnemyX() - 1, arr2.getEnemyY()) == true) {
					board.enemyUp(arr2.getEnemyX(), arr2.getEnemyY(), arr2.getEnemy());
					arr2.setEnemyX(arr2.getEnemyX() - 1);
					arr2.setEnemyY(arr2.getEnemyY());
				}else{
					randomEnemy(arr2, x+1);
				}
				break;
			case 1:
				if (board.enemyMoveCheck(arr2.getEnemyX(), arr2.getEnemyY() - 1) == true) {
					board.enemyLeft(arr2.getEnemyX(), arr2.getEnemyY(), arr2.getEnemy());
					arr2.setEnemyX(arr2.getEnemyX());
					arr2.setEnemyY(arr2.getEnemyY() - 1);
				}else{
					randomEnemy(arr2,x+1);
				}
				break;
			case 2:
				if (board.enemyMoveCheck(arr2.getEnemyX(), arr2.getEnemyY() + 1) == true) {
					board.enemyRight(arr2.getEnemyX(), arr2.getEnemyY(),arr2.getEnemy());
					arr2.setEnemyX(arr2.getEnemyX());
					arr2.setEnemyY(arr2.getEnemyY() + 1);
				}else{
					randomEnemy(arr2,x+1);
				}
				break;
			case 3:
				if (board.enemyMoveCheck(arr2.getEnemyX() + 1, arr2.getEnemyY()) == true) {
					board.enemyDown(arr2.getEnemyX(), arr2.getEnemyY(), arr2.getEnemy());
					arr2.setEnemyX(arr2.getEnemyX() + 1);
					arr2.setEnemyY(arr2.getEnemyY());
				} else {
					randomEnemy(arr2, x-3);
				}
				break;
			default:
				break;
		}
	}
	
	public String action(int i) {
		String s = "";
		switch(i) {
		case 1:
			s = "move";
			break;
		case 2:
			s = "shoot";
			break;
		case 3:
			s = "load";
			break;
		case 4:
			s = "save";
			break;
		case 5:
			s = "debug";
		default:
			break;
		}
		return s;

	}

	public void move(int i) {
		switch(i) {
		case 1:
			if (board.playerMoveCheck(board.getPlayerX() - 1, board.getPlayerY()) == true) {
				board.playerUp(player.getPlayer());
				enemyMove();
			}
			break;
		case 2:
			if (board.playerMoveCheck(board.getPlayerX(), board.getPlayerY() - 1) == true) {
				board.playerLeft(player.getPlayer());
				enemyMove();
			}
			break;
		case 3:
			if (board.playerMoveCheck(board.getPlayerX(), board.getPlayerY() + 1) == true) {
				board.playerRight(player.getPlayer());
				enemyMove();
			}
			break;
		case 4:
			if (board.playerMoveCheck(board.getPlayerX() + 1, board.getPlayerY()) == true) {
				board.playerDown(player.getPlayer());
				enemyMove();
			}
			break;
		default:
			break;
		}
	}
	
	public void shoot(int i) {
		switch(i) {
		case 1:
			if(player.shoot() == true) {
				board.shootUp(board.getPlayerY());
			}
			break;
		case 2:
			if(player.shoot() == true) {
				board.shootLeft(board.getPlayerX());
			}
			break;
		case 3:
			if(player.shoot() == true) {
				board.shootRight(board.getPlayerX());
			}
			break;
		case 4:
			if(player.shoot() == true) {
				board.shootDown(board.getPlayerY());
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
