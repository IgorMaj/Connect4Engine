package model;


public class Constants {
	public static final int NUM_ROWS = 6;
	public static final int NUM_COLS = 7;
	public static final int REQUIRED_IN_ROW = 4;
	public static final int WAIT_TIME = 70;
	public static int MAIN_WINDOW_HEIGHT = 600;
	public static int MAIN_WINDOW_WIDTH = 800;
	
	public static String victoryMessage(Turn t) {
		return "Player "+t+" is victorious.";
	}

	public static String boardFilledMessage() {
		return "Board filled.";
		
	}
}
