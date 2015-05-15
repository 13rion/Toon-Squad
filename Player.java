/**
 * 
 */
package edu.cpp.cs.cs141.teamproject;

/**
 * @author Toon Squad
 *
 */
public class Player extends ActiveAgents {

	private int lives;

	private char playerChar;

	private Gun gun;

	/**
	 * The constructor will instantiate gun and set the lives and player string
	 */
	public Player() {
		super();
		playerChar = 'P';
		lives = 3;
		gun = new Gun();
	}

	/**
	 * This method will return the player string
	 * 
	 * @return player string
	 */
	public char getPlayer() {
		return playerChar;

	}

	/**
	 * This method will check if player is able to shoot their gun.
	 * 
	 * @return true if player has ammo, false if not
	 */
	public boolean shoot() {
		return false;
	}

	public int getAmmo() {
		return gun.getAmmo();

	}

	public int getLives() {
		return lives;

	}

}
