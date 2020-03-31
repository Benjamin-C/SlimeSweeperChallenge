package dev.benjaminc.challenge;

import dev.benjaminc.challenge.RobotAction.RobotActionTypes;

public class DefaultRobot implements Robot {
	
	@Override
	public void init() {
		System.out.println("Human-player Robot Enabled!");
	}
	
	@Override
	public RobotAction makeMove(int[][] board) {
		while(true) {
			
			System.out.print(">");
			
			String[] dta = Main.scan.nextLine().split(" ");
			switch(dta[0]) {
			case "r": {
				if(dta.length == 3) {
					try {
						return new RobotAction(Integer.parseInt(dta[1]), Integer.parseInt(dta[2]), RobotActionTypes.REVEAL);
					} catch(NumberFormatException e) {
						System.out.println("That's not a number!");
					}
				} else {
					System.out.println("Not enough args" + dta.length);
				}
			} break;
			case "m": {
				if(dta.length == 3) {
					try {
						return new RobotAction(Integer.parseInt(dta[1]), Integer.parseInt(dta[2]), RobotActionTypes.MARK);
					} catch(NumberFormatException e) {
						System.out.println("That's not a number!");
					}
				} else {
					System.out.println("Not enough args");
				}
			} break;
			default: {
				System.out.println("I don't know what that is");
			} break;
			}
		}
	}

	@Override
	public void cleanup(boolean won, int[][] board) {
		
	}
}
