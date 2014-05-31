package com.digitreko.games.model;

public class RaceSettings {

	static final int HARD = 0;
	static final int SOFT = 0;	
	
	private int aggressivness;
	private int startingTires;
	
	public RaceSettings(int aggro, int tiresType){
		aggressivness = aggro;
		startingTires = tiresType;
	}

	public int getAggressivness() {
		return aggressivness;
	}

	public void setAggressivness(int aggressivness) {
		this.aggressivness = aggressivness;
	}

	public int getStartingTires() {
		return startingTires;
	}

	public void setStartingTires(int startingTires) {
		this.startingTires = startingTires;
	}
	
}
