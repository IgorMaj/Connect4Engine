package model;

import java.util.LinkedList;

import exception.IllegalMove;

public class SearchState extends GameGrid {
	
	private LinkedList<SearchState> successors;
	private int column;
	
	public SearchState() {
		super();
		successors = new LinkedList<SearchState>();
		column = 0;
	}
	
	public SearchState(SearchState state) {
		successors = new LinkedList<SearchState>();
		setGrid(state.grid);
	}
	
	private void setGrid(GameField[][] grid) {
		grid = new GameField[Constants.NUM_ROWS][Constants.NUM_COLS];
		for(int i=0;i< Constants.NUM_ROWS;i++) {
			for(int j=0;j<Constants.NUM_COLS;j++) {
				grid[i][j] = grid[i][j];
			}
		}
	}

	public SearchState(GameGrid g) {
		successors = new LinkedList<SearchState>();
		setGrid(g.grid);
	}

	public LinkedList<SearchState> getSuccessors(Turn player) {
		if(successors.size()==0) {
			return generateSuccessors(player);
		}
		return successors;
	}
	
	

	private LinkedList<SearchState> generateSuccessors(Turn player) {
		for(int column=1;column<=Constants.NUM_COLS;column++) {
			SearchState potentialSuccessor = new SearchState(this);
			try {
				potentialSuccessor.performMove(column, player);
				potentialSuccessor.column = column;
				successors.add(potentialSuccessor);
			} catch (IllegalMove e) {
				continue;
			}
		}
		return successors;
	}



	public void setSuccessors(LinkedList<SearchState> successors) {
		this.successors = successors;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}
}
