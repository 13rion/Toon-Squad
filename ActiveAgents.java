/**
 * 
 */
package edu.cpp.cs.cs141.teamproject;

import java.io.Serializable;

/**
 * @author Toon Squad
 *
 */
public class ActiveAgents implements Serializable {

	int hp;

	public ActiveAgents() {
		hp = 1;
	}

	public int getHp() {
		return hp;
	}

}
