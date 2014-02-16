package com.digitreko.games.logica;

public class Section {
	private double lenght;
	private int difficulty;

	public Section(){
		
	}
	
	public Section(int[] data){
		this.lenght = data[0];
		this.difficulty = data[1];
	}
	
	
	
	public double getLenght() {
		return lenght;
	}
	public void setLenght(double lenght) {
		this.lenght = lenght;
	}
	
	public int getDificulty() {
		return difficulty;
	}
	public void setDificulty(int dificulty) {
		this.difficulty = dificulty;
	}
	
	
}
