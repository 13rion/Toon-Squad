/**
 * 
 */
package edu.cpp.cs.cs141.teamproject;

import java.io.*;
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
	
	private int lookCounter;
	private int playerLives;
	private int level;

	/**
	 * This constructor will instantiate the scanner and GameEngine.
	 */
	public UI() {
		ge = new GameEngine();
		sc = new Scanner(System.in);
		level = ge.getLevel();
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
			lookCounter = 0;
			playerLives = 3;
		} else {
			start();
		}
	}
	
	public void nextLevel() {
		System.out.println("\n1. Continue to next level \n2. Save Game \n3. Quit Game");
		String x = ge.level(sc.nextLine());
		if(x != "") {
			options(x);
			lookCounter = 0;
			playerLives = 3;
		} else {
			start();
		}
	}
	
	public void play() {
		if(ge.playerLives() > 0){
			if(ge.invinc() == true){
				System.out.println("YOU'RE INVINCIBLE FOR " +ge.invincNum() +" TURNS");
			}
			if(playerLives > ge.playerLives()){
				playerLives--;
				System.out.println("A NINJA KILLED YOU!");
			}
			System.out.println("Level " + ge.getLevel());
			System.out.println(ge.getBoard());
			System.out.println("Lives: "+ ge.playerLives());
			System.out.println("Bullets: "+ ge.playerAmmo());
			System.out.println("1. Move \n2. Shoot \n3. Look \n4. Check \n5. Save Game \n6. Debug/Exit Debug");				
			options(ge.action(sc.nextLine()));
		}else{
			playerDead();
		}
	}
	
	public void playerDead() {
		System.out.println("\nYOU FAILED YOUR MISSION! \nBETTER LUCK NEXT TIME");
		
	}
	
	public void options(String s) {
		switch(s) {
		case "start":
		case "continue":
			play();
			break;
		case "debug":
			System.out.println("Debug Mode Activated.");
			play();
			break;
		case "normal":
			System.out.println("Debug Mode Deactivated.");
			play();
			break;
		case "move":
			System.out.println("1. Move Up 2. Move Left 3. Move Right 4. Move Down 5. Options");
			String t = ge.move(sc.nextLine());
				if(t == "") {
					invalid();
					play();
				}
				else if(t == "options") { 
					play();
				}else{
					lookCounter = 0;
					play();
				}
			break;
		case "shoot":
			if(ge.playerAmmo() == 0){
				System.out.println("YOU HAVE NO BULLETS TO SHOOT");
				play();
			}else{
				System.out.println("1. Shoot Up 2. Shoot Left 3. Shoot Right 4. Shoot Down 5. Options");
				String t1 = ge.shoot(sc.nextLine());
					if(t1 == "") {
						invalid();
						play();
					}
					else if(t1 == "kill"){
						System.out.println("YOU KILLED AN ENEMY!");
						lookCounter = 0;
						play();
					}
					else if(t1 == "options") {
						play();
					}else{
						System.out.println("YOU FIRED BLANKLY DOWN THE HALL AND HIT THE WALL");
						lookCounter = 0;
						play();
					}
			}
			break;
		case "check":
			System.out.println("CHECKING ROOM...");
			if(ge.checkRoom() == true) {
				System.out.println("BRIEFCASE FOUND!");
				win();
			}
			else{
				System.out.println("BRIEFCASE NOT FOUND");
				play();
			}
			break;
		case "look":
			if(lookCounter == 0){
				System.out.println("1. Look Up 2. Look Left 3. Look Right 4. Look Down 5. Options");
				String t1 = ge.look(sc.nextLine());
				if(t1 == "") {
					invalid();
					play();
				}
				else if(t1 == "options") {
					play();
				}else if(t1 == "enemy"){
					System.out.println("NINJA AHEAD");
					lookCounter = 1;
					play();
				}else{
					System.out.println("AREA CLEAR");
					lookCounter = 1;
					play();
				}
			}else{
				System.out.println("YOU CAN ONLY USE 'LOOK' ONCE PER TURN");
				play();
			}
			break;
		case "save":
			try {
				FileOutputStream fos = new FileOutputStream("save.dat");
				ObjectOutputStream oos = new ObjectOutputStream(fos); 
				oos.writeObject(ge);
				oos.close();
				System.out.println("Saved");
			} catch (IOException e) {
				e.printStackTrace();
			}
			play();
			break;
		case "load":
			try {
				FileInputStream fis = new FileInputStream("save.dat");
				ObjectInputStream ois = new ObjectInputStream(fis); 
				ge = (GameEngine) ois.readObject();
				ois.close();
				System.out.println("Game Loaded\n");
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			play();
			break;
		case "":
			lookCounter = 0;
			invalid();
			play();
			break;
			default:
				break;
		}
	}
	
	public void win() {
		for(int i = 0; i < 9; i++) {
			System.out.println("YOU WON!!!!!");
		}
		System.out.println("Want a fucking trophy bitch?");
		
		level++;
		ge = new GameEngine(level);
		nextLevel();
	}
	
	public void invalid(){
		System.out.println("\nInvalid Entry \nPlease choose one of the following");
	}

}
