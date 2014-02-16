package com.digitreko.games.logica;

import java.util.Calendar;
import java.util.List;

public class SessionManager {
		private List<Pilot> pilotStanding;
		private List<Team> TeamStanding;
		private int currentRace;
		private int year;
		
		public SessionManager(){
			year=Calendar.getInstance().get(Calendar.YEAR);
		}

		public List<Pilot> getPilotStanding() {
			return pilotStanding;
		}

		public void setPilotStanding(List<Pilot> pilotStanding) {
			this.pilotStanding = pilotStanding;
		}

		public List<Team> getTeamStanding() {
			return TeamStanding;
		}

		public void setTeamStanding(List<Team> teamStanding) {
			TeamStanding = teamStanding;
		}

		public int getCurrentRace() {
			return currentRace;
		}

		public void setCurrentRace(int currentRace) {
			this.currentRace = currentRace;
		}

		public int getYear() {
			return year;
		}

		public void setYear(int year) {
			this.year = year;
		}
		
		
}
