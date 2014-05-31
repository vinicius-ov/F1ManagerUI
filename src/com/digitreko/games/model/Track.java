package com.digitreko.games.model;

import java.util.ArrayList;

public class Track {
	
	
	private String name;
	private String cityAndCountry;
	private double lenght;
	private ArrayList<Section> sections;
	private String fileName;
	
	public Track(){
		
	}
	public Track(String nome,String fileName, 
			int[] sec1,int[] sec2,
			int[] sec3,int[] sec4,			
			int[] sec5,int[] sec6,
			int[] sec7,int[] sec8,
			int[] sec9,int[] sec10){
	this.name = nome;
	this.cityAndCountry = nome;
	this.fileName = fileName;
	lenght = 0;
	sections =  new ArrayList<Section>();
	sections.add(new Section(sec1));
	sections.add(new Section(sec2));
	sections.add(new Section(sec3));
	sections.add(new Section(sec4));
	sections.add(new Section(sec5));
	sections.add(new Section(sec6));
	sections.add(new Section(sec7));
	sections.add(new Section(sec8));
	sections.add(new Section(sec9));
	sections.add(new Section(sec10));
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCityAndCountry() {
		return cityAndCountry;
	}
	public void setCityAndCountry(String cityAndCountry) {
		this.cityAndCountry = cityAndCountry;
	}
	public double getLenght() {
		return lenght;
	}
	public void setLenght(double lenght) {
		this.lenght = lenght;
	}
	public ArrayList<Section> getSections() {
		return sections;
	}
	public void setSections(ArrayList<Section> sections) {
		this.sections = sections;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	

}
