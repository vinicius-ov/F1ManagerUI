package com.digitreko.games.model;

public class Manager {
	private String name;
	private String nationality;
	private Team playerTeam;	//a pointer to players team
	
	public Manager(){
		
	}
	
	public String getName() {
		return name;
	}	
	public void setName(String name) {
		this.name = name;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public Team getPlayerTeam() {
		return playerTeam;
	}

	public void setPlayerTeam(Team playerTeam) {
		this.playerTeam = playerTeam;
	}
	
	
	
}
