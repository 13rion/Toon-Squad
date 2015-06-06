* CS 141: Introduction to Programming and Problem Solving
 * Professor: Edwin Rodr&iacute;guez
 *
 * Programming Assignment #N
 *
 * <description-of-assignment>
 *
 * Team Toon Squad
 *   <Fortunato Maldonado, Brion Baskerville, Cristian Avina, Tristin Chau>
 */
package edu.cpp.cs.cs141.class_project;

import java.io.Serializable;

/**
 * This class represents the player and the  attributes/behaviors that the player
 * contains. The player begins with 3 lives, is given a gun, the ability to shoot,
 * and can get information about the gun that the player possesses.
 *
 */
public class Player implements Serializable {
	/**
	 * This field represents the {@link Player}'s {@link lives}. Initially {@code 3}, 
	 * this field can be changed when received {@link #subLives()} damage from {@link Enemy}.
	 */
	private int lives;

	/**
	 * This field represents the {@link Player} using the character {@code P}in the game {@link Board}.
	 */
	private char playerChar;

	/**
	 * This field represents an object of the {@link Gun} class in order for the {@link Player}
	 * to {@link #shoot()} an {@link Enemy}.
	 */
	private Gun gun;

	/**
	 * A constructor for the class {@link Player}, that sets the {@link #playerChar} to {@code P},
	 * the {@link #lives} to {@code 3}, and the object {@link Gun}.
	 */
	public Player() {
		playerChar = 'P';
		lives = 3;
		gun = new Gun();
	}

	/**
	 * This method will check if{@link Player} is able to {@link #shoot()} their {@link #gun}.
	 * 
	 * @return true if the {@link #gun} has ammo, else returns false
	 */
	public boolean shoot() {
		boolean x = false;
		if(getAmmo() > 0) {
			x = true;
			gun.shoot();
		}
		return x;
	}
	
	/**
	 * This method decrements the {@link Player}'s {@link #lives}.
	 */
	public void subLives() {
		--lives;
	}

	/**
	 * This method will return the {@link Player}'s character {@code P}.
	 * 
	 * @return {@link Player}'s character {@code P}
	 */
	public char getPlayer() {
		return playerChar;

	}
	
	/**
	 * This method calls the {@link Gun} class to return the {@link ammo} it currently has.
	 *
	 * @return
	 * 			The {@link ammo} that the {@link Gun} has.
	 */
	public int getAmmo() {
		return gun.getAmmo();

	}

	/**
	 * This method calls the {@link Gun} and adds ammo.
	 */
	public void giveAmmo(){
		gun.addAmmo();
	}
	
	/**
	 * This methods returns the {@link Player}'s lives.
	 * 
	 * @return the {@link Player}'s lives
	 */
	public int getLives() {
		return lives;

	}
}
