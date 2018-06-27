package model;

import exception.IllegalMove;

public class GameGrid {
	
	
	protected GameField[][] grid;
	
	public GameGrid() {
		grid = new GameField[Constants.NUM_ROWS][Constants.NUM_COLS];
		for(int i=0;i< Constants.NUM_ROWS;i++) {
			for(int j=0;j<Constants.NUM_COLS;j++) {
				grid[i][j] = GameField.FREE;
			}
		}
	}
	
	public static GameField turnToGameField(Turn player) {
		if(player == Turn.PLAYER_1) {
			return GameField.PLAYER_1;
		}
		return GameField.PLAYER_2;
	}
	
	
	public boolean gridFilled() {
		for(int i=0;i< Constants.NUM_ROWS;i++) {
			for(int j=0;j<Constants.NUM_COLS;j++) {
				if(grid[i][j]==GameField.FREE) {
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean checkVictory(Turn player) {
		for(int i=0;i<Constants.NUM_ROWS;i++){
			for(int j=0;j<Constants.NUM_COLS;j++){
				if(turnToGameField(player) == grid[i][j]){
					if(isLinearMatch(i,j,1,0) || isLinearMatch(i,j,0,1)|| isLinearMatch(i,j,1,1) || isLinearMatch(i,j,1,-1)){
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	private boolean isLinearMatch(int x,int y,int step_x,int step_y){
		try{
			for(int i=1;i< Constants.REQUIRED_IN_ROW;i++){
				if(grid[x +i*step_x][y+ i*step_y]!=grid[x][y]){
					return false;
				}
			}
		
			return true;
		}
		catch (IndexOutOfBoundsException e){
			return false;
		}
	}
	
	public GameField[][] getGrid(){
		return grid;
	}
	

	public void performMove(int column,Turn player) throws IllegalMove {
		if(column < 1 || column > Constants.NUM_COLS) {
			throw new IllegalMove();
		}
		
		int row = Constants.NUM_ROWS-1;
		while(row >=0) {
			
			if(grid[row][column-1]==GameField.FREE) {
				
				if(player==Turn.PLAYER_1) {
					grid[row][column-1] = GameField.PLAYER_1;
				}
				else {
					grid[row][column-1] = GameField.PLAYER_2;
				}
				return;
			}
			row--;
		}
		
		throw new IllegalMove();
	}
	
	
}
