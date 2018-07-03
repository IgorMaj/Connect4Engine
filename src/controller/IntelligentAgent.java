package controller;

import java.util.LinkedList;

import model.GameGrid;
import model.SearchState;
import model.Turn;

public abstract class IntelligentAgent extends Agent {
	
	protected int depth;
	protected SearchState currentState;
	protected Heuristic heuristic;
	
	public IntelligentAgent() {
		
	}
	
	public Heuristic getHeuristic() {
		return heuristic;
	}

	@Override
	public void setHeuristic(Heuristic heuristic) {
		this.heuristic = heuristic;
	}


	public IntelligentAgent(int d,Heuristic h) {
		depth = d;
		currentState = null;
		heuristic = h;
	}
	

	public IntelligentAgent(int d) {
		depth = d;
		currentState = null;
		heuristic =  new NullHeuristic();
	}


	public int getDepth() {
		return depth;
	}


	@Override
	public void setDepth(int depth) {
		this.depth = depth;
	}
	
	
	public SearchState updateState(GameGrid g) {
		for(SearchState state:currentState.getSuccessors()) {
			if(state.isEqualToGrid(g)) {
				return state;
			}
		}
		return null;
	}


	public SearchState pickBestMove(SearchState state) {
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



	@Override
	public void resetAgent() {
		currentState = null;
	}
}
