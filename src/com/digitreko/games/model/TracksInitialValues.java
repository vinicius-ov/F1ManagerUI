package com.digitreko.games.model;

public enum TracksInitialValues {
	
	Australia("Australia",2,3,6,5,9,8,5,4,1,2);
	
	private int name;
	private int[] difficulty;

	private TracksInitialValues(String name, int... difficulties){
		
	}
	
	
	public int getName() {
		return name;
	}
	public void setName(int name) {
		this.name = name;
	}
	public int[] getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(int[] difficulty) {
		this.difficulty = difficulty;
	}
	
}
