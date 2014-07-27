package com.digitreko.games.model;

import java.io.Serializable;
import java.util.Calendar;

public class SessionManager implements Serializable{
	
		/**
	 * 
	 */
	private static final long serialVersionUID = -4905054272835099711L;
		private int currentRace;
		private int year;
		
		public SessionManager(){
			year=Calendar.getInstance().get(Calendar.YEAR);
			currentRace = 1;
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
		@Override
		public String toString() {
			return "SessionManager [currentRace=" + currentRace + ", year="
					+ year + "]";
		}
		
		
}
