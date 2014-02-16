package com.digitreko.games.logica;

public class Sponsor {
	private String name;
	private int yearValue;
	private int level;		//level determines the how big you must be to draw attention from this sponsor
							//level 1 means starter sponsor, level 10 mean big sponsor, lots of cash but team
							//must be big to get an offer


	public Sponsor(String name, int value, int level){
		this.name = name;
		this.yearValue = value;
		this.level = level;		
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getYearValue() {
		return yearValue;
	}


	public void setYearValue(int yearValue) {
		this.yearValue = yearValue;
	}


	public int getLevel() {
		return level;
	}


	public void setLevel(int level) {
		this.level = level;
	}
	
}

