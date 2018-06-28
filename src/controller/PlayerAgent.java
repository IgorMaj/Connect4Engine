package controller;

import model.GameGrid;
import model.Turn;

public class PlayerAgent extends Agent {

	@Override
	public int calculateNextMove(GameGrid g, Turn t) {
		return 0;
	}

	@Override
	public String toString() {
		return "PlayerAgent";
	}

	@Override
	public void resetAgent() {
	}

}
