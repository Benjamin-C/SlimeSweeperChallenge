package slimesweeper.challenge.robot;

import dev.benjaminc.challenge.Robot;
import dev.benjaminc.challenge.RobotAction;

/**
 * Blank Robot class to play SlimeSweeper.
 * To make your own class, naming is not important, but it must implement the Robot class.
 * You are allowed to do anything that you wish in this class,
 * except for modifying the game or anything else improper.
 * To run your robot, compile it into a jar file, then run the SlimeSweeper-version.jar
 * passing the location of the robot.jar as the first paramater.
 * Multiple robots can be compiled into a single jar, and the user can select the one to use at runitme.
 * 
 * @author Benjamin-C
 */
public class BlankRobot implements Robot {

	/*
	 * Put any code here that you want run when the Robot starts.
	 * This would initialize any instance variables or say hi to the user.
	 * This is the equivalent of the Robot's constructor.
	 */
	@Override
	public void init() {
		
	}

	/*
	 * Put code here to run when the robot should make a move.
	 * You are given the game board as a 2d integer array.
	 * Each cell represents what the game board has at that location.
	 *  >  9: The space is marked
	 *  > -1: The space is hidden
	 *  > Otherwise it is the number of nearby slimes
	 * Return a RobotAction to tell the game what to do.
	 * Use the constructor RobotAction(int x, int y, RobotActionTypes type)
	 *  > x is the X location, which is the first dimension of the board array.
	 *  > y is the Y location, which is the second dimension of the board array.
	 *  > type is the type of action can be REVEAL or MARK
	 */
	@Override
	public RobotAction makeMove(int[][] board) {
		System.out.println("HELP I don't know what to do!");
		
		return null;
	}
	
	/*
	 * Put code here to run once the game is over.
	 * This would clean up any variables, or say bye to the user.
	 * This would also be where you would close any resources that you opened earlier.
	 * You are also given the final board with all spaces revealed.
	 * Each cell represents what the game board has at that location:
	 *  >  9: The space is marked
	 *  > -1: The space is hidden
	 *  > -2: The space is slimy
	 *  > -3: The space is slimy and marked
	 *  > Otherwise it is the number of nearby slimes
	 */
	@Override
	public void cleanup(boolean won, int[][] board) {
		
	}

}
