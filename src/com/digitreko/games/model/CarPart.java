package com.digitreko.games.model;

import java.io.Serializable;


public abstract class CarPart implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7476552229164043210L;
	
	private static final double MAXCONDITION = 100;
	private static final int MAXLEVEL = 10;
	private static final int INITIAL_COST = 100000;
	private static final double PRICE_MODIFIER = 1.3;
	private String brand;
	private double condition;
	private int power;

	public CarPart(int power){
		brand = "new";
		condition = 80;	//655000
		condition = 50;	//655000
		this.power = power;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public double getCondition() {
		return condition;
	}

	public void setCondition(int condition) {
		this.condition = condition;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}
	
	
	public static double getMaxcondition() {
		return MAXCONDITION;
	}

	public static int getInitialCost() {
		return INITIAL_COST;
	}

	public static double getPriceModifier() {
		return PRICE_MODIFIER;
	}

	public void setCondition(double condition) {
		this.condition = condition;
	}

	public int costToRepair(){		
		return (int) Math.round( ( ((MAXCONDITION - condition)*PRICE_MODIFIER) * (power*2500)));
	}
	
	public int costToUpgrade(){
		if (power < 2){		
			return INITIAL_COST * power;
		}else{
			return (int)((double)INITIAL_COST * (double)power * PRICE_MODIFIER);
		}
	}
	
	public boolean wearPart(int skill, int aggro, int diff){
		//if condition reaches 0 car is broken car is retired
		double cond = ((aggro*diff*10)*1.0 / skill*1.0);
		//System.out.println(cond);
		condition -= cond;
		//System.out.println(condition);
		if (condition <= 0) 
			return false;
		else
			return true;
	}

	public boolean isMaxLevel(){
		if (power < MAXLEVEL)
			return false;
		else
			return true;
	}
	public boolean isMaxCondition(){
		if (condition < MAXCONDITION)
			return false;
		else
			return true;
	}

	@Override
	public String toString() {
		return "CarPart [brand=" + brand + ", condition=" + condition
				+ ", power=" + power + "]";
	}
	
	
	
}
