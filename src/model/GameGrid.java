package model;
import exception.IllegalMove;

public class GameGrid {
	
	
	protected long[] bitboards = new long[2];
	
	//maska, ima logicke nule na mestima iznad vrhova kolona
	private static long columnFullMask;
	
	private static long boardFilledMask;
	
	static {
		StringBuilder longStr = new StringBuilder("");
		
		for(int i=Constants.NUM_ROWS*Constants.NUM_COLS;i<64;i++) {
			longStr.append("0");
		}
		
		for(int i=0;i<Constants.NUM_COLS;i++) {
			for(int j=0;j<Constants.NUM_ROWS;j++) {
				longStr.append("1");
			}
			
			if(i!=Constants.NUM_COLS-1) {
				longStr.append("0");
			}
			
		}
		
		boardFilledMask = Long.parseLong(longStr.toString(), 2);
		columnFullMask = 0b1000000100000010000001000000100000010000001000000L;
	}
	
	protected byte[] height = {0,  7, 14, 21, 28, 35, 42};
	
	public static void main(String [] args) throws IllegalMove {
		GameGrid g = new GameGrid();
		System.out.println(g+"\n\n");
		g.performMove(4, Turn.PLAYER_1);
		System.out.println(g+"\n\n");
		g.performMove(4,Turn.PLAYER_2);
		System.out.println(g+"\n\n");
		
		g.performMove(1,Turn.PLAYER_1);
		System.out.println(g+"\n\n");
		g.performMove(7,Turn.PLAYER_2);
		System.out.println(g+"\n\n");
		
		g.performMove(7,Turn.PLAYER_1);
		System.out.println(g+"\n\n");
	}
	
	
	public GameGrid() {
		bitboards[0] = 0;
		bitboards[1] = 0;
	}
	
	
	
	public static GameField turnToGameField(Turn player) {
		if(player == Turn.PLAYER_1) {
			return GameField.PLAYER_1;
		}
		return GameField.PLAYER_2;
	}
	
	
	public boolean gridFilled() {
		return (bitboards[0] ^ bitboards[1])==boardFilledMask;
	}
	
	public boolean checkVictory(Turn player) {
		if(player==Turn.PLAYER_1) {
			return checkVictory(bitboards[0]);
		}
		else {
			return checkVictory(bitboards[1]);
		}
		
	}
	
	private boolean checkVictory(long bitboard) {
		//horizontal check
		if((bitboard & bitboard >> Constants.NUM_COLS & bitboard >> 2*Constants.NUM_COLS & bitboard >> 3*Constants.NUM_COLS)!=0) {
			return true;
		}
		//vertical check
		else if ((bitboard & (bitboard >> 1) & (bitboard >>  2) & (bitboard >>  3)) != 0) {
			return true;
		}
		// diagonal check (/)
		else if ((bitboard & (bitboard >> 1*8) & (bitboard >> 2*8) & (bitboard >> 3*8)) != 0) {
			return true;
		}
		//diagonal check(\)
		else if((bitboard & (bitboard >> 1*6) & (bitboard >> 2*6) & (bitboard >> 3*6)) != 0) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		for(int i=0;i<Constants.NUM_ROWS;i++) {
			builder.append("| ");
			for(int j=0;j<Constants.NUM_COLS;j++) {
				builder.append(getGrid(i,j)+" | ");
			}
			builder.append("\n");
		}
		return builder.toString();
	}

	public void performMove(int column,Turn player) throws IllegalMove {
		
		if(column <=0 || column > Constants.NUM_COLS) {
			throw new IllegalMove();
		}
		int realColumn = column-1;
		if(player==Turn.PLAYER_1) {
			performMove(realColumn,0);
		}
		else {
			performMove(realColumn,1);
		}
	}

	private void performMove(int realColumn, int index) throws IllegalMove {
		long move = 1L << height[realColumn];
		if((move & columnFullMask)!=0) {
			throw new IllegalMove();
		}
		height[realColumn]++;
		bitboards[index] = bitboards[index] ^ move;
	}



	public void resetGrid() {
		bitboards[0] = 0;
		bitboards[1]  = 0;
		int sum = 0;
	    for(int i=0;i<Constants.NUM_COLS;i++) {
	    	height[i] = (byte)sum;
	    	sum += Constants.NUM_COLS;
	    }
	}

	public GameField getGrid(int i, int j) {
		int pos = Constants.NUM_ROWS-1-i + (j)*Constants.NUM_COLS;
		if(((bitboards[0] >> pos) & 1)==1) {
			return GameField.PLAYER_1;
		}
		else if(((bitboards[1] >> pos) & 1)==1) {
			return GameField.PLAYER_2;
		}
		return GameField.FREE;
	}
	
	
}
