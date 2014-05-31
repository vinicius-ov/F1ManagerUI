package com.digitreko.games.model;

public class Salary {

	
		private final static int baseModifier = 2109 ;	//c
		private final static double factor = 0.097;	//k
		private final static int skill = 80;
		
		public static long minimumSalary(int driverSkill){
			
			double kx = factor * driverSkill;
			kx = Math.exp(kx);
			System.out.println(String.valueOf(kx));
			double minimumSalary = kx * baseModifier;			
			return Math.round(minimumSalary);
		}
		
		public static double moralFactor(int driverMoral){
			double moralFactor = ((double)driverMoral / 1000 ) + 1;
			//return Math.round(minimumSalary(driverSkill)*moralFactor);
			return moralFactor;
		}
		
		/*
		public static void main(String[] args) {
			double minimum = minimumSalary(skill);
			int minimumm = (int) minimum;
			double maximum = Math.round(minimumSalary(skill)*moralFactor(skill));
			int maximumm = (int) maximum;
			System.out.println("Minimum = " + String.valueOf(minimumSalary(skill)));
			System.out.println("Maximum = " + String.valueOf(Math.round(minimumSalary(skill)*moralFactor(skill))));
			System.out.println("Min"+String.valueOf(minimumm)+" MAx"+String.valueOf(maximumm));
			int salario = minimumm + (int)(Math.random() * ((maximumm - minimumm) + 1));
			System.out.println("Salario final= "+salario);
			
			
			// * calcula o minimo
			// * aplica o fator de moral no minimo para gerar o maximo
			// * sorteia o salario entre minimo e maximo com o random acima
			 
		}
			*/	
	} 
