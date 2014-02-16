package com.digitreko.games.logica;

import java.util.Comparator;


public class Pilot{
	private String name;
	private int skill;
	private int age;
	private int reputation;
	private int minSalary;	//decididos por formula
	private int maxSalary;
	private int moral;
	private Integer outcomeAccumulator;
	private int seasonPoints;

    
	public Pilot(){
		
	}
	
	public Pilot(Codes.pilotCodes code){
    	outcomeAccumulator = 0;
		age = 20;
    	reputation = 50; 
    	moral = 50;
    	seasonPoints = 0;
    	
        switch (code){
            case Vettel:{
            	name = "Sebastian Vettel";
            	skill = 80;
            }
            break;
            case Webber:{
            	name = "Mark Webber";
            	skill = 77;
            }
                break;
            case Alonso:{
            	name 	= "Fernando Alonso";
            	skill 	= 80;            	
            }
            break;    
            case Massa:{
            	name 	= "Felipe Massa";
            	skill 	= 72;            	
            }
            break;
            case Button:{
            	name 	= "Jason Button";
            	skill 	= 78;            	
            }
            break;
            case Perez:{
            	name 	= "Sergio Perez";
            	skill 	= 60;            	
            }
            break;           
            case Raikkonen:{
            	name 	= "Kimi Raikkonen";
            	skill 	= 79;            	
            }
            break;
            case Grosjean:{
            	name 	= "Romain Grosjean";
            	skill 	= 75;            	
            }
            break;
            case Rosberg:{
            	name 	= "Nico Rosberg";
            	skill 	= 74;            	
            }
            break;
            case Hamilton:{
            	name 	= "Lewis Hamilton";
            	skill 	= 79;            	
            }
            break;
            case Hulkenberg:{
            	name 	= "Nico Hulkenberg";
            	skill 	= 65;            	
            }
            break;
            case Gutierrez:{
            	name 	= "Esteban Gutierrez";
            	skill 	= 58;            	
            }
            break;
            case Resta:{
            	name 	= "Paul di Resta";
            	skill 	= 65;            	
            }
            break;
            case Sutil:{
            	name 	= "Adrian Sutil";
            	skill 	= 65;            	
            }
            break;
            case Vergne:{
            	name 	= "Jean-Eric Vergne";
            	skill 	= 59;            	
            }
            break;
            case Ricciardo:{
            	name 	= "Daniel Ricciardo";
            	skill 	= 59;            	
            }
            break;
            case Pic:{
            	name 	= "Charles Pic";
            	skill 	= 60;            	
            }
            break;
            case Garde:{
            	name 	= "Giedo van der Garde";
            	skill 	= 65;            	
            }
            break;
            case Maldonado:{
            	name 	= "Pastor Maldonado";
            	skill 	= 65;            	
            }
            break;
            case Bottas:{
            	name 	= "Valttere Bottas";
            	skill 	= 60;            	
            }
            break;
            case Bianchi:{
            	name 	= "Jules Bianchi";
            	skill 	= 65;            	
            }
            break;
            case Chilton:{
            	name 	= "Max Chilton";
            	skill 	= 65;            	
            }
            break;
		case Buemi:
			break;
		case DAmbrosio:
			break;
		case DelaRosa:
			break;
		case Frijns:
			break;
		case Gene:
			break;
		case Gonzales:
			break;
		case Paffett:
			break;
		case Prost:
			break;
		case Rossi:
			break;
		case Turvey:
			break;
		default:
			break;
        }
    }
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getSkill() {
		return skill;
	}

	public void setSkill(int skill) {
		this.skill = skill;
	}

	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getReputation() {
		return reputation;
	}
	public void setReputation(int reputation) {
		this.reputation = reputation;
	}
	
	public int getSeasonPoints() {
		return seasonPoints;
	}

	public void setSeasonPoints(int seasonPoints) {
		this.seasonPoints = seasonPoints;
	}

	public int getMinSalary() {
		return minSalary;
	}
	public void setMinSalary(int minSalary) {
		this.minSalary = minSalary;
	}
	public int getMaxSalary() {
		return maxSalary;
	}
	public int getMoral() {
		return moral;
	}
	public void setMoral(int moral) {
		this.moral = moral;
	}
	public void setMaxSalary(int maxSalary) {
		this.maxSalary = maxSalary;
	}
	public Integer getOutcomeAccumulator() {
		return outcomeAccumulator;
	}
	public void setOutcomeAccumulator(Integer outcomeAccumulator) {
		this.outcomeAccumulator = outcomeAccumulator;
	}
	public void addToOutcomeAccumulator(Integer outcomeAccumulator) {
		this.outcomeAccumulator += outcomeAccumulator;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + skill;
		result = prime * result + maxSalary;
		result = prime * result + minSalary;
		result = prime * result + moral;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + outcomeAccumulator;
		result = prime * result + reputation;
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
		Pilot other = (Pilot) obj;
		if (age != other.age)
			return false;
		if (skill != other.skill)
			return false;
		if (maxSalary != other.maxSalary)
			return false;
		if (minSalary != other.minSalary)
			return false;
		if (moral != other.moral)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (outcomeAccumulator != other.outcomeAccumulator)
			return false;
		if (reputation != other.reputation)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Pilot [name=" + name + ", level=" + skill + ", age=" + age
				+ ", reputation=" + reputation + ", minSalary=" + minSalary
				+ ", maxSalary=" + maxSalary + ", moral=" + moral
				+ ", outcomeAccumulator=" + outcomeAccumulator + "]";
	}

		
	public void addPoints(int points){
		seasonPoints += points;
	}
	
	public static class OutcomeComparator implements Comparator<Pilot>{

		@Override
		public int compare(Pilot arg0, Pilot arg1) {
			return arg1.getOutcomeAccumulator() - arg0.getOutcomeAccumulator();
		}

				
	}
	
	public static class PointsComparator implements Comparator<Pilot>{

		@Override
		public int compare(Pilot arg0, Pilot arg1) {
			return arg1.getSeasonPoints() - arg0.getSeasonPoints();
		}

				
	}

}
