package com.digitreko.games.logica;

public abstract class CarPart {

	private static final int MAXCONDITION = 100;
	private String brand;
	private double condition;
	private int power;

	public CarPart(int power){
		brand = "new";
		condition = 100.0;
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
	
	public int costToRepair(){
		return (((int)condition - MAXCONDITION)*(power*10000));
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

	@Override
	public String toString() {
		return "CarPart [brand=" + brand + ", condition=" + condition
				+ ", power=" + power + "]";
	}
	
}
