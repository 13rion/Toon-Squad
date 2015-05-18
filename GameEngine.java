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

	/**
	 * This method will allow the user to save
	 */
	public void save() {

	}
	
	public void move(int i) {
		switch(i) {
		case 1:
			board.playerUp(player.getPlayer());
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		default:
			break;
		}
	}

	/**
	 * This method will let the user move left
	 */
	public void left() {
		
	}

	/**
	 * This method will let the user move right
	 */
	public void right() {
		
	}

	/**
	 * This method will let the user move up
	 */
	public void up() {

	}

	/**
	 * This method will let the user move down
	 */
	public void down() {

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
