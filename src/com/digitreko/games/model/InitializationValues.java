package com.digitreko.games.model;

public enum InitializationValues {

		Barrichello("Rubens Barrichello",31,60,45),
		Ferrari("Ferrari","Ferrari",10,10);
		
		private String name;
		private int age;
		private int skill;
		private int moral;
		private String engine;
		private int engineers;
		private int planners;
		
		
		public String getEngine() {
			return engine;
		}
		public void setEngine(String engine) {
			this.engine = engine;
		}
		public int getEngineers() {
			return engineers;
		}
		public void setEngineers(int engineers) {
			this.engineers = engineers;
		}
		public int getPlanners() {
			return planners;
		}
		public void setPlanners(int planners) {
			this.planners = planners;
		}

		private InitializationValues(String nome,int idade,int habil, int moral){
			this.name = nome;
			this.age = idade;
			this.skill = habil;
			this.moral = moral;
		}
		private InitializationValues(String nome,String motor,int engenheiros,int planners){
			this.name = nome;
			this.engine = motor;
			this.engineers = engenheiros;
			this.planners = planners;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public int getSkill() {
			return skill;
		}

		public void setSkill(int skill) {
			this.skill = skill;
		}

		public int getMoral() {
			return moral;
		}

		public void setMoral(int moral) {
			this.moral = moral;
		}
		
}