/**
 * 
 */
package edu.cpp.cs.cs141.teamproject;

import java.util.Random;
import java.io.Serializable;

/**
 * @author Toon Squad
 *
 */
public class GameEngine implements Serializable {

	/**
	 * 
	 */
	private Player player;
	/**
	 * 
	 */
	private Board board;

	private Random R;
	private Enemy[] enemies;
	
	private boolean kill;

	private boolean invincibility;
	
	private int invin = 0;
	
	private int level;
	
	private boolean hardmode;

	/**
	 * This constructor will instantiate Player, Enemy, and Board, as well as
	 */
	public GameEngine() {
		board = new Board();
		player = new Player();
		R = new Random();
		level = 1;
		kill = false;
		invincibility = false;
		set();
	}
	
	public GameEngine(int x, String mode, boolean hard) {
		board = new Board();
		player = new Player();
		R = new Random();
		level = 1 + x;
		kill = false;
		invincibility = false;
		hardmode = hard;
		board.setMode(mode);
		set();
	}

	public void set() {
		setPlayer();
		setEnemies();
	}
	
	public void setPlayer() {
		board.setPlayer(8, 0, player.getPlayer());
	}
	
	public void setEnemies() {
		enemies = new Enemy[5 + level];
		for (int i = 0; i < enemies.length; i++) {
			enemies[i] = new Enemy();
			board.setEnemy(enemies[i].getEnemy());
			enemies[i].setEnemyX(board.getEnemyX());
			enemies[i].setEnemyY(board.getEnemyY());
		}
	}
	
	public boolean invinc() {
		return invincibility;
	}
	
	public int invincNum() {
		int num = 6-invin;
		return num;
	}
	
	public boolean checkKill(){
		return kill;
	}

	public void enemyMove() {
		if(invincibility == true) {
			invin++;
			if(invin > 5){
				invincibility = false;
			}
		} else {
			for (int i = 0; i < enemies.length; i++) {
				if (enemies[i].alive() == true) {
					kill(enemies[i]);
				}
			}
		}
		for (int j = 0; j < enemies.length; j++) {
			if (kill == false) {
				if (enemies[j].alive() == true) {
					if(hardmode == true){
						hardMode(enemies[j]);
					}else{
						R = new Random();
						int rndm = R.nextInt(4);
						randomEnemy(enemies[j], rndm);
					}
				}
			}
		}
		kill = false;
	}

	public void randomEnemy(Enemy enemies2, int x) {
		if((board.enemyMoveCheck(enemies2.getEnemyX() - 1, enemies2.getEnemyY()) == false) &&
			(board.enemyMoveCheck(enemies2.getEnemyX(), enemies2.getEnemyY() - 1) == false)	&&
			(board.enemyMoveCheck(enemies2.getEnemyX(), enemies2.getEnemyY() + 1) == false) &&
			(board.enemyMoveCheck(enemies2.getEnemyX() + 1, enemies2.getEnemyY()) == false)) {
		} else {
			switch(x){
				//Up
				case 0:
					if (board.enemyMoveCheck(enemies2.getEnemyX() - 1, enemies2.getEnemyY()) == true) {
						board.enemyUp(enemies2.getEnemyX(), enemies2.getEnemyY(), enemies2.getEnemy());
						enemies2.setEnemyX(enemies2.getEnemyX() - 1);
						enemies2.setEnemyY(enemies2.getEnemyY());
					}else{
						x = R.nextInt(4 - 1) + 1;
						randomEnemy(enemies2, x);
					}
					break;
				//Left
				case 1:
					if (board.enemyMoveCheck(enemies2.getEnemyX(), enemies2.getEnemyY() - 1) == true) {
						board.enemyLeft(enemies2.getEnemyX(), enemies2.getEnemyY(), enemies2.getEnemy());
						enemies2.setEnemyX(enemies2.getEnemyX());
						enemies2.setEnemyY(enemies2.getEnemyY() - 1);
					}else{
						while(x == 1) {
							x = R.nextInt(4);
						}
						randomEnemy(enemies2,x);
					}
					break;
				//Right
				case 2:
					if (board.enemyMoveCheck(enemies2.getEnemyX(), enemies2.getEnemyY() + 1) == true) {
						board.enemyRight(enemies2.getEnemyX(), enemies2.getEnemyY(),enemies2.getEnemy());
						enemies2.setEnemyX(enemies2.getEnemyX());
						enemies2.setEnemyY(enemies2.getEnemyY() + 1);
					}else{
						x = R.nextInt(4);
						while(x == 2) {
							x = R.nextInt(4);
						}
						randomEnemy(enemies2,x);
					}
					break;
				//Down
				case 3:
					if (board.enemyMoveCheck(enemies2.getEnemyX() + 1, enemies2.getEnemyY()) == true) {
						board.enemyDown(enemies2.getEnemyX(), enemies2.getEnemyY(), enemies2.getEnemy());
						enemies2.setEnemyX(enemies2.getEnemyX() + 1);
						enemies2.setEnemyY(enemies2.getEnemyY());
					} else {
						x = R.nextInt(4 - 1);
						randomEnemy(enemies2, x);
					}
					break;
				default:
					break;
			}
		}
	}
	
	public void hardMode(Enemy enemies2){
		String pos = board.enemyRowCheck(enemies2.getEnemyX(), enemies2.getEnemyY());
		if(pos == ""){
			R = new Random();
			int rndm = R.nextInt(4);
			randomEnemy(enemies2, rndm);
		}else{
			switch(pos){
			case "up":
				if (board.enemyMoveCheck(enemies2.getEnemyX() - 1, enemies2.getEnemyY()) == true) {
					board.enemyUp(enemies2.getEnemyX(), enemies2.getEnemyY(), enemies2.getEnemy());
					enemies2.setEnemyX(enemies2.getEnemyX() - 1);
					enemies2.setEnemyY(enemies2.getEnemyY());
				}
				break;
			case "down":
				if (board.enemyMoveCheck(enemies2.getEnemyX() + 1, enemies2.getEnemyY()) == true) {
					board.enemyDown(enemies2.getEnemyX(), enemies2.getEnemyY(), enemies2.getEnemy());
					enemies2.setEnemyX(enemies2.getEnemyX() + 1);
					enemies2.setEnemyY(enemies2.getEnemyY());
				}
				break;
			case "left":
				if (board.enemyMoveCheck(enemies2.getEnemyX(), enemies2.getEnemyY() - 1) == true) {
					board.enemyLeft(enemies2.getEnemyX(), enemies2.getEnemyY(), enemies2.getEnemy());
					enemies2.setEnemyX(enemies2.getEnemyX());
					enemies2.setEnemyY(enemies2.getEnemyY() - 1);
				}
				break;
			case "right":
				if (board.enemyMoveCheck(enemies2.getEnemyX(), enemies2.getEnemyY() + 1) == true) {
					board.enemyRight(enemies2.getEnemyX(), enemies2.getEnemyY(),enemies2.getEnemy());
					enemies2.setEnemyX(enemies2.getEnemyX());
					enemies2.setEnemyY(enemies2.getEnemyY() + 1);
				}
				break;
			default:
				break;	
			}
		}
	}

	public boolean kill(Enemy enemies2) {
		if (board.killPlayerUp(enemies2.getEnemyX() - 1, enemies2.getEnemyY()) == true) {
			player.subLives();
			for (int i = 0; i < enemies.length; i++) {
				board.checkDeath(enemies[i]);
			}
			setPlayer();
			kill = true;
		} else if (board.killPlayerDown(enemies2.getEnemyX() + 1, enemies2.getEnemyY()) == true) {
			player.subLives();
			for (int i = 0; i < enemies.length; i++) {
				board.checkDeath(enemies[i]);
			}
			setPlayer();
			kill = true;
		} else if (board
				.killPlayerRight(enemies2.getEnemyX(), enemies2.getEnemyY() + 1) == true) {
			player.subLives();
			for (int i = 0; i < enemies.length; i++) {
				board.checkDeath(enemies[i]);
			}
			setPlayer();
			kill = true;
		} else if (board.killPlayerLeft(enemies2.getEnemyX(), enemies2.getEnemyY() - 1) == true) {
			player.subLives();
			for (int i = 0; i < enemies.length; i++) {
				board.checkDeath(enemies[i]);
			}
			setPlayer();
			kill = true;
		}
		return kill;
	}
	
	public String start(String t) {
		String s = "";
		switch(t) {
		case "1": 
		case "start":
		case "s":
			s = "start";
			board.setMode("normal");
			break;
		case "2": 
		case "load":
		case "l":
			s = "load";
			break;
		case "3": 
		case "hard":
		case "h":
			s = "hard";
			board.setMode("normal");
			hardmode = true;
			break;
		case "4": 
		case "debug":
		case "d":
			s = "debug";
			board.setMode("debug");
			break;
		default:
			break;
		}
		return s;

	}
	
	public String level(String t) {
		String s = " ";
		switch(t) {
		case "1": 
		case "continue":
		case "c":
			s = "continue";
			break;
		case "2": 
		case "save":
			s = "save";
			break;
		case "3": 
		case "quit":
		case "q":
			s = "quit";
			break;
		default:
			s = "";
			break;
		}
		return s;

	}
	
	public String action(String t) {
		String s = " ";
		switch(t) {
		case "1":
		case "move":
		case "m":
			s = "move";
			break;
		case "2":
		case "shoot":
		case "s":
			s = "shoot";
			break;
		case "3":
		case "look":
		case "l":
			s = "look";
			break;
		case "4":
		case "check":
		case "c":
			s = "check";
			break;
		case "5":
		case "save":
			s = "save";
			break;
		case "6":
		case "debug":
		case "d":
			if(board.getMode().equals("normal") || board.getMode().equals("")) {
				s = "debug";
				board.setMode("debug");
			} else if(board.getMode().equals("debug")) {
				s = "normal";
				board.setMode("normal");
			}
			break;
		case "7":
		case "quit":
		case "q":
			s = "quit";
			break;
		default:
			s = "";
			break;
		}
		return s;

	}
	
	public boolean checkRoom() {
		return board.checkRoom();
	}

	public String move(String x) {
		String s = " ";
		switch (x) {
		case "1":
		case "up":
			if (board.playerMoveCheck(board.getPlayerX() - 1,board.getPlayerY()) == true) {
				if(board.checkChar(board.getPlayerX()-1,board.getPlayerY(),'B') == true) {
					player.giveAmmo();
				}
				else if(board.checkChar(board.getPlayerX()-1,board.getPlayerY(),'I') == true) {
					invincibility = true;
				}
				else if(board.checkChar(board.getPlayerX()-1,board.getPlayerY(),'R') == true) {
					board.setRadar(true);
				}
				board.playerUp(player.getPlayer());
				s = "1";
				enemyMove();
			}
			break;
		case "2":
		case "left":
			if (board.playerMoveCheck(board.getPlayerX(),board.getPlayerY() - 1) == true) {
				if(board.checkChar(board.getPlayerX(),board.getPlayerY()-1,'B') == true){
					player.giveAmmo();
				}
				else if(board.checkChar(board.getPlayerX(),board.getPlayerY()-1,'I') == true){
					invincibility = true;
				}
				else if(board.checkChar(board.getPlayerX(),board.getPlayerY()-1,'R') == true){
					board.setRadar(true);
				}
				board.playerLeft(player.getPlayer());
				s = "2";
				enemyMove();
			}
			break;
		case "3":
		case "right":
			if (board.playerMoveCheck(board.getPlayerX(),board.getPlayerY() + 1) == true) {
				if(board.checkChar(board.getPlayerX(),board.getPlayerY()+1,'B') == true){
					player.giveAmmo();
				}
				else if(board.checkChar(board.getPlayerX(),board.getPlayerY()+1,'I') == true){
					invincibility = true;
				}
				else if(board.checkChar(board.getPlayerX(),board.getPlayerY()+1,'R') == true){
					board.setRadar(true);
				}
				board.playerRight(player.getPlayer());
				s = "3";
				enemyMove();
			}
			break;
		case "4":
		case "down":
			if (board.playerMoveCheck(board.getPlayerX() + 1,board.getPlayerY()) == true) {
				if(board.checkChar(board.getPlayerX()+1,board.getPlayerY(),'B') == true){
					player.giveAmmo();
				}
				else if(board.checkChar(board.getPlayerX()+1,board.getPlayerY(),'I') == true){
					invincibility = true;
				}
				else if(board.checkChar(board.getPlayerX()+1,board.getPlayerY(),'R') == true){
					board.setRadar(true);
				}
				board.playerDown(player.getPlayer());
				s = "4";
				enemyMove();
			}
		case "5":
		case "options":
			s = "move";
			break;
		default:
			s = "";
			break;
		}
		return s;
	}
	
	public String shoot(String x) {
		String s =" ";
		switch(x) {
		case "1":
		case "up":
			if(player.shoot() == true) {
				board.shootUp();
				int[] location = board.position();
				for (int i = 0; i < enemies.length; i++) {
					if (enemies[i].getEnemyY() == location[1] && enemies[i].getEnemyX() == location[0]) {
						enemies[i].subhealth();
						enemies[i].setEnemyChar(' ');
						s = "kill";
					}
				}
		
			}
			enemyMove();
			break;
		case "2":
		case "left":
			if(player.shoot() == true) {
				board.shootLeft();
				int[] location = board.position();
				for (int i = 0; i < enemies.length; i++) {
					if (enemies[i].getEnemyX() == location[0] && enemies[i].getEnemyY() == location[1]) {
						enemies[i].subhealth();
						enemies[i].setEnemyChar(' ');
						s = "kill";
					}
				}
		
			}
			enemyMove();
			break;
		case "3":
		case "right":
			if(player.shoot() == true) {
				board.shootRight();
				int[] location = board.position();
				for (int i = 0; i < enemies.length; i++) {
					if (enemies[i].getEnemyX() == location[0] && enemies[i].getEnemyY() == location[1]) {
						enemies[i].subhealth();
						enemies[i].setEnemyChar(' ');
						s = "kill";
					}
				}
		
			}
			enemyMove();
			break;
		case "4":
		case "down":
			if(player.shoot() == true) {
				board.shootDown();
				int[] location = board.position();
				for (int i = 0; i < enemies.length; i++) {
					if (enemies[i].getEnemyY() == location[1] && enemies[i].getEnemyX() == location[0]) {
						enemies[i].subhealth();
						enemies[i].setEnemyChar(' ');
						s = "kill";
					}
				}
		
			}
			enemyMove();
			break;
		case "5":
		case "options":
			s = "options";
		default:
			s = "";
			break;
		}
		return s;
	}

	/**
	 * This method will let the user look at any direction
	 */
	public String look(String t) {
		String s =" ";
		switch(t) {
		case "1":
		case "up":
		case "u":
			if(board.lookUp() == true) {
				s = "enemy";
			}
			break;
		case "2":
		case "left":
		case "l":
			if(board.lookLeft() == true) {
				s = "enemy";
			}
			break;
		case "3":
		case "right":
		case "r":
			if(board.lookRight() == true) {
				s = "enemy";
			}
			break;
		case "4":
		case "down":
		case "d":
			if(board.lookDown() == true) {
				s = "enemy";
			}
			break;
		case "5":
		case "options":
		case "o":
			s = "options";
			break;
		default:
			s = "";
			break;
		}
		return s;
	}

	/**
	 * This method will return true if player shoots an enemy, else return false
	 * 
	 * @return
	 */
	public boolean shoot() {
		return false;

	}

	public String getBoard() {
		return board.toString();
	}
	
	public String getMode() {
		return board.getMode();
	}
	
	public boolean getHardMode(){
		return hardmode;
	}
	
	public boolean getMove(){
		return board.avaliableMove();
	}
	
	public char[][] board() {
		return board.getBoard();
	}
	
	public int playerLives(){
		return player.getLives();
	}
	
	public int playerAmmo(){
		return player.getAmmo();
	}
	
	public int getLevel() {
		return level;
	}
}
