package com.digitreko.games.model;

import java.io.IOException;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


import android.util.Log;

public class F1GameManager implements Serializable {

	
	final int RACE_LAPS = 5;
	final int QUALIFY_LAPS = 2; 	

	private ArrayList<Team> teams;
	private Team PCTeam;			//this will be the team picked by the player or current controlled by PC,
	//tells the system to build default UI with this team and AI ignores this one
	private int gameMode;	
	private  boolean isRace;	//isRace true uses RACE_LAPS, false uses QUALIFY_LAPS
	private  Track actualRace;	
	private  RaceSettings settings;
	private  int currentRace;	
	private static F1GameManager globalGameManager;
	private Manager player;
	private double interest;


	/**
	 * @param args
	 */
	private F1GameManager(){

		teams = new ArrayList<Team>();
		settings = new RaceSettings(2,RaceSettings.HARD);

		//initializeAllTracks();
		//initializeSeason();
		initializeTeams();
		currentRace = 1;
		interest = 0;	}


	public static F1GameManager getInstance(){
		if (globalGameManager == null){
			globalGameManager = new F1GameManager();
		}
		return globalGameManager;
	}



	public Track getTrack(int currentRace) throws Exception{
		Track pista = null;
		switch (currentRace){
		
		case 1:
		pista = new Track("Melbourne, Australia",	//length,difficulty
				"1Albert_Park_Melbourne_large.jpg",

				(new int[]{5,2}),(new int[]{5,3}),
				(new int[]{4,6}),(new int[]{6,5}),
				(new int[]{3,9}),(new int[]{3,8}),
				(new int[]{4,5}),(new int[]{4,4}),
				(new int[]{3,1}),(new int[]{3,2}));

		break;
		case 2:
		pista = new Track("Jackarta, Malaysia",
				"1Albert_Park_Melbourne_large.jpg",

				(new int[]{5,2}),(new int[]{5,2}),
				(new int[]{4,2}),(new int[]{3,1}),
				(new int[]{3,2}),(new int[]{5,2}),
				(new int[]{3,2}),(new int[]{3,3}),
				(new int[]{3,6}),(new int[]{3,2}));

		break;
		case 3:		
		pista = new Track("Bharain, Bahrain",
				"1Kuala_Lumpur_Malaysia_compact.jpg",

				(new int[]{4,1}),(new int[]{2,3}),
				(new int[]{2,1}),(new int[]{2,3}),
				(new int[]{2,5}),(new int[]{2,1}),
				(new int[]{2,8}),(new int[]{2,4}),
				(new int[]{2,1}),(new int[]{2,6}));
		break;
		case 4:
		pista = new Track("China",
				"1Shanghai_China_compact.jpg",
				(new int[]{3,1}),(new int[]{4,8}),
				(new int[]{2,5}),(new int[]{2,2}),
				(new int[]{2,2}),(new int[]{3,5}),
				(new int[]{3,5}),(new int[]{2,3}),
				(new int[]{4,1}),(new int[]{4,7}));
		break;
		case 5:
		pista = new Track("Spain",
				"1Catalunya_Spain_compact.jpg",
				(new int[]{2,1}),(new int[]{2,6}),
				(new int[]{2,7}),(new int[]{2,2}),
				(new int[]{2,2}),(new int[]{2,6}),
				(new int[]{2,2}),(new int[]{2,2}),
				(new int[]{2,2}),(new int[]{2,1}));
		break;
		case 6:
		pista = new Track("Monaco",
				"1Monte_Carlo_Monaco_compact.jpg",
				(new int[]{2,2}),(new int[]{2,1}),
				(new int[]{2,2}),(new int[]{2,2}),
				(new int[]{2,8}),(new int[]{2,7}),
				(new int[]{2,2}),(new int[]{2,2}),
				(new int[]{2,8}),(new int[]{2,5}));
		break;
		case 7:
		pista = new Track("Canada",
				"1Gilles_Villeneuve_Canada_compact.jpg",
				(new int[]{2,5}),(new int[]{2,3}),
				(new int[]{2,3}),(new int[]{2,6}),
				(new int[]{2,3}),(new int[]{2,1}),
				(new int[]{2,9}),(new int[]{2,2}),
				(new int[]{2,1}),(new int[]{2,5}));
		break;
		case 8:
		pista = new Track("Austria",
				"1Red_Bull_Ring_compact.jpg",
				(new int[]{2,1}),(new int[]{2,4}),
				(new int[]{2,1}),(new int[]{2,2}),
				(new int[]{2,7}),(new int[]{2,2}),
				(new int[]{2,6}),(new int[]{2,7}),
				(new int[]{2,6}),(new int[]{2,5}));
		break;
		case 9:
		pista = new Track("Great Britain",
				"1Silverstone_England_compact.jpg",
				(new int[]{2,2}),(new int[]{2,6}),
				(new int[]{2,1}),(new int[]{2,8}),
				(new int[]{2,2}),(new int[]{2,3}),
				(new int[]{2,4}),(new int[]{2,1}),
				(new int[]{2,2}),(new int[]{2,6}));
		break;
		case 10:
		pista = new Track("Germany",
				"1Hockenheimring_Germany_compact.jpg",
				(new int[]{2,2}),(new int[]{2,5}),
				(new int[]{2,1}),(new int[]{2,8}),
				(new int[]{2,3}),(new int[]{2,7}),
				(new int[]{2,2}),(new int[]{2,5}),
				(new int[]{2,5}),(new int[]{2,4}));
		break;
		case 11:
		pista = new Track("Hungary",
				"1Budapest_Hungary_compact.jpg",
				(new int[]{2,1}),(new int[]{2,4}),
				(new int[]{2,5}),(new int[]{2,2}),
				(new int[]{2,3}),(new int[]{2,5}),
				(new int[]{2,5}),(new int[]{2,4}),
				(new int[]{2,8}),(new int[]{2,6}));
		break;
		case 12:
		pista = new Track("Belgium",
				"1Spa-Francorchamps_Belgium_compact.jpg",
				(new int[]{2,6}),(new int[]{2,2}),
				(new int[]{2,1}),(new int[]{2,4}),
				(new int[]{2,7}),(new int[]{2,4}),
				(new int[]{2,4}),(new int[]{2,4}),
				(new int[]{2,3}),(new int[]{2,7}));
		break;
		case 13:
		pista = new Track("Italia",
				"1Monza_Italy_-_Copy_compact.jpg",
				(new int[]{2,1}),(new int[]{2,7}),
				(new int[]{2,2}),(new int[]{2,3}),
				(new int[]{2,3}),(new int[]{2,3}),
				(new int[]{2,1}),(new int[]{2,3}),
				(new int[]{2,1}),(new int[]{2,4}));
		break;
		case 14:
		pista = new Track("Singapore",
				"1Marina_Bay_Singapore_compact.jpg",
				(new int[]{2,8}),(new int[]{2,3}),
				(new int[]{2,5}),(new int[]{2,7}),
				(new int[]{2,6}),(new int[]{2,7}),
				(new int[]{2,5}),(new int[]{2,4}),
				(new int[]{2,4}),(new int[]{2,3}));
		break;
		case 15:		
		pista = new Track("Japan",
				"1Suzuka_Japan_compact.jpg",
				(new int[]{2,3}),(new int[]{2,4}),
				(new int[]{2,6}),(new int[]{2,5}),
				(new int[]{2,9}),(new int[]{2,7}),
				(new int[]{2,8}),(new int[]{2,4}),
				(new int[]{2,7}),(new int[]{2,3}));
		break;
		case 16:
		pista = new Track("Russia",
				"1sochi.png",		//attention here, artifacts incoming
				(new int[]{2,3}),(new int[]{2,5}),
				(new int[]{2,6}),(new int[]{2,7}),
				(new int[]{2,7}),(new int[]{2,7}),
				(new int[]{2,3}),(new int[]{2,8}),
				(new int[]{2,5}),(new int[]{2,5}));
		break;
		case 17:		
		pista = new Track("United States",
				"1Circuit Americas_Austin.jpg",
				(new int[]{2,3}),(new int[]{2,3}),
				(new int[]{2,3}),(new int[]{2,5}),
				(new int[]{2,9}),(new int[]{2,1}),
				(new int[]{2,8}),(new int[]{2,7}),
				(new int[]{2,7}),(new int[]{2,6}));
		break;
		case 18:
		pista = new Track("Brazil",
				"1Sao_Paulo_Brazil_compact.jpg",
				(new int[]{2,3}),(new int[]{2,2}),
				(new int[]{2,1}),(new int[]{2,4}),
				(new int[]{2,2}),(new int[]{2,4}),
				(new int[]{2,7}),(new int[]{2,8}),
				(new int[]{2,8}),(new int[]{2,5}));
		break;
		case 19:
		pista = new Track("United Arab Emirates",
				"1Yas_Marina_Abu_Dhabi_compact.jpg",
				(new int[]{2,6}),(new int[]{2,5}),
				(new int[]{2,3}),(new int[]{2,8}),
				(new int[]{2,2}),(new int[]{2,5}),
				(new int[]{2,3}),(new int[]{2,6}),
				(new int[]{2,7}),(new int[]{2,7}));

		break;
		default:
			throw new Exception("Invalid track index.");

		}
		return pista;
	}


	private   void initializeSeason() {		
		//actualRace = tracks.get(currentRace);		
	}
	

	private void simulateRace() {		
		for (int volta = 0;volta < RACE_LAPS;volta++){
			System.out.println("\nVOLTA "+(volta+1));
			int sectionNumber = 0;
			for (Section currSect: actualRace.getSections()){				
				sectionNumber++;
				for (Team currTeam: teams){										
					calculatePerformanceInSection(currSect, currTeam);
				}
				//System.out.println("END OF SECTION "+sectionNumber);				
			}
			showLapResults();
		}
		setRaceFinalResults();	
	}

	//move to RaceCar
	private   void showLapResults() {
		for (Team currTeam: teams){										
			currTeam.showPilotStandingInLap(currTeam);
		}
	}

	private   void setRaceFinalResults(){		
		List<Pilot> pilots = getAllPilots("outcome");		
		pilots.get(0).addPoints(25);	
		System.out.println(pilots.get(0).getName() + " recebeu 25 pontos. Total= "+pilots.get(0).getSeasonPoints());
		pilots.get(1).addPoints(18);
		System.out.println(pilots.get(1).getName() + " recebeu 18 pontos. Total= "+pilots.get(1).getSeasonPoints());
		pilots.get(2).addPoints(15);
		System.out.println(pilots.get(2).getName() + " recebeu 15 pontos. Total= "+pilots.get(2).getSeasonPoints());
		pilots.get(3).addPoints(12);
		System.out.println(pilots.get(3).getName() + " recebeu 12 pontos. Total= "+pilots.get(3).getSeasonPoints());
		pilots.get(4).addPoints(10);
		System.out.println(pilots.get(4).getName() + " recebeu 10 pontos. Total= "+pilots.get(4).getSeasonPoints());
		pilots.get(5).addPoints(8);
		System.out.println(pilots.get(5).getName() + " recebeu 8 pontos. Total= "+pilots.get(5).getSeasonPoints());
		pilots.get(6).addPoints(6);
		System.out.println(pilots.get(6).getName() + " recebeu 6 pontos. Total= "+pilots.get(6).getSeasonPoints());
		pilots.get(7).addPoints(4);
		System.out.println(pilots.get(7).getName() + " recebeu 4 pontos. Total= "+pilots.get(7).getSeasonPoints());
		pilots.get(8).addPoints(2);
		System.out.println(pilots.get(8).getName() + " recebeu 2 pontos. Total= "+pilots.get(8).getSeasonPoints());
		pilots.get(9).addPoints(1);
		System.out.println(pilots.get(9).getName() + " recebeu 1 pontos. Total= "+pilots.get(9).getSeasonPoints());

		Collections.sort(teams, new Team.PointsComparator());

		for (Team team: teams){
			System.out.println(team.getName()+" tem "+team.getSeasonPoints()+" pontos na temporada.");
		}

		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		clearPastOutcome();	//resetCarStats/money sponsors etc
		currentRace++;
	}

	private   List<RaceCar> getAllCars(){
		List<RaceCar> cars = new ArrayList<RaceCar>();
		for (Team team: teams){
			cars.addAll(team.getCars());			
		}
		return cars;
	}
	private   List<Pilot> getAllPilots(String sortedBy){
		List<Pilot> pilots = new ArrayList<Pilot>();
		for (RaceCar car:getAllCars()){
			pilots.add(car.getDriver());
		}				
		if (sortedBy.equals("outcome")){
			Collections.sort(pilots,new Pilot.OutcomeComparator());
		}
		else if(sortedBy.equals("points")){
			Collections.sort(pilots,new Pilot.PointsComparator());
		}
		return pilots;
	}
	
	public int getPlayerTeamPosition(){		
		Collections.sort(teams,new Team.ConstructorPointsComparator());
		Team pct = getPCTeam();		
		return teams.lastIndexOf(pct);
	}

	//working
	private   void clearPastOutcome() {		
		for (RaceCar c:getAllCars()){
			c.getDriver().setOutcomeAccumulator(0);
		}				
	}

	private   void calculatePerformanceInSection(Section currSect, Team currTeam) {
		ArrayList<RaceCar> cars = (ArrayList<RaceCar>) currTeam.getCars();		
		for (RaceCar currCar: cars){			
			currCar.calculatePerformance(currSect.getDificulty(),settings.getAggressivness());					
		}
	}


	public void initializeTeams(){		

		//case t0 decide which team was selected
		//in case of custom create team with data and add to array
		Team redBullRacing = new Team(Codes.teamAndCarCodes.RBR1);
		Team mcLaren = new Team(Codes.teamAndCarCodes.MCL1);
		Team ferrari =  new Team(Codes.teamAndCarCodes.FER1);
		Team lotus =  new Team(Codes.teamAndCarCodes.LOT1);
		Team mercedes =  new Team(Codes.teamAndCarCodes.MER1);
		Team sauber =  new Team(Codes.teamAndCarCodes.SAU1);
		Team forceIndia =  new Team(Codes.teamAndCarCodes.FOR1);
		Team willians =  new Team(Codes.teamAndCarCodes.WIL1);
		Team toroRosso =  new Team(Codes.teamAndCarCodes.TOR1);
		Team caterhan =  new Team(Codes.teamAndCarCodes.CAT1);

		Team marussia =  new Team(Codes.teamAndCarCodes.MAR1);
		
		

		//teams.add(customTeam);
		teams.add(caterhan);
		teams.add(redBullRacing);
		teams.add(mcLaren);
		teams.add(ferrari);
		teams.add(lotus);
		teams.add(mercedes);
		teams.add(sauber);
		teams.add(forceIndia);
		teams.add(willians);
		teams.add(toroRosso);

		teams.add(marussia);
		
	}
	
	public void setPlayerControlledTeam(String selectedTeam){
		System.out.println("Team: "+selectedTeam);
		for (Team t: teams){
			if (t.getName().equals(selectedTeam)){
				t.setPlayerControlled(true);
			}
		}
		setToLowestValuesStartGame(getSponsor(0));
	}

	private void printInfoAboutAllTeams(){
		for (Team t: teams){
			Log.w("TeamInfo",t.toString());
		}
	}
	
	private void setToLowestValuesStartGame(Sponsor sponsor){
		getPCTeam().setToLowestValues(sponsor);		
	}
	
	private Sponsor getSponsor(int level){
		Sponsor s = null;
		if (level == 0)
			s = new Sponsor("HP",300000,1);
		if (level == 1)
			s = new Sponsor("Microsoft",500000,2);
		if (level == 2)
			s = new Sponsor("IBM",600000,4);
		if (level == 3)
			s = new Sponsor("MegaPetro",700000,8);
		if (level == 4)
			s = new Sponsor("Osama Holdings",1000000,10);
		return s;		
	}


	public   ArrayList<Team> getTeams() {
		return teams;
	}


	public   void setTeams(ArrayList<Team> teams) {
		this.teams = teams;
	}


	public Team getPCTeam() {		
		Team team = null;
		for (Team t: teams){
			if (t.isPlayerControlled()){
				team = t;					
			}
		}
		return team;
	}

	public void setPCTeam(Team pCTeam) {
		PCTeam = pCTeam;
	}


	public   int getGameMode() {
		return gameMode;
	}


	public   void setGameMode(int gameMode) {
		this.gameMode = gameMode;
	}


	public   boolean isRace() {
		return isRace;
	}


	public   void setRace(boolean isRace) {
		this.isRace = isRace;
	}


	public   Track getActualRace() {
		return actualRace;
	}


	public   void setActualRace(Track actualRace) {
		this.actualRace = actualRace;
	}


	public   RaceSettings getSettings() {
		return settings;
	}


	public   void setSettings(RaceSettings settings) {
		this.settings = settings;
	}


	public   int getCurrentRace() {
		return currentRace;
	}


	public   void setCurrentRace(int currentRace) {
		this.currentRace = currentRace;
	}

	public   int getRaceLaps() {
		return RACE_LAPS;
	}


	public   int getQualifyLaps() {
		return QUALIFY_LAPS;
	}

	public Manager getPlayer() {
		return player;
	}

	public void setPlayer(Manager player) {
		this.player = player;
	}
	
	public double generateInterest(){
		//this method will generate round interest based in a lot of factors
		//refer to design manual
		return 10;
	}
	

}
