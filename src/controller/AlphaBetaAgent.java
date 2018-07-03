package controller;

import java.util.LinkedList;

import model.Constants;
import model.GameGrid;
import model.SearchState;
import model.Turn;

public class AlphaBetaAgent extends IntelligentAgent {

	
	public AlphaBetaAgent(int d,Heuristic h) {
		super(d,h);
	}
	
	public AlphaBetaAgent() {
		super();
	}
	

	public AlphaBetaAgent(int d) {
		super(d);
	}


	@Override
	public String toString() {
		return "AlphaBetaAgent";
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
		
		alphaBeta(currentState,Integer.MIN_VALUE,Integer.MAX_VALUE,depth);
		currentState = pickBestMove(currentState);
		return currentState.getColumn();
	}
	
	
	private int alphaBeta(SearchState state,int alpha,int beta,int realDepth) {
		
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
				state.setScore(maxValue(state,alpha,beta,realDepth)+heuristic.computeScore(state));
				
			}
			else {
				state.setScore(minValue(state,alpha,beta,realDepth)-heuristic.computeScore(state));
			}
		}
		
		return state.getScore();
		
	}
	
	private int minValue(SearchState state,int alpha,int beta,int realDepth) {
		LinkedList<SearchState> successors = state.getSuccessors();
		int retVal = Integer.MAX_VALUE;
		for(SearchState s:successors) {
			retVal = Math.min(retVal,alphaBeta(s,alpha,beta,realDepth-1));
			
			beta = Math.min(beta, retVal);
			if(alpha>=beta) {
				break;
			}
		}

		return retVal;
	}


	private int maxValue(SearchState state,int alpha,int beta ,int realDepth) {
		LinkedList<SearchState> successors = state.getSuccessors();
		int retVal = Integer.MIN_VALUE;
		for(SearchState s:successors) {
			retVal = Math.max(retVal,alphaBeta(s,alpha,beta,realDepth-1));
			
			alpha = Math.max(retVal,alpha);
			if(alpha>=beta) {
				break;
			}
		}
		return retVal;
	}



}
