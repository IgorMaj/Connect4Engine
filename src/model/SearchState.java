package model;

import java.util.LinkedList;

import exception.IllegalMove;

public class SearchState extends GameGrid {
	
	private LinkedList<SearchState> successors;
	private int column;
	private int score;
	
	private Turn turn;
	
	private static int[] moveOrder;
	
	static {
		moveOrder = new int[Constants.NUM_COLS];
		for(int i=0;i<moveOrder.length;i++) {
			moveOrder[i] = i+1;
		}
		
		int temp = moveOrder[0];
		int index = Constants.NUM_COLS/2;
		moveOrder[0] = moveOrder[index];
		moveOrder[index] = temp;
		
	}
	
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
		setGrid(state);
		turn = t;
	}
	
	public SearchState(GameGrid g) {
		successors = new LinkedList<SearchState>();
		setGrid(g);
		turn = Turn.PLAYER_1;
	}
	
	
	public int getScore() {
		return score;
	}
	

	public void setScore(int score) {
		this.score = score;
	}

	private void setGrid(GameGrid g) {
		bitboards[0] = g.bitboards[0];
		bitboards[1] =g.bitboards[1];
		height  = g.height.clone();
	}

	

	public LinkedList<SearchState> getSuccessors() {
		if(successors.size()==0) {
			generateSuccessors();
		}
		return successors;
	}
	
	

	private void generateSuccessors() {
		for(int column:moveOrder) {
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
		return g.bitboards[0] == bitboards[0] && g.bitboards[1]==bitboards[1];
	}

	public boolean hasExploredSuccessors() {
		return successors.size()==0;
	}
}
