package model;

public enum GameField {
	
	FREE,PLAYER_1,PLAYER_2;
	
	@Override
	public String toString() {
	    switch(this) {
	      case FREE : return "0";
	      case PLAYER_1: return "1";
	      case PLAYER_2: return "2";
	      default: throw new IllegalArgumentException();
	    }
	  }
}
