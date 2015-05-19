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
			int rndmX = R.nextInt(8);
			int rndmY = R.nextInt(8);
			board.setEnemies(rndmX, rndmY, arr[i].getEnemy());
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
			if (board.moveCheck(board.getPlayerX() - 1, board.getPlayerY()) == true) {
			board.playerUp(player.getPlayer());

			}
			break;
		case 2:
			if (board.moveCheck(board.getPlayerX(), board.getPlayerY() - 1) == true) {
			board.playerLeft(player.getPlayer());

			}
			break;
		case 3:
			if (board.moveCheck(board.getPlayerX(), board.getPlayerY() + 1) == true) {
			board.playerRight(player.getPlayer());

			}
			break;
		case 4:
			if (board.moveCheck(board.getPlayerX() + 1, board.getPlayerY()) == true) {
			board.playerDown(player.getPlayer());

			}
			break;
		default:
			break;
		}
	}
	
	public void shoot(int i) {
		switch(i) {
		case 1:
			board.shootUp(board.getPlayerY());
			break;
		case 2:
			board.shootLeft(board.getPlayerX());
			break;
		case 3:
			board.shootRight(board.getPlayerX());
			break;
		case 4:
			board.shootDown(board.getPlayerY());
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
