/**
 * 
 */
package edu.cpp.cs.cs141.class_project;

import java.io.*;

/**
 * This class represents the attributes/behaviors of a gun. This gun will be used
 * by the player and has the ability to shoot and add to its ammo count.
 *
 */
public class Gun implements Serializable {
	/**
	 * This field represents the {@link Gun}'s {@link #ammo}. Initially {@code 1}, this field
	 * can be decremented when the {@link Player} shoots and can be incremented when the {@link Player}
	 * picks up another bullet.
	 */
	private int ammo;

	/**
	 * A constructor for the {@link Gun}, that sets the {@link #ammo} to {@code 1}.
	 */
	public Gun() {
		ammo = 1;
	}

	/**
	 * This method will return the amount of {@link #ammo} that the {@link Gun} has left.
	 * 
	 * @return ammo
	 * 				The amount of {@link #ammo} that the {@link Gun} has left.
	 */
	public int getAmmo() {
		return ammo;

	}

	/**
	 * This method will only be used if the {@link Player} picked up a bullet. It checks to see
	 * if the {@link #ammo} is equal to {@code 0} and if it is will increment by one.
	 */
	public void addAmmo() {
		if(ammo == 0) {
			++ammo;
		}
	}

	/**
	 * This method will shoot the {@link Gun} and decrement the {@link #ammo} by one.
	 */
	public void shoot() {
		--ammo;
	}
}
