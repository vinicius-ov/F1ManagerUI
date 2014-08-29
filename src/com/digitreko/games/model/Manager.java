package com.digitreko.games.model;

import java.io.Serializable;

public class Manager implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -722048577435286148L;
	private String name;
	private String nationality;
	private int age;
	private int yearsInCommand;
	
	
	public Manager(){
		yearsInCommand = 0;
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

	@Override
	public String toString() {
		return "Manager [name=" + name + ", nationality=" + nationality
				+ ", age=" + age + ", yearsInCommand=" + yearsInCommand + "]";
	}
	
	
	
}
