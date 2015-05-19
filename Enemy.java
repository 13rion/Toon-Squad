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
	
	private int enemyX;
	
	private int enemyY;

	/**
	 * This constructor will set String enemy.
	 */
	public Enemy() {
		super();
		enemyChar = 'E';
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
	
	//GETTERS & SETTERS
	public void setEnemyX(int x){
		enemyX = x;
	}
	
	public void setEnemyY(int y){
		enemyY = y;
	}
	
	/**
	 * This method will return the String enemy
	 * @return
	 */
	public char getEnemy() {
		return enemyChar;
	}
	
	public int getEnemyX(){
		return enemyX;
	}
	
	public int getEnemyY(){
		return enemyY;
	}

}
