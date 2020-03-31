package dev.benjaminc.slimesweeper;

import java.util.Random;

import dev.benjaminc.challenge.RobotAction;

public class SlimeSweeper {
	
	// Set these defaults
	/** The int width of the gameboard */
	private int width = 16;
	/** The int height of the gameboard */
	private int height = 16;
	/** The float percent of the board as slimes */
	private float slimePercent = 0.2f;
	
	// Don't touch these
	/** the int number of bombs */
	private int slimeCount;
	/** the int number of bombs */
	private int slimeMarkedCount;
	
	/** The int width of the gameboard */
	private GameSpace[][] spaces;
	
	/** The int of slimes stepped on */
	private int steppedSlimes;
	/** The boolean of is won */
	private boolean isDone;
	/** the boolean of if it has opened space */
	private boolean didOpenSpace;
	
	/**
	 * Creates a new SlimeSweeper. Use a number <=0 to use default values
	 * @param inWidth			the int width of the game
	 * @param inHeight		the int height of the game
	 * @param inSlimePercent	the float percent of slimes
	 */
	public SlimeSweeper(int inWidth, int inHeight, float inSlimePercent) {
		if(inWidth > 0) {
			this.width = inWidth;
		}
		if(inHeight > 0) {
			this.height = inHeight;
		}
		if(inSlimePercent > 0) {
			this.slimePercent = inSlimePercent;
		}
		
		spaces = new GameSpace[width][height];
		
		didOpenSpace = false;
		
		slimeCount = (int) Math.max((int) (width*height*slimePercent), 1);
		slimeMarkedCount = 0;
		
		Random r = new Random();
		
		int addedSlimes = 0;
		while(addedSlimes < slimeCount) {
			int x = r.nextInt(width);
			int y = r.nextInt(height);
			if(spaces[x][y] == null) {
				spaces[x][y] = new GameSpace();
			}
			GameSpace sp = spaces[x][y];
			
			if(!sp.isSlimey()) {
				sp.setSlimey(true);
				addedSlimes++;
			}
		}
		
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				if(spaces[x][y] == null) {
					spaces[x][y] = new GameSpace();
				}
			}
		}
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				int slimeCountNearby = 0;
				for(int tx = (int) Math.min(Math.max(x-1, 0), width-1); tx <= (int) Math.min(Math.max(x+1, 0), width-1); tx++) {
					for(int ty = (int) Math.min(Math.max(y-1, 0), height-1); ty <= (int) Math.min(Math.max(y+1, 0), height-1); ty++) {
						if(spaces[tx][ty].isSlimey()) {
							slimeCountNearby++;
						}
					}
				}
				spaces[x][y].setNearbySlimes(slimeCountNearby);
			}
		}
	}
	
	/**
	 * Marks a space
	 * @param x	the int x location
	 * @param y	the int y location
	 */
	public void markSpace(int x, int y) {
		if(x < width && x >= 0 && y < height && y >= 0) {
			if(!spaces[x][y].isRevealed()) {
				if(spaces[x][y].isMarked()) {
					spaces[x][y].setMakred(false);
					slimeMarkedCount--;
				} else {
					spaces[x][y].setMakred(true);
					slimeMarkedCount++;
				}
			}
		} else {
			System.out.println("Out of bounds");
		}
	}
	
	/**
	 * Reveals a space
	 * @param x	the int x location
	 * @param y	the int y location
	 */
	public void openSpace(int x, int y) {
		if(x < width && x >= 0 && y < height && y >= 0) {
			spaces[x][y].setRevealed(true);
			if(spaces[x][y].getNearbySlimes() == 0) {
				// Open nearby spaces if the selected space is not near any slimes
				didOpenSpace = true;
				for(int tx = (int) Math.min(Math.max(x-1, 0), width-1); tx <= (int) Math.min(Math.max(x+1, 0), width-1); tx++) {
					for(int ty = (int) Math.min(Math.max(y-1, 0), height-1); ty <= (int) Math.min(Math.max(y+1, 0), height-1); ty++) {
						if(!spaces[tx][ty].isRevealed()) {
							openSpace(tx, ty);
						}
					}
				}
			} else {
				spaces[x][y].setRevealed(true);
			}
			
			if(spaces[x][y].isSlimey()) {
				steppedSlimes++;
				System.out.println("You stepped in a slime");
			}
		} else {
			System.out.println("Out of bounds");
		}
	}
	
	public boolean didOpenSpace() {
		return didOpenSpace;
	}
	
	public int getSteppedSlimes() {
		return steppedSlimes;
	}
	/**
	 * Determines if the game has been won
	 * @return	boolean did you win
	 */
	public boolean isDone() {
		if(!isDone) {
			for(int x = 0; x < width; x++) {
				for(int y = 0; y < height; y++) {
					if(!(spaces[x][y].isRevealed() || spaces[x][y].isSlimey())) {
						return false;
					}
				}
			}
			isDone = true;
		}
		return true;
	}
	
	/**
	 * Prints the gameboard. Shows only visible things unless you are dead, then shows everything.
	 */
	public void printBoard() {
		System.out.println("Found " + slimeMarkedCount + " of " + slimeCount);
		String nums = "   ";
		for(int i = 0; i < width; i++) {
			String nm = Integer.toString(i);
			if(nm.length() < 2) {
				nm += " ";
			}
			nums += nm;
		}
		System.out.println(nums);
		for(int y = 0; y < height; y++) {
			String row = "";
			String nm = Integer.toString(y);
			if(nm.length() < 2) {
				nm += " ";
			}
			row += nm;
			for(int x = 0; x < width; x++) {
				row += " " + spaces[x][y].getChar(isDone());
			}
			System.out.println(row);
		}
	}
	
	/**
	 * Gets the gameboard in a nicer format for the computer to use.
	 * @return a 2d array of ints representing the gameboard
	 */
	public int[][] getGameBoard() {
		int[][] board = new int[width][height];
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				board[x][y] = spaces[x][y].getInt(isDone());
			}
		}
		return board;
	}

	/**
	 * Runs a {@link RobotAction}
	 * @param a	the {@link RobotAction} to run
	 */
	public boolean runRobotActoin(RobotAction a) {
		if(a != null) {
			switch(a.getType()) {
			case MARK: markSpace(a.getX(), a.getY()); break;
			case REVEAL: openSpace(a.getX(), a.getY()); break;
			}
			return true;
		}
		return false;
	}
}
