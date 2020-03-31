package dev.benjaminc.challenge;

import java.util.List;

import dev.benjaminc.slimesweeper.SlimeSweeper;

public class SlimeStatistic {

	private SlimeSweeper game;
	private List<RobotAction> actions;
	private Exception e;
	
	/**
	 * Representation of completed game.
	 * @param endState	the {@link GameEndState} the game ended in
	 * @param board		the 2d char array of the revealed board
	 * @param actions	the {@link List} of {@link RobotAction} the robot made	
	 * @param e			the {@link Exception} the {@link Robot} threw. Null if no exception was thrown.
	 */
	public SlimeStatistic(SlimeSweeper game, List<RobotAction> actions, Exception e) {
		super();
		this.game = game;
		this.actions = actions;
		this.e = e;
	}
	
	public SlimeSweeper getGame() {
		return game;
	}
	public List<RobotAction> getActions() {
		return actions;
	}
	public Exception getException() {
		return e;
	}
	
	public void print() {
		System.out.print("SlimeStatistic\nSteppedSlimes: " + game.getSteppedSlimes() + "\nActions:");
		for(int i = 0; i < actions.size(); i++) {
			if(i != 0) {
				System.out.print(" ");
			}
			System.out.print(actions.get(i).toString());
		}
		System.out.print("\nBoard:\n");
		game.printBoard();
	}
}
