package model;

import java.util.LinkedList;

import controller.Heuristic;
import controller.MyHeuristic;
import controller.NullHeuristic;

public class Options {
	
	@Override
	public String toString() {
		return "Options [agent1Depth=" + agent1Depth + ", agent1Heuristic=" + agent1Heuristic + ", agent2Depth="
				+ agent2Depth + ", agent2Heuristic=" + agent2Heuristic + "]";
	}

	private int agent1Depth;
	private Heuristic agent1Heuristic;
	
	private int agent2Depth;
	private Heuristic agent2Heuristic;
		
	public static LinkedList<Heuristic> allHeuristics = new LinkedList<Heuristic>();
	
	static {
		allHeuristics.add(new NullHeuristic());
		allHeuristics.add(new MyHeuristic());
	}
	
	
	
	
	public Options() {
		agent1Depth = 6;
		agent2Depth = 6;
		agent1Heuristic = allHeuristics.getFirst();
		agent2Heuristic = allHeuristics.getFirst();
	}

	public int getAgent1Depth() {
		return agent1Depth;
	}

	public void setAgent1Depth(int agent1Depth) {
		this.agent1Depth = agent1Depth;
	}

	public Heuristic getAgent1Heuristic() {
		return agent1Heuristic;
	}

	public void setAgent1Heuristic(Heuristic agent1Heuristic) {
		this.agent1Heuristic = agent1Heuristic;
	}

	public int getAgent2Depth() {
		return agent2Depth;
	}

	public void setAgent2Depth(int agent2Depth) {
		this.agent2Depth = agent2Depth;
	}

	public Heuristic getAgent2Heuristic() {
		return agent2Heuristic;
	}

	public void setAgent2Heuristic(Heuristic agent2Heuristic) {
		this.agent2Heuristic = agent2Heuristic;
	}
	
	
}
