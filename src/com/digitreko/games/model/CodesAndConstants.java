package com.digitreko.games.model;

public class CodesAndConstants {
	public static enum pilotCodes {
		Vettel,Webber,Buemi,
		Alonso,Massa,Gene,DelaRosa,
		Button,Perez,Paffett,Turvey,
		Raikkonen,
		Grosjean,DAmbrosio,Prost,
		Rosberg,Hamilton,
		Hulkenberg,Gutierrez,Frijns,
		Resta,Sutil,
		Maldonado,Bottas,
		Vergne,Ricciardo,
		Pic,Garde,Rossi,		
		Bianchi,Chilton,Gonzales	
	};
	
	public static enum teamAndCarCodes {
		RBR1,RBR2,RBR3,
		FER1,FER2,FER3,
		MCL1,MCL2,MCL3,
		CAT1,CAT2,CAT3,
		FOR1,FOR2,FOR3,
		LOT1,LOT2,LOT3,
		MER1,MER2,MER3,
		SAU1,SAU2,SAU3,
		WIL1,WIL2,WIL3,
		TOR1,TOR2,TOR3,
		MAR1,MAR2,MAR3,	
	};
	public static enum  teamNames {
		 Ferrari("Ferrari"), 
         Red_Bull_Racing("Red Bull Racing"), 
         Williams("Williams"), 
         Sauber("Sauber"), 
         McLaren("McLaren"), 
         Force_India("Force India"), 
         Marussia("Marussia"), 
         Catherham("Catherham"), 
         Lotus("Lotus"), 
         Mercedes("Mercedes"), 
         Toro_Rosso("Toro Rosso");
         
         private teamNames(final String name){
			 this.name = name;
		 }
         private final String name;
         
         @Override
         public String toString(){
        	 return name;
         }
	}
	public static enum  pilotNames {
		Vettel("Sebastian Vettel"),Webber("Mark Webber"), 
        Alonso("Fernando Alonso"),Massa("Felipe Massa"),
        Button("Jason Button"),Perez("Sergio Perez"), 
        Raikonnen("Kimi Raikonnen"),Grosjean("Sauber"), 
        Rosberg("Nico Rosberg"),Hamilton("Lewis Hamilton"), 
        Resta("Paul di Resta"),Sutil("Adrian Sutil"), 
        Maldonado("Pastor Maldonado"),Bottas("Valtere Bottas"), 
        Bianchi("Jules Bianchi"),Garde("Guiedo Van Der Garde"),
        Hulkenberg("Nico Hulkenberg"),Gutierrez("Esteban Gutierrez"),
        Pic("Charles Pic"),Vergne("Jean-Eric Vergne"),
		Ricciardo("Daniel Ricciardo"),Chilton("Max Chilton");
        
        private pilotNames(final String name){
			 this.name = name;
		 }
        private final String name;
        
        @Override
        public String toString(){
       	 return name;
        }
	}
	/*
	 * Vettel,Webber,Buemi,
		Alonso,Massa,Gene,DelaRosa,
		Button,Perez,Paffett,Turvey,
		Raikkonen,
		Grosjean,DAmbrosio,Prost,
		Rosberg,Hamilton,
		Hulkenberg,Gutierrez,Frijns,
		Resta,Sutil,
		Maldonado,Bottas,
		Vergne,Ricciardo,
		Pic,Garde,Rossi,		
		Bianchi,Chilton,Gonzales
	 */
	
}

