package model;

public enum Turn {
	PLAYER_1,PLAYER_2;
	
	public static Turn oppositeTurn(Turn t) {
		if(t==PLAYER_1) {
			return PLAYER_2;
		}
		else {
			return PLAYER_1;
		}
	}
	
}
