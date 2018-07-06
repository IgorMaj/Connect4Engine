package controller;

import model.Constants;
import model.GameField;
import model.SearchState;

public class MyHeuristic implements Heuristic {
	
	private static int[][] evaluationTable = {{3, 4, 5, 7, 5, 4, 3}, 
            {4, 6, 8, 10, 8, 6, 4},
            {5, 8, 11, 13, 11, 8, 5}, 
            {5, 8, 11, 13, 11, 8, 5},
            {4, 6, 8, 10, 8, 6, 4},
            {3, 4, 5, 7, 5, 4, 3}};

	@Override
	public String toString() {
		return "MyHeuristic";
	}
	

	@Override
	public int computeScore(SearchState state) {
		int utility = 138;
		int sum = 0;
		
		for (int i = 0; i < Constants.NUM_ROWS; i++) {
			for (int j = 0; j <Constants.NUM_COLS; j++) {
				if (state.getGrid(i, j)==GameField.PLAYER_1) {
					sum += evaluationTable[i][j];
				}
				else if (state.getGrid(i, j)==GameField.PLAYER_2) {
					sum -= evaluationTable[i][j];
				}
			}
		}
		
		
		return utility + sum;
	}

}
