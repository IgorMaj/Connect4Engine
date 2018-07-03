package controller;

import model.GameGrid;
import model.Turn;

public abstract class Agent {
	
	
	public abstract int calculateNextMove(GameGrid g,Turn t);
	
	public abstract void resetAgent();
	
	public abstract void setHeuristic(Heuristic h);
	public abstract void setDepth(int d);
}
