/**
 * 
 */
package edu.cpp.cs.cs141.teamproject;

/**
 * @author Toon Squad
 *
 */
public class Enemy extends ActiveAgents {

	private char enemyChar;

	/**
	 * This constructor will set String enemy.
	 */
	public Enemy() {
		super();
		enemyChar = 'E';
	}
	/**
	 * This method will return the String enemy
	 * @return
	 */
	public char getEnemy() {
		return enemyChar;
	}
	/**
	 * This method will pick randomly where the enemy to go.
	 * @return
	 */
	public int random() {
		return 0;

	}
	
	public void move(){ 
		
	}
	
	/**
	 * This method will kill the player if the enemy has a chance too.
	 * 
	 * @return true if enemy can kill, false if cannot
	 */
	public boolean kill() {
		return false;
	}

}
