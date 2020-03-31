package dev.benjaminc.challenge;

public class Util {
	 
	/**
	 * Prints a board as the Robot sees it
	 * @param board	the 2d int array of the gameboard
	 */
	public static void printBoard(int[][] board) {
		for(int y = 0; y < board[0].length; y++) {
			String row = "";
			for(int x = 0; x < board.length; x++) {
				switch(board[x][y]) {
				case -3: row += "$ "; break;
				case -2: row += "X "; break;
				case -1: row += "- "; break;
				case 0: row += "  "; break;
				case 9: row += "# "; break;
				default: row += board[x][y]; break;
				}
			}
			System.out.println(row);
		}
	}

}
