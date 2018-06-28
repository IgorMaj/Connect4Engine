package controller;

import model.SearchState;

public class NullHeuristic implements Heuristic {

	@Override
	public int computeScore(SearchState state) {
		return 0;
	}

}
