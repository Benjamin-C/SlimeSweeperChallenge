package dev.benjaminc.slimesweeper;

public class GameSpace {
	
	private boolean isSlimey;
	private int nearbySlimes;
	private boolean isRevealed;
	private boolean isMarked;
	
	public GameSpace() {
		this(false, 0, false, false);
	}
	
	public GameSpace(boolean isSlimey) {
		this(isSlimey, 0, false, false);
	}

	public GameSpace(boolean isSlimey, int nearbySlimes, boolean isRevealed, boolean marked) {
		super();
		this.isSlimey = isSlimey;
		this.nearbySlimes = nearbySlimes;
		this.isRevealed = isRevealed;
		this.isMarked = marked;
	}
	
	public boolean isSlimey() {
		return isSlimey;
	}
	public void setSlimey(boolean isSlimey) {
		this.isSlimey = isSlimey;
	}
	public int getNearbySlimes() {
		return nearbySlimes;
	}
	public void setNearbySlimes(int nearbySlimes) {
		this.nearbySlimes = nearbySlimes;
	}
	public boolean isRevealed() {
		return isRevealed;
	}
	public void setRevealed(boolean isRevealed) {
		this.isRevealed = isRevealed;
	}
	public boolean isMarked() {
		return isMarked;
	}
	public void setMakred(boolean marked) {
		this.isMarked = marked;
	}
	public String getChar() {
		return getChar(false);
	}
	public String getChar(boolean showAll) {
		if(isRevealed || showAll) {
			if(isSlimey) {
				if(isMarked) {
					return "$";
				} else {
					return "X";
				}
			}
			if(nearbySlimes == 0) {
				return " ";
			}
			return Integer.toString(nearbySlimes).substring(0,1);
		} else {
			if(isMarked) {
				return "#";
			} else {
				return "-";
			}
		}
	}
	
	/**
	 * Gets a representation of the space as an int.<br/>
	 * <li>If revealed, returns the number of nearby slimes</li>
	 * <li>If marked, returns 9</li>
	 * <li>If hidden, returns -1</li>
	 * @return the number representation of the space
	 */
	public int getInt() {
		return getInt(false);
	}
	/**
	 * Gets a representation of the space as an int.<br/>
	 * <li>If revealed, returns the number of nearby slimes</li>
	 * <li>If marked, returns 9</li>
	 * <li>If hidden, returns -1</li>
	 * Additional outputs if showAll is true
	 * <li>If slimy, returns -2</li>
	 * <li>If slimy and marked, returns -3</li>
	 * @param showAll	boolean to show all spaces even if hidden
	 * @return the number representation of the space
	 */
	public int getInt(boolean showAll) {
		if(isRevealed || showAll) {
			if(isSlimey) {
				if(isMarked) {
					return -3;
				} else {
					return -2;
				}
			}
			return nearbySlimes;
		} else {
			if(isMarked) {
				return 9;
			} else {
				return -1;
			}
		}
	}

}
