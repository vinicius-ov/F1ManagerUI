package com.digitreko.games.logica;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;


//car-to-team codes are in the spreadsheet 
public class Team {
	public final int MAX_PILOTS = 4;
	public final int MAX_COLORS = 2;

	private String name;			//name of team
	private List<String> colors;
	private List<RaceCar> cars;
	private String engine;	//just a name
	private int engineers;
	private int planners;
	private int rAndD;
	private int fanCount;
	private int reputation;
	private int funds;
	private List<Sponsor> sponsors;
	private boolean playerControlled;

	public Team (){
	
	}
	
	public Team (String nome,String motor,int engenheiros,int planners){
		name =nome;
		engine = motor;
		engineers = engenheiros;
		this.planners = planners;
	}
	
	public Team(Codes.teamAndCarCodes code) {
		//pilots = new Pilot[4];
		//colors = new String[2];
		sponsors = new ArrayList<Sponsor>();
		colors = new ArrayList<String>();
		cars = new ArrayList<RaceCar>();
		playerControlled = false;
		
		switch (code){
		case RBR1:{
			name = "Red Bull Racing";
			engine = "Renault";
			engineers = 7;
			planners = 7;
			rAndD = 7;
			fanCount = 50000;
			reputation = 70;
			colors.add("White");
			colors.add("Black");
			cars.add(new RaceCar(code,new Pilot(Codes.pilotCodes.Vettel)));
			cars.add(new RaceCar(code,new Pilot(Codes.pilotCodes.Webber)));
		}
		break;
		case MCL1:{
			name = "McLaren";
			engine = "Mercedes";
			engineers = 7;
			planners = 6;
			rAndD = 8;
			fanCount = 100000;
			reputation = 70;
			colors.add("White");
			colors.add("Gold");
			cars.add(new RaceCar(code,new Pilot(Codes.pilotCodes.Button)));
			cars.add(new RaceCar(code,new Pilot(Codes.pilotCodes.Perez)));			
		}
		break;
		case FER1:{
			name = "Ferrari";
			engine = "Ferrari";
			engineers = 7;
			planners = 8;
			rAndD = 8;
			fanCount = 100000;
			reputation = 70;
			colors.add("Red");
			colors.add("Black");
			cars.add(new RaceCar(code,new Pilot(Codes.pilotCodes.Alonso)));
			cars.add(new RaceCar(code,new Pilot(Codes.pilotCodes.Massa)));			
		}
		break;
		case LOT1:{
			name = "Lotus";
			engine = "Mercedes";
			engineers = 7;
			planners = 8;
			rAndD = 8;
			fanCount = 100000;
			reputation = 70;
			colors.add("Red");
			colors.add("Black");
			cars.add(new RaceCar(code,new Pilot(Codes.pilotCodes.Raikkonen)));
			cars.add(new RaceCar(code,new Pilot(Codes.pilotCodes.Grosjean)));			
		}
		break;
		case MER1:{
			name = "Mercedes";
			engine = "Mercedes";
			engineers = 7;
			planners = 8;
			rAndD = 8;
			fanCount = 100000;
			reputation = 70;
			colors.add("Red");
			colors.add("Black");
			cars.add(new RaceCar(code,new Pilot(Codes.pilotCodes.Rosberg)));
			cars.add(new RaceCar(code,new Pilot(Codes.pilotCodes.Hamilton)));			
		}
		break;
		case SAU1:{
			name = "Sauber";
			engine = "Ferrari";
			engineers = 7;
			planners = 6;
			rAndD = 6;
			fanCount = 80000;
			reputation = 50;
			colors.add("Red");
			colors.add("Black");
			cars.add(new RaceCar(code,new Pilot(Codes.pilotCodes.Hulkenberg)));
			cars.add(new RaceCar(code,new Pilot(Codes.pilotCodes.Gutierrez)));			
		}
		break;
		case FOR1:{
			name = "Force India";
			engine = "Mercedes";
			engineers = 5;
			planners = 6;
			rAndD = 6;
			fanCount = 50000;
			reputation = 50;
			colors.add("Red");
			colors.add("Black");
			cars.add(new RaceCar(code,new Pilot(Codes.pilotCodes.Resta)));
			cars.add(new RaceCar(code,new Pilot(Codes.pilotCodes.Sutil)));			
		}
		break;
		case WIL1:{
			name = "Willians";
			engine = "Renault";
			engineers = 9;
			planners = 6;
			rAndD = 8;
			fanCount = 50000;
			reputation = 50;
			colors.add("Red");
			colors.add("Black");
			cars.add(new RaceCar(code,new Pilot(Codes.pilotCodes.Maldonado)));
			cars.add(new RaceCar(code,new Pilot(Codes.pilotCodes.Bottas)));			
		}
		break;
		case TOR1:{
			name = "Toro Rosso";
			engine = "Ferrari";
			engineers = 9;
			planners = 6;
			rAndD = 8;
			fanCount = 50000;
			reputation = 50;
			colors.add("Red");
			colors.add("Black");
			cars.add(new RaceCar(code,new Pilot(Codes.pilotCodes.Vergne)));
			cars.add(new RaceCar(code,new Pilot(Codes.pilotCodes.Ricciardo)));			
		}
		break;
		case CAT1:{
			name = "Caterhan";
			engine = "Renault";
			engineers = 9;
			planners = 6;
			rAndD = 8;
			fanCount = 50000;
			reputation = 50;
			colors.add("Red");
			colors.add("Black");
			cars.add(new RaceCar(code,new Pilot(Codes.pilotCodes.Pic)));
			cars.add(new RaceCar(code,new Pilot(Codes.pilotCodes.Garde)));			
		}
		break;
		case MAR1:{
			name = "Marussia";
			engine = "Cosworth";
			engineers = 6;
			planners = 6;
			rAndD = 5;
			fanCount = 50000;
			reputation = 50;
			colors.add("Red");
			colors.add("Black");
			cars.add(new RaceCar(code,new Pilot(Codes.pilotCodes.Bianchi)));
			cars.add(new RaceCar(code,new Pilot(Codes.pilotCodes.Chilton)));			
		}
		break;
		default:{	//DEFAULT DEVE CRIAR O CUSTOM TEAM?? DEVE DIFERENCIAR VALIDOS DE INVALIDOS
			System.out.println("ERROR! Team code not found. Team not created!");
		}
		}

	}

	public int getPlanners() {
		return planners;
	}


	public void setPlanners(int planners) {
		this.planners = planners;
	}


	public int getFanCount() {
		return fanCount;
	}


	public void setFanCount(int fanCount) {
		this.fanCount = fanCount;
	}


	public int getReputation() {
		return reputation;
	}

	public void setReputation(int reputation) {
		this.reputation = reputation;
	}


	public int getEngineers() {
		return engineers;
	}

	public void setEngineers(int engineers) {
		this.engineers = engineers;
	}

	public int getFunds() {
		return funds;
	}

	public void setFunds(int funds) {
		this.funds = funds;
	}

	public int getrAndD() {
		return rAndD;
	}

	public void setrAndD(int rAndD) {
		this.rAndD = rAndD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEngine() {
		return engine;
	}

	public void setEngine(String engine) {
		this.engine = engine;
	}


	public List<Sponsor> getSponsors() {
		return sponsors;
	}

	public void setSponsors(List<Sponsor> sponsors) {
		this.sponsors = sponsors;
	}

	public List<String> getColors() {
		return colors;
	}

	public void setColors(List<String> colors) {
		this.colors = colors;
	}

	public List<RaceCar> getCars() {
		return cars;
	}

	public void setCars(List<RaceCar> cars) {
		this.cars = cars;
	}
	
	public boolean isPlayerControlled() {
		return playerControlled;
	}

	public void setPlayerControlled(boolean playerControlled) {
		this.playerControlled = playerControlled;
	}
	
	public int getSeasonPoints(){		
		return  cars.get(0).getDriver().getSeasonPoints() + cars.get(1).getDriver().getSeasonPoints();
		
	}

	@Override
	public String toString() {
		return "Team [MAX_PILOTS=" + MAX_PILOTS + ", MAX_COLORS=" + MAX_COLORS
				+ ", name=" + name + ", colors=" + colors + ", cars=" + cars
				+ ", engine=" + engine + ", engineers=" + engineers
				+ ", planners=" + planners + ", rAndD=" + rAndD + ", fanCount="
				+ fanCount + ", reputation=" + reputation + ", sponsors="
				+ sponsors + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + MAX_COLORS;
		result = prime * result + MAX_PILOTS;
		result = prime * result + ((cars == null) ? 0 : cars.hashCode());
		result = prime * result + ((colors == null) ? 0 : colors.hashCode());
		result = prime * result + ((engine == null) ? 0 : engine.hashCode());
		result = prime * result + engineers;
		result = prime * result + fanCount;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + planners;
		result = prime * result + rAndD;
		result = prime * result + reputation;
		result = prime * result
				+ ((sponsors == null) ? 0 : sponsors.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Team other = (Team) obj;
		if (MAX_COLORS != other.MAX_COLORS)
			return false;
		if (MAX_PILOTS != other.MAX_PILOTS)
			return false;
		if (cars == null) {
			if (other.cars != null)
				return false;
		} else if (!cars.equals(other.cars))
			return false;
		if (colors == null) {
			if (other.colors != null)
				return false;
		} else if (!colors.equals(other.colors))
			return false;
		if (engine == null) {
			if (other.engine != null)
				return false;
		} else if (!engine.equals(other.engine))
			return false;
		if (engineers != other.engineers)
			return false;
		if (fanCount != other.fanCount)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (planners != other.planners)
			return false;
		if (rAndD != other.rAndD)
			return false;
		if (reputation != other.reputation)
			return false;
		if (sponsors == null) {
			if (other.sponsors != null)
				return false;
		} else if (!sponsors.equals(other.sponsors))
			return false;
		return true;
	}

	public void showPilotStandingInLap(Team currTeam) {
		System.out.println(currTeam.getCars().get(0).getDriver().getName()+"= "+currTeam.getCars().get(0).getDriver().getOutcomeAccumulator());
		System.out.println(currTeam.getCars().get(1).getDriver().getName()+"= "+currTeam.getCars().get(1).getDriver().getOutcomeAccumulator());
	}
	
	public void removeSponsorByName(String sponsorName){
		for (Sponsor s: sponsors){
			if (s.getName().equals(sponsorName)){
				sponsors.remove(s);
			}
		}
	}
	public static class PointsComparator implements Comparator<Team>{

		@Override
		public int compare(Team arg0, Team arg1) {
			return arg1.getSeasonPoints() - arg0.getSeasonPoints();
		}
				
	}
	
}

