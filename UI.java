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
		System.out.println("1. Start Game \n2. Load Game \n3. Debug Mode");
		String x = ge.start(sc.nextLine());
		if(x != "") {
			options(x);
		} else {
			start();
		}
	}
	
	public void play() {
		System.out.println(ge.getBoard());
		System.out.println("1. Move \n2. Shoot \n3. Look \n4. Save");
		options(ge.action(sc.nextLine()));
		play();		
	}
	
	/*
	 * This method will print out the options to user.
	 */
	private void options(String s) {
		if(s == "start") {
			play();
		}
		if(s == "move") {
			System.out.println("1. Move Up 2. Move Left 3. Move Right 4. Move Down");
			ge.move(sc.nextLine());
		}
		if(s == "shoot") {
			System.out.println("1. Shoot Up 2. Shoot Left 3. Shoot Right 4. Shoot Down");
			ge.shoot(sc.nextLine());
		}
	}

	/*
	 *This method will print out the outcomes. 
	 */
	private void response() {

	}
}
