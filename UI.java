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
		welcome();
		start();
	}
	
	/**
	 * This method houses the welcome message for the game. It will start when the game starts.
	 */
	public void welcome() {
		String message = "WELCOME TO NINJA ASYLUM";
        for (int k=0; k < message.length( ); k++)
        {
              System.out.print(message.charAt(k)+ " ");

              try {
				Thread.sleep (100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        System.out.print("\n\n");
	}
	
	/**
	 * This method will start the game
	 */
	public void start() {
		System.out.println("1. Start Game \n2. Load Game \n3. Debug Mode");
		String x = ge.start(sc.nextLine());
		if(x != "") {
			lookCounter = 0;
			playerLives = 3;
			options(x);
		} else {
			start();
		}
	}
	
	public void nextLevel() {
		System.out.println("\n1. Continue to next level \n2. Save Game \n3. Quit Game");
		String s = ge.nextLevel(sc.nextLine());
		if(s != "") {
			lookCounter = 0;
			playerLives = 3;
			options(s);
		} else {
			start();
		}
	}
	
	public void play() {
		level = ge.getLevel();
		if(ge.getMove() == false){
			System.out.println("NINJAS CORNERED YOU! \nYOU WILL DIE A SLOW PAINFUL DEATH");
			System.out.println(ge.getBoard());
			playerDead();
		}else{
			level = ge.getLevel();
			if(ge.playerLives() > 0) {
				if (playerLives > ge.playerLives()) {
					playerLives--;
					System.out.println("A NINJA KILLED YOU!");
				}
				if (ge.getHardMode() == true) {
					System.out.println("Level " + level + " - Hard");
				} else {
					System.out.println("Level " + level + " - Normal");
				}
			System.out.println(ge.getBoard());
			if (ge.invinc() == true) {
				System.out.println("YOU'RE INVINCIBLE FOR "
						+ ge.invincNum() + " TURNS");
			}
			/*if (ge.invinc() == true) {
			System.out.println("ACTIVE STATUS EFFECT(S): ");
			System.out.println("Invincible For: "
					+ ge.invincNum() + " TURNS\n");
			}*/
			System.out.println("Lives: "+ ge.playerLives());
			System.out.println("Bullets: "+ ge.playerAmmo());
			System.out.println("1. Move \n2. Shoot \n3. Look \n4. Check \n5. Save Game \n6. Debug/Exit Debug \n7. Quit Game");				
			options(ge.action(sc.nextLine()));
			}else{
				playerDead();
			}
		}
	}
	
	public void playerDead() {
		System.out.println("\nYOU FAILED YOUR MISSION! \nBETTER LUCK NEXT TIME");
		
	}
	
	public void options(String s) {
		switch(s) {
		case "start":
		case "startdebug":
			System.out.println("Game Difficulty:\n1. Easy 2. Normal 3. Hard");
			String t = sc.nextLine();
			switch(t) {
			case "1":
			case "easy":
				System.out.println("This difficulty was so easy we didn't even implement it." 
								+ "\n" + "We'll take you to the next one up, Normal.\n");
				if(s.equalsIgnoreCase("startdebug")) {
					System.out.println("Debug Mode Activated.");
				}
				play();
				break;
			case "2":
			case "normal":
				if(s.equalsIgnoreCase("startdebug")) {
					System.out.println("Debug Mode Activated.");
				}
				play();
				break;
			case "3":
			case "hard":
				ge.setHardMode();
				if(s.equalsIgnoreCase("startdebug")) {
					System.out.println("Debug Mode Activated.");
				}
				play();
				break;
			default:
				break;
			}
			
			break;
		case "continue":
			play();
			break;
		case "debug":
			System.out.println("Debug Mode Activated.");
			play();
			break;
		case "default":
			System.out.println("Debug Mode Deactivated.");
			play();
			break;
		case "move":
			System.out.println("1. Move Up 2. Move Left 3. Move Right 4. Move Down 5. Options");
			String t1 = ge.move(sc.nextLine());
				if(t1.equalsIgnoreCase("")) {
					invalid();
					play();
				} else if(t1.equalsIgnoreCase("nomove")) {
					System.out.println("YOU CANNOT MOVE THERE!");
					play();
				} else if(t1.equalsIgnoreCase("options")) { 
					play();
				} else {
					lookCounter = 0;
					play();
				}
			break;
		case "shoot":
			if(ge.playerAmmo() == 0) {
				System.out.println("YOU HAVE NO BULLETS TO SHOOT");
				play();
			} else {
				System.out.println("1. Shoot Up 2. Shoot Left 3. Shoot Right 4. Shoot Down 5. Options");
				String t2 = ge.shoot(sc.nextLine());
					if(t2.equalsIgnoreCase("")) {
						invalid();
						play();
					} else if(t2.equalsIgnoreCase("kill")) {
						System.out.println("YOU KILLED AN ENEMY!");
						lookCounter = 0;
						play();
					} else if(t2.equalsIgnoreCase("options")) {
						play();
					} else {
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
			} else {
				System.out.println("BRIEFCASE NOT FOUND");
				play();
			}
			break;
		case "look":
			if(lookCounter == 0) {
				System.out.println("1. Look Up 2. Look Left 3. Look Right 4. Look Down 5. Options");
				String t3 = ge.look(sc.nextLine());
				if(t3.equalsIgnoreCase("")) {
					invalid();
					play();
				} else if(t3.equalsIgnoreCase("options")) {
					play();
				} else if(t3.equalsIgnoreCase("enemy")) {
					System.out.println("NINJA AHEAD");
					lookCounter = 1;
					play();
				} else {
					System.out.println("AREA CLEAR");
					lookCounter = 1;
					play();
				}
			} else {
				System.out.println("YOU CAN ONLY USE 'LOOK' ONCE PER TURN");
				play();
			}
			break;
		case "save":
			System.out.print("Filename: ");
			String saveFile = sc.nextLine();
			try {
				File file = new File("Saves.txt");
				file.createNewFile();
				Scanner kb = new Scanner(file);
				int k = 0;
				while (kb.hasNextLine()) {
					String input = kb.nextLine();
					if (input.equalsIgnoreCase(saveFile) || input.isEmpty()) {
						k = -1;
					}
				}
				if(k == 0) {
					FileWriter fw = new FileWriter("Saves.txt", true);
					fw.write(saveFile + "\n");
					fw.close();
				}
				} catch (IOException e) {
					e.printStackTrace();
				}
			
			try {
				FileOutputStream fos = new FileOutputStream(saveFile);
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
				System.out.println("List of Saves:");
				File file = new File("Saves.txt");
				Scanner kb = new Scanner(file);
				while (kb.hasNextLine()) {
					System.out.println(kb.nextLine());
				}
				System.out.println();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.print("Filename: ");
			String loadFile = sc.nextLine();
			try {
				FileInputStream fis = new FileInputStream(loadFile);
				ObjectInputStream ois = new ObjectInputStream(fis); 
				ge = (GameEngine) ois.readObject();
				ois.close();
				System.out.println("Game Loaded\n");
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			play();
			break;
		case "quit":
			System.out.println("OH SO YOU'RE TOO SCARED TO CONTINUE, EH?\nSo be it. You WILL be back.");
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
		System.out.println("\nCONGRATUALATIONS YOU JUST WON A FREE MACINTOSH COMPUTER!");
		System.out.println("Please enter your credit card information below for A FREE MAC!\n");
		switch(level) {
		case 1:
			System.out.println("(This ad is totally legit because it is)");
			break;
		case 2:
			System.out.println("(This add is totally legit, and you should enter your credit card information because why not?)");
			break;
		case 3:
			System.out.println("(This ad is brought to you by Burger King. Have it your way!)");
			break;
		case 4:
			System.out.println("(This ad is brought to you by Linux. Who is using this OS again?)");
			break;
		case 5:
			System.out.println("(This ad does not contain a virus. No siree. Please click to download)");
			break;
		case 6:
			System.out.println("(This ad is...ok I'm sure you're pretty tired of this now)");
			break;
		case 7:
			System.out.println("(I was dropped once when I was a baby y'know. Please donate by entering your credit card information!)");
			break;
		case 8:
			System.out.println("(Is this real life?)");
			break;
		case 9: 
			System.out.println("(Bonus question!: What is the meaning of life?)");
			if(sc.nextLine().equalsIgnoreCase("42")) {
				System.out.println("CORRECT! 5 POINTS TO GRYFFINDOR");
			} else {
				System.out.println("How do you not know this?");
			}
			break;
		case 10:
			System.out.println("(Your Mac will ship between 6 months to NEVER!)");
			break;
		default:
			System.out.println("(This ad is totally legit because it is)");
			break;
		}
		
		ge = new GameEngine(level, ge.getMode(), ge.getHardMode());
		nextLevel();
	}
	
	public void invalid() {
		System.out.println("\nInvalid Entry \nPlease choose one of the following");
	}

}
