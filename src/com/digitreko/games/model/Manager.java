package com.digitreko.games.model;

public class Manager {
	private String name;
	private String nationality;
	private int age;
	private int yearsInCommand;
	
	
	public Manager(){
		
	}
	
	public String getName() {
		return name;
	}	
	public void setName(String name) {
		this.name = name;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getYearsInCommand() {
		return yearsInCommand;
	}

	public void setYearsInCommand(int yearsInCommand) {
		this.yearsInCommand = yearsInCommand;
	}
	
	
}
