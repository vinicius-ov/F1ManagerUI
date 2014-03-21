package com.digitreko.games.model;

public class Codes {
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
         
         private teamNames(final String text){
			 this.text = text;
		 }
         private final String text;
         
         @Override
         public String toString(){
        	 return text;
         }
	}
}

