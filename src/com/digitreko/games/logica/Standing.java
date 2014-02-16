package com.digitreko.games.logica;

import java.util.Comparator;
import java.util.Map;

public class Standing implements Comparator<Standing>, Comparable<Standing> {
	private int outcome;
	private Team team;
	private Pilot pilot;
	
	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Pilot getPilot() {
		return pilot;
	}

	public void setPilot(Pilot pilot) {
		this.pilot = pilot;
	}

	public Standing(int outcome, Pilot pilot, Team team){
		this.outcome = outcome;
		this.team = team;
		this.pilot = pilot;
	}
	
	public Standing(){
		
	}

	public int getOutcome() {
		return outcome;
	}

	public void setOutcome(int outcome) {
		this.outcome = outcome;
	}

	@Override
	public int compareTo(Standing arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int compare(Standing arg0, Standing arg1) {
		return arg0.getOutcome() - arg1.getOutcome();
	}
	
}