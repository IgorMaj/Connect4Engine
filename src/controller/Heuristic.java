package controller;

import model.SearchState;

public interface Heuristic {
	
	public abstract int computeScore(SearchState state);
}
