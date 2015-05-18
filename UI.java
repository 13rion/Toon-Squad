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
		System.out.println("1. Move Up\n 2. Move Left \n 3. Move Right \n 4. Move Down");
		ge.move(sc.nextInt());
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
