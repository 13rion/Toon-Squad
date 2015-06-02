/**
 * 
 */
package edu.cpp.cs.cs141.teamproject;

import java.io.*;

/**
 * @author Toon Squad
 *
 */
public class Gun implements Serializable {
	/**
	 * This is the ammo for the gun
	 */
	private int ammo;

	/**
	 * The constructor that will set ammo amount.
	 */
	public Gun() {
		ammo = 1;
	}

	/**
	 * The method that will return ammo.
	 * 
	 * @return ammo
	 */
	public int getAmmo() {
		return ammo;

	}

	/**
	 * The method that will add ammo.
	 */
	public void addAmmo() {
		if(ammo == 0) {
			++ammo;
		}
	}

	/**
	 * The method will shoot the players gun
	 * 
	 * @return
	 */
	public void shoot() {
		--ammo;
	}
}
