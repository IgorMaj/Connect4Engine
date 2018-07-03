package controller;

import java.util.LinkedList;
import java.util.Random;

import model.GameGrid;
import model.SearchState;
import model.Turn;

public class DummyAgent extends Agent {

	@Override
	public String toString() {
		return "DummyAgent";
	}

	private Random rand = new Random();
	
	@Override
	public int calculateNextMove(GameGrid g, Turn t) {
		
		SearchState currentState = new SearchState(g);
		LinkedList<SearchState> successors = currentState.getSuccessors();
		SearchState nextState = successors.get(rand.nextInt(successors.size()));
		return nextState.getColumn();
	}

	@Override
	public void resetAgent() {
		
	}

	@Override
	public void setHeuristic(Heuristic h) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDepth(int d) {
		// TODO Auto-generated method stub
		
	}
	
}
