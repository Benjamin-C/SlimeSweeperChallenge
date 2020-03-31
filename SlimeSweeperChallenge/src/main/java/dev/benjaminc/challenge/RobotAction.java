package dev.benjaminc.challenge;

public class RobotAction {
	
	public enum RobotActionTypes {
		/** Reveals a space */ REVEAL,
		/** Marks a space as slime */ MARK;
	}
	
	private int x;
	private int y;
	private RobotActionTypes type;
	private char userchar;

	/**
	 * Makes a new {@link RobotAction}
	 * @param x		the int x location
	 * @param y		the int y location
	 * @param type	the {@link RobotActionTypes} type of action
	 */
	public RobotAction(int x, int y, RobotActionTypes type) {
		this(x, y, type, (char) 0x00);
	}
	
	/**
	 * Makes a new {@link RobotAction}
	 * @param x		the int x location
	 * @param y		the int y location
	 * @param type	the {@link RobotActionTypes} type of action
	 */
	public RobotAction(int x, int y, RobotActionTypes type, char userchar) {
		super();
		this.x = x;
		this.y = y;
		this.type = type;
		this.userchar = userchar;
	}
	
	
	/**
	 * Gets the x location of the {@link RobotAction}
	 * @return	the int x location
	 */
	public int getX() {
		return x;
	}
	/**
	 * Sets the x location of the {@link RobotAction}
	 * @param x	the int new x locatoin
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * Gets the y location of the {@link RobotAction}
	 * @return	the int y location
	 */
	public int getY() {
		return y;
	}
	/**
	 * Sets the y location of the {@link RobotAction}
	 * @param y	the int new y locatoin
	 */
	public void setY(int y) {
		this.y = y;
	}
	/**
	 * Gets the type of the {@link RobotAction}
	 * @return	the {@link RobotActionTypes} type
	 */
	public RobotActionTypes getType() {
		return type;
	}
	/**
	 * Sets the type of the {@link RobotAction}
	 * @param type	the {@link RobotActionTypes} new type
	 */
	public void setType(RobotActionTypes type) {
		this.type = type;
	}
	
	public char getUserchar() {
		return userchar;
	}

	public void setUserchar(char userchar) {
		this.userchar = userchar;
	}

	@Override
	public String toString() {
		if(userchar != 0) {
			return type + "[" + userchar + "](" + x + "," + y + ")";
		} else {
			return type + "(" + x + "," + y + ")";
		}
	}
}
