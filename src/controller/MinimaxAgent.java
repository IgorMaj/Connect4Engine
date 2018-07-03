package controller;
import java.util.LinkedList;

import model.Constants;
import model.GameGrid;
import model.SearchState;
import model.Turn;

public class MinimaxAgent extends IntelligentAgent {
		
	
	public MinimaxAgent(int d,Heuristic h) {
		super(d,h);
	}
	
	public MinimaxAgent() {
		super();
	}
	

	public MinimaxAgent(int d) {
		super(d);
	}


	@Override
	public String toString() {
		return "MinimaxAgent";
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
	
	private int minimax(SearchState state,int realDepth) {
		
		int depthPenalty = (depth-realDepth);
		if(state.checkVictory(Turn.PLAYER_1)) {
			state.setScore(Constants.VICTORY_SCORE-depthPenalty);
		}
		else if(state.checkVictory(Turn.PLAYER_2)) {
			state.setScore(Constants.DEFEAT_SCORE+depthPenalty);
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

}
