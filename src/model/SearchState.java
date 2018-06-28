package model;

import java.util.LinkedList;

import exception.IllegalMove;

public class SearchState extends GameGrid {
	
	private LinkedList<SearchState> successors;
	private int column;
	private int score;
	
	private Turn turn;
	
	public Turn getTurn() {
		return turn;
	}

	public void setTurn(Turn turn) {
		this.turn = turn;
	}

	public SearchState() {
		super();
		successors = new LinkedList<SearchState>();
		column = 0;
		score = 0;
		turn = Turn.PLAYER_1;
	}
	
	public SearchState(SearchState state,Turn t) {
		successors = new LinkedList<SearchState>();
		column = 0;
		score = 0;
		setGrid(state.grid);
		turn = t;
	}
	
	public SearchState(GameGrid g) {
		successors = new LinkedList<SearchState>();
		setGrid(g.grid);
		turn = Turn.PLAYER_1;
	}
	
	
	public int getScore() {
		return score;
	}
	

	public void setScore(int score) {
		this.score = score;
	}

	private void setGrid(GameField[][] g) {
		grid = new GameField[Constants.NUM_ROWS][Constants.NUM_COLS];
		for(int i=0;i< Constants.NUM_ROWS;i++) {
			for(int j=0;j<Constants.NUM_COLS;j++) {
				grid[i][j] = g[i][j];
			}
		}
	}

	

	public LinkedList<SearchState> getSuccessors() {
		if(successors.size()==0) {
			generateSuccessors();
		}
		return successors;
	}
	
	

	private void generateSuccessors() {
		for(int column=1;column<=Constants.NUM_COLS;column++) {
			SearchState potentialSuccessor = new SearchState(this);
			try {
				potentialSuccessor.performMove(column, turn);
				potentialSuccessor.column = column;
				potentialSuccessor.setTurn(Turn.oppositeTurn(turn));
				successors.add(potentialSuccessor);
			} catch (IllegalMove e) {
			}
		}
	}



	@Override
	public String toString() {
		StringBuilder retVal = new StringBuilder();
		for(int i=0;i< Constants.NUM_ROWS;i++) {
			retVal.append("|");
			for(int j=0;j<Constants.NUM_COLS;j++) {
				retVal.append(grid[i][j]+"|");
			}
			retVal.append("\n");
		}
		retVal.append("\nTurn: "+turn+"\nColumn: "+column+"\nScore: "+score+"\n");
		return retVal.toString();
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

	public boolean isEqualToGrid(GameGrid g) {
		for(int i=0;i< Constants.NUM_ROWS;i++) {
			for(int j=0;j<Constants.NUM_COLS;j++) {
				if(g.grid[i][j]!=grid[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
}
