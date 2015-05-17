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
	private Enemy enemy;
	/**
	 * 
	 */
	private Board board;
	
	Random R;

	/**
	 * This constructor will instantiate Player, Enemy, and Board, as well as
	 */
	public GameEngine() {
		board = new Board();
		player = new Player();
		enemy = new Enemy();
		R = new Random();
		set();
	}
	
	public void set() {
		board.setPlayer(8, 0, player.getPlayer());
		setEnemies();
	}
	
	public void setEnemies() {
		int rndmX = R.nextInt(8);
		int rndmY = R.nextInt(8);
		if(board.check(rndmX, rndmY) == true) {
			for (int i = 0; i < 6; ++i) {
				board.setEnemies(rndmX, rndmY, enemy.getEnemy());
			}
		} else {
			setEnemies();
		}
	}

	/**
	 * This method will allow the user to save
	 */
	public void save() {

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
