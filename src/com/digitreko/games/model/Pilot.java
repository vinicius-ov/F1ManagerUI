package com.digitreko.games.model;

import java.util.Comparator;


public class Pilot{
	private String name;
	private int skill;
	private int age;
	private int reputation;
	private int minSalary;	//decididos por formula
	private int maxSalary;
	private int currentSalary;
	private int moral;
	private Integer outcomeAccumulator;
	private int seasonPoints;
	private String fileName;
    
	public Pilot(){
		
	}
	
	public Pilot(Codes.pilotCodes code){
    	outcomeAccumulator = 0;
		age = 20;
    	reputation = 50; 
    	moral = 50;
    	seasonPoints = 0;
    	createSalary();
    	
        switch (code){
            case Vettel:{
            	name = "Sebastian Vettel";
            	skill = 80;
            	fileName = "vettel.png";
            	
            }
            break;
            case Webber:{
            	name = "Mark Webber";
            	skill = 77;
            	fileName = "webber.jpg";
            }
                break;
            case Alonso:{
            	name 	= "Fernando Alonso";
            	skill 	= 80; 
            	fileName = "alonso.jpg";
            }
            break;    
            case Massa:{
            	name 	= "Felipe Massa";
            	skill 	= 72; 
            	fileName = "massa.png";
            }
            break;
            case Button:{
            	name 	= "Jason Button";
            	skill 	= 78; 
            	fileName = "button.jpg";
            }
            break;
            case Perez:{
            	name 	= "Sergio Perez";
            	skill 	= 60; 
            	fileName = "perez.jpg";
            }
            break;           
            case Raikkonen:{
            	name 	= "Kimi Raikkonen";
            	skill 	= 79; 
            	fileName = "raikkonen.jpg";
            }
            break;
            case Grosjean:{
            	name 	= "Romain Grosjean";
            	skill 	= 75; 
            	fileName = "grosjean.png";
            }
            break;
            case Rosberg:{
            	name 	= "Nico Rosberg";
            	skill 	= 74; 
            	fileName = "rosberg.png";
            }
            break;
            case Hamilton:{
            	name 	= "Lewis Hamilton";
            	skill 	= 79; 
            	fileName = "hamilton.jpg";
            }
            break;
            case Hulkenberg:{
            	name 	= "Nico Hulkenberg";
            	skill 	= 65; 
            	fileName = "hulkenberg.jpg";
            }
            break;
            case Gutierrez:{
            	name 	= "Esteban Gutierrez";
            	skill 	= 58; 
            	fileName = "gutierrez.jpg";
            }
            break;
            case Resta:{
            	name 	= "Paul di Resta";
            	skill 	= 65; 
            	fileName = "di_resta.jpg";
            }
            break;
            case Sutil:{
            	name 	= "Adrian Sutil";
            	skill 	= 65; 
            	fileName = "sutil.png";
            }
            break;
            case Vergne:{
            	name 	= "Jean-Eric Vergne";
            	skill 	= 59; 
            	fileName = "vergne.jpg";
            }
            break;
            case Ricciardo:{
            	name 	= "Daniel Ricciardo";
            	skill 	= 59; 
            	fileName = "ricciardo.jpg";
            }
            break;
            case Pic:{
            	name 	= "Charles Pic";
            	skill 	= 60; 
            	fileName = "pic.jpg";
            }
            break;
            case Garde:{
            	name 	= "Giedo van der Garde";
            	skill 	= 65; 
            	fileName = "giedo.jpg";
            }
            break;
            case Maldonado:{
            	name 	= "Pastor Maldonado";
            	skill 	= 65; 
            	fileName = "maldonado.jpg";
            }
            break;
            case Bottas:{
            	name 	= "Valttere Bottas";
            	skill 	= 60; 
            	fileName = "bottas.png";
            }
            break;
            case Bianchi:{
            	name 	= "Jules Bianchi";
            	skill 	= 65; 
            	fileName = "bianchi.jpg";
            }
            break;
            case Chilton:{
            	name 	= "Max Chilton";
            	skill 	= 65; 
            	fileName = "chilton.png";
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
	
	
	public int getCurrentSalary() {
		return currentSalary;
	}

	public void setCurrentSalary(int currentSalary) {
		this.currentSalary = currentSalary;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
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

	@Override
	public String toString() {
		return "Pilot [name=" + name + ", skill=" + skill + ", age=" + age
				+ ", reputation=" + reputation + ", minSalary=" + minSalary
				+ ", maxSalary=" + maxSalary + ", currentSalary="
				+ currentSalary + ", moral=" + moral + ", outcomeAccumulator="
				+ outcomeAccumulator + ", seasonPoints=" + seasonPoints
				+ ", fileName=" + fileName + "]";
	}
	
	public void createSalary(){
		minSalary = (int)Salary.minimumSalary(skill);
		maxSalary = (int) Math.round(minSalary * Salary.moralFactor(moral));
		currentSalary = minSalary + (int)(Math.random() * ((maxSalary - minSalary) + 1));
	}
	

}
