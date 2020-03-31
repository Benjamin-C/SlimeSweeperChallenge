package dev.benjaminc.challenge;

/**
 * Robot class to play the SlimeSweeper game. You may use Main.scan to scan System.in
 * @author Benjmain-C
 *
 */
public interface Robot {
	
	/**
	 * Called before the robot is asked to make any moves
	 */
	public void init();
	/**
	 * Called each time the robot is asked to make a move<br/>
	 * Gets a representation of the space as an int.
	 * <li>If revealed, returns the number of nearby slimes</li>
	 * <li>If marked, returns 9</li>
	 * <li>If hidden, returns -1</li>
	 * @param board	the 2d int array representation of the gameboard
	 * @return		a {@link RobotAction} the robot would like to take
	 */
	public RobotAction makeMove(int[][] board);
	/**
	 * Called when the game is over
	 * The final gameboard is also passed in
	 * <li>If revealed, returns the number of nearby slimes</li>
	 * <li>If marked, returns 9</li>
	 * <li>If hidden, returns -1</li>
	 * <li>If slimy, returns -2</li>
	 * @param won	boolean if you won the game or not
	 * @param board	the 2d int array representation of the gameboard
	 */
	public void cleanup(boolean won, int[][] board);

}
