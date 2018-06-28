package controller;


import java.util.LinkedList;

import model.Constants;
import model.GameGrid;
import model.SearchState;
import model.Turn;

public class MinimaxAgent extends Agent {
	
	private int depth;
	private SearchState currentState;
	private Heuristic heuristic;
	
	public MinimaxAgent(int d,Heuristic h) {
		depth = d;
		currentState = null;
		heuristic = h;
	}
	

	public MinimaxAgent(int d) {
		depth = d;
		currentState = null;
		heuristic =  new NullHeuristic();
	}


	@Override
	public String toString() {
		return "MinimaxAgent";
	}


	public int getDepth() {
		return depth;
	}



	public void setDepth(int depth) {
		this.depth = depth;
	}
	
	



	@Override
	public int calculateNextMove(GameGrid g, Turn t) {
		if(currentState==null) {
			currentState = new SearchState(g);
			currentState.setTurn(t);
		}
		else {
			currentState = updateState(g);
		}
		
		minimax(currentState,depth);
		currentState = pickBestMove(currentState);
		return currentState.getColumn();
	}

	private SearchState updateState(GameGrid g) {
		for(SearchState state:currentState.getSuccessors()) {
			if(state.isEqualToGrid(g)) {
				return state;
			}
		}
		return null;
	}


	private SearchState pickBestMove(SearchState state) {
		if(state.getTurn()==Turn.PLAYER_1) {
			return pickMaxScoreState(state);
		}
		else {
			return pickMinScoreState(state);
		}
	}


	private SearchState pickMinScoreState(SearchState state) {
		LinkedList<SearchState> successors = state.getSuccessors();
		SearchState minScoreState = successors.getFirst();
		for(SearchState successor:successors) {
			if(successor.getScore() < minScoreState.getScore()) {
				minScoreState = successor;
			}
		}
		
		return minScoreState;
	}


	private SearchState pickMaxScoreState(SearchState state) {
		LinkedList<SearchState> successors = state.getSuccessors();
		SearchState maxScoreState = successors.getFirst();
		
		for(SearchState successor:successors) {
			if(successor.getScore() > maxScoreState.getScore()) {
				maxScoreState = successor;
			}
		}
		
		return maxScoreState;
	}


	private int minimax(SearchState state,int realDepth) {
		
		if(state.checkVictory(Turn.PLAYER_1)) {
			state.setScore(Constants.VICTORY_SCORE);
		}
		else if(state.checkVictory(Turn.PLAYER_2)) {
			state.setScore(Constants.DEFEAT_SCORE);
		}
		else if(state.gridFilled()) {
			state.setScore(Constants.DRAW_SCORE);
		}
		else if(realDepth==0) {
			state.setScore(heuristic.computeScore(state));
		}
		else {
			if(state.getTurn()==Turn.PLAYER_1) {
				state.setScore(maxValue(state,realDepth)+heuristic.computeScore(state));
				
			}
			else {
				state.setScore(minValue(state,realDepth)-heuristic.computeScore(state));
			}
		}
		
		return state.getScore();
		
	}
	
	private int minValue(SearchState state,int realDepth) {
		LinkedList<SearchState> successors = state.getSuccessors();
		int retVal = Integer.MAX_VALUE;
		for(SearchState s:successors) {
			retVal = Math.min(retVal,minimax(s,realDepth-1));
		}
		return retVal;
	}


	private int maxValue(SearchState state, int realDepth) {
		LinkedList<SearchState> successors = state.getSuccessors();
		int retVal = Integer.MIN_VALUE;
		for(SearchState s:successors) {
			retVal = Math.max(retVal,minimax(s,realDepth-1));
		}
		
		return retVal;
	}


	@Override
	public void resetAgent() {
		currentState = null;
		
	}
	

}
