package com.digitreko.games.logica;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class F1Manager {

	static final int RACE_LAPS = 5;
	static final int QUALIFY_LAPS = 2; 	
	
	static ArrayList<Team> teams;
	Team PCTeam;			//this will be the team picked by the player or current controlled by PC,
							//tells the system to build default UI with this team and AI ignores this one
	static int gameMode;	
	static boolean isRace;	//isRace true uses RACE_LAPS, false uses QUALIFY_LAPS
	static Track actualRace;
	static List<Standing> standings; 
	static List<Track> tracks;
	static RaceSettings settings;
	static int currentRace;
	static List<Sponsor> sponsors;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		teams = new ArrayList<Team>();
		standings = new ArrayList<Standing>();
		settings = new RaceSettings(2,RaceSettings.HARD);
		initializeAllTracks();
		initializeTrack();
		initializeTeams();
		currentRace = 0;
		System.out.println("Welcome to F1 Manager");
		System.out.println("Choose game mode: 1-Carrer 2-Team Manager");
		System.out.println("In carreer mode you will must pave your way to the top managing succesfully each team that hires you.");
		System.out.println("In Team Manager you will pick a team (custom or existent) and make it the best team of all history.");
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String sampleString;
		try {
			sampleString = br.readLine();
			switch(Integer.valueOf(sampleString)){
			case 1:{
				gameMode = 1;
				System.out.println("Very well. You will start in Force India.");	
				System.out.println("In real game your team will be shuffled between Force India, Marussia and Caterham.");
				System.out.println("Your pilots will be default of 2013 championship, both from Force and both form RBR.");
				System.out.println("Manage well and be promoted. Manage bad and be fired.");
				System.out.println("You are in the main menu. From here you can train your staff, check sponsors, fans and everything.");
				System.out.println("Now you will race. It will be your team against RBR. Good luck.");				
				break;
			}
			case 2:{
				gameMode = 2;
				System.out.println("Do you want to pick a existing team (1) or create your own? (2)");
				sampleString = br.readLine();
				switch(Integer.valueOf(sampleString)){
				case 1: //selected teams become player team
					System.out.println("CHOOSE EXISTING TEAM DIALOG...");
				break;
				case 2: //laucnh team creator activity, then after confirmed add this team to the team list and make main team
					System.out.println("CREATE-A-TEAM DIALOG...:");
					Team playerTeam = new Team();
					System.out.println("Team name: ");
					playerTeam.setName(br.readLine());
					System.out.println("Your name (this is the first thing to be asked, before asking which game mode...");
					Manager playerManager = new Manager();
					playerManager.setName(br.readLine());					
					
				break;	
				}				
				System.out.println("Very well. You will start in Force India.");	
				System.out.println("In real game your team will be shuffled between Force India, Marussia and Caterham.");
				System.out.println("Your pilots will be default of 2013 championship, both from Force and both form RBR.");
				System.out.println("Manage well and gain championships, great sponsors and fans.");
				System.out.println("Manage bad, lose money and game over.");
				break;
			}			
			}
			System.out.println("The first screen will be the managing view.");
			System.out.println("From here you will be able to managa every aspect of the team. Pilots, cars and finance.");
			System.out.println("Also info about the next race and news and trends you be shown.");
			System.out.println("Let's race!");
			simulateSeason();			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//System.gc();
	}
	
		
	private static void initializeAllTracks() {
		tracks = new ArrayList<Track>();
		Track pista = new Track("Australia",	//length,difficulty
				(new int[]{5,2}),(new int[]{5,3}),
				(new int[]{4,6}),(new int[]{6,5}),
				(new int[]{3,9}),(new int[]{3,8}),
				(new int[]{4,5}),(new int[]{4,4}),
				(new int[]{3,1}),(new int[]{3,2}));
		tracks.add(pista);
		pista = new Track("Malaysia",
				(new int[]{5,2}),(new int[]{5,2}),
				(new int[]{4,2}),(new int[]{3,1}),
				(new int[]{3,2}),(new int[]{5,2}),
				(new int[]{3,2}),(new int[]{3,3}),
				(new int[]{3,6}),(new int[]{3,2}));
		tracks.add(pista);		
		pista = new Track("Bahrain",
				(new int[]{4,1}),(new int[]{2,3}),
				(new int[]{2,1}),(new int[]{2,3}),
				(new int[]{2,5}),(new int[]{2,1}),
				(new int[]{2,8}),(new int[]{2,4}),
				(new int[]{2,1}),(new int[]{2,6}));
		tracks.add(pista);
		pista = new Track("China",
				(new int[]{3,1}),(new int[]{4,8}),
				(new int[]{2,5}),(new int[]{2,2}),
				(new int[]{2,2}),(new int[]{3,5}),
				(new int[]{3,5}),(new int[]{2,3}),
				(new int[]{4,1}),(new int[]{4,7}));
		tracks.add(pista);
		pista = new Track("Spain",
				(new int[]{2,1}),(new int[]{2,6}),
				(new int[]{2,7}),(new int[]{2,2}),
				(new int[]{2,2}),(new int[]{2,6}),
				(new int[]{2,2}),(new int[]{2,2}),
				(new int[]{2,2}),(new int[]{2,1}));
		tracks.add(pista);
		pista = new Track("Monaco",
				(new int[]{2,2}),(new int[]{2,1}),
				(new int[]{2,2}),(new int[]{2,2}),
				(new int[]{2,8}),(new int[]{2,7}),
				(new int[]{2,2}),(new int[]{2,2}),
				(new int[]{2,8}),(new int[]{2,5}));
		tracks.add(pista);
		pista = new Track("Canada",
				(new int[]{2,5}),(new int[]{2,3}),
				(new int[]{2,3}),(new int[]{2,6}),
				(new int[]{2,3}),(new int[]{2,1}),
				(new int[]{2,9}),(new int[]{2,2}),
				(new int[]{2,1}),(new int[]{2,5}));
		tracks.add(pista);
		pista = new Track("Austria",
				(new int[]{2,1}),(new int[]{2,4}),
				(new int[]{2,1}),(new int[]{2,2}),
				(new int[]{2,7}),(new int[]{2,2}),
				(new int[]{2,6}),(new int[]{2,7}),
				(new int[]{2,6}),(new int[]{2,5}));
		tracks.add(pista);
		pista = new Track("Great Britain",
				(new int[]{2,2}),(new int[]{2,6}),
				(new int[]{2,1}),(new int[]{2,8}),
				(new int[]{2,2}),(new int[]{2,3}),
				(new int[]{2,4}),(new int[]{2,1}),
				(new int[]{2,2}),(new int[]{2,6}));
		tracks.add(pista);
		pista = new Track("Germany",
				(new int[]{2,2}),(new int[]{2,5}),
				(new int[]{2,1}),(new int[]{2,8}),
				(new int[]{2,3}),(new int[]{2,7}),
				(new int[]{2,2}),(new int[]{2,5}),
				(new int[]{2,5}),(new int[]{2,4}));
		tracks.add(pista);
		pista = new Track("Hungary",
				(new int[]{2,1}),(new int[]{2,4}),
				(new int[]{2,5}),(new int[]{2,2}),
				(new int[]{2,3}),(new int[]{2,5}),
				(new int[]{2,5}),(new int[]{2,4}),
				(new int[]{2,8}),(new int[]{2,6}));
		tracks.add(pista);
		pista = new Track("Belgium",
				(new int[]{2,6}),(new int[]{2,2}),
				(new int[]{2,1}),(new int[]{2,4}),
				(new int[]{2,7}),(new int[]{2,4}),
				(new int[]{2,4}),(new int[]{2,4}),
				(new int[]{2,3}),(new int[]{2,7}));
		tracks.add(pista);
		pista = new Track("Italia",
				(new int[]{2,1}),(new int[]{2,7}),
				(new int[]{2,2}),(new int[]{2,3}),
				(new int[]{2,3}),(new int[]{2,3}),
				(new int[]{2,1}),(new int[]{2,3}),
				(new int[]{2,1}),(new int[]{2,4}));
		tracks.add(pista);
		pista = new Track("Singapore",
				(new int[]{2,8}),(new int[]{2,3}),
				(new int[]{2,5}),(new int[]{2,7}),
				(new int[]{2,6}),(new int[]{2,7}),
				(new int[]{2,5}),(new int[]{2,4}),
				(new int[]{2,4}),(new int[]{2,3}));
		tracks.add(pista);		
		pista = new Track("Japan",
				(new int[]{2,3}),(new int[]{2,4}),
				(new int[]{2,6}),(new int[]{2,5}),
				(new int[]{2,9}),(new int[]{2,7}),
				(new int[]{2,8}),(new int[]{2,4}),
				(new int[]{2,7}),(new int[]{2,3}));
		tracks.add(pista);
		pista = new Track("Russia",
				(new int[]{2,3}),(new int[]{2,5}),
				(new int[]{2,6}),(new int[]{2,7}),
				(new int[]{2,7}),(new int[]{2,7}),
				(new int[]{2,3}),(new int[]{2,8}),
				(new int[]{2,5}),(new int[]{2,5}));
		tracks.add(pista);		
		pista = new Track("United States",
				(new int[]{2,3}),(new int[]{2,3}),
				(new int[]{2,3}),(new int[]{2,5}),
				(new int[]{2,9}),(new int[]{2,1}),
				(new int[]{2,8}),(new int[]{2,7}),
				(new int[]{2,7}),(new int[]{2,6}));
		tracks.add(pista);
		pista = new Track("Brazil",
				(new int[]{2,3}),(new int[]{2,2}),
				(new int[]{2,1}),(new int[]{2,4}),
				(new int[]{2,2}),(new int[]{2,4}),
				(new int[]{2,7}),(new int[]{2,8}),
				(new int[]{2,8}),(new int[]{2,5}));
		tracks.add(pista);
		pista = new Track("United Arab Emirates",
				(new int[]{2,6}),(new int[]{2,5}),
				(new int[]{2,3}),(new int[]{2,8}),
				(new int[]{2,2}),(new int[]{2,5}),
				(new int[]{2,3}),(new int[]{2,6}),
				(new int[]{2,7}),(new int[]{2,7}));
		tracks.add(pista);
	}
	
	private static void initializeTrack() {		
		actualRace = tracks.get(currentRace);
	}

	private static void simulateSeason(){
		while (currentRace < tracks.size())
		{
			//preRaceSettings();
			System.out.println("\nGp de "+tracks.get(currentRace).getCountry());
			simulateRace();
			
		}
		showSeasonResults();
	}
	
	private static void preRaceSettings() {
		System.out.println("Repair?");
		System.out.println("Race?");
		
	}


	private static void showSeasonResults() {
		for (Pilot p: getAllPilots("points")){
			System.out.println(p.getName()+" fez "+ p.getSeasonPoints()+" na temporada.");
		}
		for (Team team: teams){
			System.out.println(team.getName()+" tem "+team.getSeasonPoints()+" pontos na temporada.");
		}
	}


	private static void simulateRace() {		
		System.out.println("Let the race begin. Each track has several sectors and in each sector" +
				"is calculated the rendimento, considering the number of cars in the sector, the condition of car's systems," +
				"the agressivness of the pilot and the tactics of the team, the dificulty of ending curve and pilot's skill.");
		System.out.println("All these factors decide if the pilot will break the car or go faster or slower" +
				"during the section. Change in position and damage will be decided per sector. Each" +
				"track will have 10 sector at most.");
		
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
	private static void showLapResults() {
		for (Team currTeam: teams){										
			currTeam.showPilotStandingInLap(currTeam);
		}
	}
	
	private static void setRaceFinalResults(){		
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
	
	private static List<RaceCar> getAllCars(){
		List<RaceCar> cars = new ArrayList<RaceCar>();
		for (Team team: teams){
			cars.addAll(team.getCars());			
		}
		return cars;
	}
	private static List<Pilot> getAllPilots(String sortedBy){
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

	//working
	private static void clearPastOutcome() {		
		for (RaceCar c:getAllCars()){
			c.getDriver().setOutcomeAccumulator(0);
		}				
	}

	private static void calculatePerformanceInSection(Section currSect, Team currTeam) {
		ArrayList<RaceCar> cars = (ArrayList<RaceCar>) currTeam.getCars();		
		for (RaceCar currCar: cars){			
			currCar.calculatePerformance(currSect.getDificulty(),settings.getAggressivness());					
		}
	}

	private static void initializeTeams() {		
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
	
	public void intializeSponsors(){
		sponsors = new ArrayList<Sponsor>();
		Sponsor s = new Sponsor("HP",300000,1);
		sponsors.add(s);
		s = new Sponsor("Microsoft",500000,2);
		sponsors.add(s);
		s = new Sponsor("IBM",600000,4);
		sponsors.add(s);
		s = new Sponsor("MegaPetro",700000,8);
		sponsors.add(s);
		s = new Sponsor("Osama Holdings",1000000,10);
		sponsors.add(s);		
	}
}
