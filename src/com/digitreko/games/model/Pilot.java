package com.digitreko.games.model;

import java.io.Serializable;
import java.util.Comparator;


public class Pilot implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4469746419058530889L;
	
	private final int INITIAL_VALUE = 1000;
	private static final int MAXLEVEL = 100;	
	private static final double PRICE_MODIFIER = 1.8;
	
	private String name;
	private int skill;
	private int age;
	private int reputation;
	private int minSalary;	//decididos por formula
	private int maxSalary;
	private int currentSalary;
	private int moral;
	private int outcomeAccumulator;
	private int seasonPoints;
	private String fileName;
    
	
	public Pilot(){
		
	}
	
	public Pilot(CodesAndConstants.pilotCodes code){
    	outcomeAccumulator = 0;
		age = 20;
    	reputation = 50; 
    	moral = 50;
    	seasonPoints = 0;
    	createSalary();
    	
        switch (code){
            case Vettel:{
            	name = CodesAndConstants.pilotNames.Vettel.toString();
            	skill = 80;
            	fileName = "vettel.png";

            }
            break;
            case Webber:{
            	name = CodesAndConstants.pilotNames.Webber.toString();
            	skill = 77;
            	fileName = "webber.jpg";
            }
                break;
            case Alonso:{
            	name 	= CodesAndConstants.pilotNames.Alonso.toString();
            	skill 	= 80; 
            	fileName = "alonso.jpg";
            }
            break;    
            case Massa:{
            	name 	= CodesAndConstants.pilotNames.Massa.toString();
            	skill 	= 72; 
            	fileName = "massa.png";
            }
            break;
            case Button:{
            	name 	= CodesAndConstants.pilotNames.Button.toString();
            	skill 	= 78; 
            	fileName = "button.jpg";
            }
            break;
            case Perez:{
            	name 	= CodesAndConstants.pilotNames.Perez.toString();
            	skill 	= 60; 
            	fileName = "perez.jpg";
            }
            break;           
            case Raikkonen:{
            	name 	= CodesAndConstants.pilotNames.Raikonnen.toString();
            	skill 	= 79; 
            	fileName = "raikkonen.jpg";
            }
            break;
            case Grosjean:{
            	name 	= CodesAndConstants.pilotNames.Grosjean.toString();
            	skill 	= 75; 
            	fileName = "grosjean.png";
            }
            break;
            case Rosberg:{
            	name 	= CodesAndConstants.pilotNames.Rosberg.toString();
            	skill 	= 74; 
            	fileName = "rosberg.png";
            }
            break;
            case Hamilton:{
            	name 	= CodesAndConstants.pilotNames.Hamilton.toString();
            	skill 	= 79; 
            	fileName = "hamilton.jpg";
            }
            break;
            case Hulkenberg:{
            	name 	= CodesAndConstants.pilotNames.Hulkenberg.toString();
            	skill 	= 65; 
            	fileName = "hulkenberg.jpg";
            }
            break;
            case Gutierrez:{
            	name 	= CodesAndConstants.pilotNames.Gutierrez.toString();
            	skill 	= 58; 
            	fileName = "gutierrez.jpg";
            }
            break;
            case Resta:{
            	name 	= CodesAndConstants.pilotNames.Resta.toString();
            	skill 	= 65; 

            	fileName = "di_resta.jpg";

            }
            break;
            case Sutil:{
            	name 	= CodesAndConstants.pilotNames.Sutil.toString();
            	skill 	= 65; 
            	fileName = "sutil.png";
            }
            break;
            case Vergne:{
            	name 	= CodesAndConstants.pilotNames.Vergne.toString();
            	skill 	= 59; 
            	fileName = "vergne.jpg";
            }
            break;
            case Ricciardo:{
            	name 	= CodesAndConstants.pilotNames.Ricciardo.toString();
            	skill 	= 59; 
            	fileName = "ricciardo.jpg";
            }
            break;
            case Pic:{
            	name 	= CodesAndConstants.pilotNames.Pic.toString();
            	skill 	= 60; 
            	fileName = "pic.jpg";
            }
            break;
            case Garde:{
            	name 	= CodesAndConstants.pilotNames.Garde.toString();
            	skill 	= 65; 
            	fileName = "giedo.jpg";
            }
            break;
            case Maldonado:{
            	name 	= CodesAndConstants.pilotNames.Maldonado.toString();
            	skill 	= 65; 
            	fileName = "maldonado.jpg";
            }
            break;
            case Bottas:{
            	name 	= CodesAndConstants.pilotNames.Bottas.toString();
            	skill 	= 60; 
            	fileName = "bottas.png";
            }
            break;
            case Bianchi:{
            	name 	= CodesAndConstants.pilotNames.Bianchi.toString();
            	skill 	= 65; 
            	fileName = "bianchi.jpg";
            }
            break;
            case Chilton:{
            	name 	= CodesAndConstants.pilotNames.Chilton.toString();
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
		return "Pilot [INITIAL_VALUE=" + INITIAL_VALUE + ", name=" + name
				+ ", skill=" + skill + ", age=" + age + ", reputation="
				+ reputation + ", minSalary=" + minSalary + ", maxSalary="
				+ maxSalary + ", currentSalary=" + currentSalary + ", moral="
				+ moral + ", outcomeAccumulator=" + outcomeAccumulator
				+ ", seasonPoints=" + seasonPoints + ", fileName=" + fileName
				+ "]";
	}

	public void createSalary(){
		minSalary = (int)Salary.minimumSalary(skill);
		maxSalary = (int) Math.round(minSalary * Salary.moralFactor(moral));
		currentSalary = minSalary + (int)(Math.random() * ((maxSalary - minSalary) + 1));
	}
	
	public int costToUpgrade(){
		if (skill <= 50){		
			return INITIAL_VALUE * skill;
		}else{
			return (int)((double)INITIAL_VALUE * (double)skill * PRICE_MODIFIER);
		}
	}

	public boolean isMaxLevel() {
		if (skill < MAXLEVEL)
			return false;
		else
			return true;
	}
	

}
