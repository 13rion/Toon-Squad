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
		String s = ge.start(sc.nextLine());
		if(s != "") {
			options(s);
		} else {
			start();
		}
	}
	
	public void play() {
		System.out.println(ge.getBoard());
		System.out.println("1. Move \n2. Shoot \n3. Look \n4. Check \n5. Save Game \n6. Debug/Exit Debug");
		options(ge.action(sc.nextLine()));
	}
	
	public void playNoBoard() {
		System.out.println("1. Move \n2. Shoot \n3. Look \n4. Save \n5. Debug");
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
		if(s == "debug") {
			System.out.println("Debug Mode Activated.");
			play();
		}
		if(s == "normal") {
			System.out.println("Debug Mode Deactivated.");
			play();
		}
		if(s == "move") {
			System.out.println("1. Move Up 2. Move Left 3. Move Right 4. Move Down 5. Options");
			if(ge.move(sc.nextLine()) == "") {
				play();
			}
			if(ge.move(sc.nextLine()) == "options") {
				playNoBoard();
			}
		}
		if(s == "shoot") {
			System.out.println("1. Shoot Up 2. Shoot Left 3. Shoot Right 4. Shoot Down 5. Options");
			if(ge.shoot(sc.nextLine()) == "") {
				play();
			}
			if(ge.shoot(sc.nextLine()) == "options") {
				playNoBoard();
			}
		}
		if(s == "check") {
			System.out.println("Checking room...");
			if(ge.checkRoom() == true) {
				System.out.println("Briefcase Found!");
				win();
			}
			if(ge.checkRoom() == false) {
				System.out.println("Briefcase Not Found");
				play();
			}
		}
		if(s == "look") {
			System.out.println("1. Look Up 2. Look Left 3. Look Right 4. Look Down 5. Options");
			String t = ge.look(sc.nextLine());
			if(t == "") {
				play();
			}
			if(t == "options") {
				playNoBoard();
			}
		}
	}
	
	private void win() {
		for(int i = 0; i < 49; i++) {
			System.out.println("YOU WON!!!!!");
		}
		System.out.println("Want a fucking trophy bitch?");
	}

	/*
	 *This method will print out the outcomes. 
	 */
	private void response() {

	}
}
