/**
 * 
 */
package edu.cpp.cs.cs141.teamproject;

import java.util.Scanner;

/**
 * @author Toon Squad
 *
 */
public class UI {
	/*
	 * 
	 */
	private Scanner sc;

	/*
	 * 
	 */
	private GameEngine ge;

	/**
	 * This constructor will instantiate the scanner and GameEngine.
	 */
	public UI() {
		ge = new GameEngine();
		sc = new Scanner(System.in);
		start();
	}
	
	/**
	 * This method will start the game
	 */
	public void start() {
		System.out.println(ge.getBoard());
		System.out.println("1. Move Up\n2. Move Left \n3. Move Right \n4. Move Down");
		ge.move(sc.nextInt());
		start();
	}

	/*
	 * This method will print out the options to user.
	 */
	private void options() {
		
	}

	/*
	 *This method will print out the outcomes. 
	 */
	private void response() {

	}
}
