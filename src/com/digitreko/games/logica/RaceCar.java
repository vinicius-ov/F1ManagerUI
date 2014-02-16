package com.digitreko.games.logica;

import java.util.Comparator;
import java.util.Random;

public class RaceCar{

	static final int CATERHAN 		= 3;
	static final int MARUSSIA 		= 3;
	static final int FORCE_INDIA 	= 4;
	static final int MERCEDEZ 		= 6;
	static final int LOTUS 			= 5;
	static final int WILLIANS 		= 6;
	static final int MCLAREN		= 8;
	static final int FERRARI 		= 8;
	static final int RBR 			= 8;
	static final int TORO_ROSO 		= 7;
	static final int SAUBER			= 4;

	private CarPart rearWing;
	private CarPart frontWing;
	private CarPart engine;
	private CarPart suspension;
	//private int gearbox;
	private CarPart breaks;
	//int chassi;
	private int currentSection;
	private boolean sectionStatus;
	//private Codes.teamAndCarCodes teamCode;
	private Pilot driver;
	private boolean retired;
	private int gas;

	public RaceCar(Codes.teamAndCarCodes code, Pilot driver){
		int value = 1;
		//teamCode = code;
		switch (code){
		case RBR1:
			value = RBR;
			break;
		case FER1:
			value =  FERRARI;
			break;
		case MAR1:
			value =  MARUSSIA;
			break;
		case CAT1:
			value =  CATERHAN;
			break;
		case WIL1:
			value =  WILLIANS;
			break;
		case TOR1:
			value =  TORO_ROSO;
			break;
		case MCL1:
			value = MCLAREN;
			break;
		case LOT1:
			value = LOTUS;
			break;
		case MER1:
			value = MERCEDEZ;
			break;
		case FOR1:
			value = FORCE_INDIA;
			break;
		default:
			try {
				throw new Exception("Team code not found. Using default value!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				value = FORCE_INDIA;			}

			break;
		};	
		currentSection = 0;
		sectionStatus = false;
		rearWing	= new RearWing(value);
		frontWing	= new FrontWing(value);
		engine		= new Engine(value);
		suspension = new Suspension(value);
		//gearbox	= value;
		breaks		= new Breaks(value);
		//chassi		= value;		
		currentSection = 0;
		sectionStatus = false;
		//pilot = new Pilot();
		this.driver = driver;
		retired = false;
		gas = 150;
	}

	public int getCurrentSection() {
		return currentSection;
	}

	public void setCurrentSection(int currentSection) {
		this.currentSection = currentSection;
	}

	public boolean isSectionStatus() {
		return sectionStatus;
	}

	public void setSectionStatus(boolean sectionStatus) {
		this.sectionStatus = sectionStatus;
	}	

	public int getPower() {		
		return rearWing.getPower()+frontWing.getPower()+engine.getPower()+suspension.getPower()+breaks.getPower()+currentSection;
	}

	public Pilot getDriver() {
		return driver;
	}

	public void setDriver(Pilot driver) {
		this.driver = driver;
	}

	@Override
	public String toString() {
		return "RaceCar [rearWing=" + rearWing + ", frontWing=" + frontWing
				+ ", engine=" + engine + ", suspension=" + suspension
				+ ", breaks=" + breaks + ", currentSection=" + currentSection
				+ ", sectionStatus=" + sectionStatus + ", driver=" + driver
				+ ", retired=" + retired + ", gas=" + gas+ "]";
	}
	
	public String getTeamName(){
		return this.getClass().getSuperclass().getSimpleName();
	}

	public int comparteTo(){
		return breaks.getPower();

	}

	public boolean isRetired() {
		return retired;
	}

	public void setRetired(boolean retired) {
		this.retired = retired;
	}

	public int getGas() {
		return gas;
	}

	public void setGas(int gas) {
		this.gas = gas;
	}

	public CarPart getRearWing() {
		return rearWing;
	}

	public void setRearWing(CarPart rearWing) {
		this.rearWing = rearWing;
	}

	public CarPart getFrontWing() {
		return frontWing;
	}

	public void setFrontWing(CarPart frontWing) {
		this.frontWing = frontWing;
	}

	public CarPart getEngine() {
		return engine;
	}

	public void setEngine(CarPart engine) {
		this.engine = engine;
	}

	public CarPart getSuspension() {
		return suspension;
	}

	public void setSuspension(CarPart suspension) {
		this.suspension = suspension;
	}

	public CarPart getBreaks() {
		return breaks;
	}

	public void setBreaks(CarPart breaks) {
		this.breaks = breaks;
	}
	
	public void setFinalResultsAndPoints(){
		
	}

	public void calculatePerformance(int difficulty, int aggro){		
		if (!retired){		
			//double length = currSect.getLenght();
			int carPower = driver.getSkill();
			int carLessDificulty = carPower - (difficulty*5);
			int pilotMoral = (driver.getMoral()+1)/10;		
			int pilotSkill = driver.getSkill()+1/2;
			int outcome = carLessDificulty + pilotMoral + pilotSkill;
			int low = -27*aggro;
			int high = 27*aggro;
			Random r = new Random();
			int rand = r.nextInt(high - (low - 1)) + low;
			outcome += rand;
			gas -= aggro;
			driver.addToOutcomeAccumulator(outcome);
			//retired = (doWear(driver.getSkill(),aggro, difficulty));					
		}

	}

	private boolean doWear(int skill, int aggro, int difficulty){
		if ((!breaks.wearPart(skill, aggro, difficulty)) ||
				(!suspension.wearPart(skill, aggro, difficulty))||
				(!rearWing.wearPart(skill, aggro, difficulty))||
				(!frontWing.wearPart(skill, aggro, difficulty))||
				(!engine.wearPart(skill, aggro, difficulty)) ||
				(gas <= 0)
				){
			return true;
		}
		else return false;
	}

}