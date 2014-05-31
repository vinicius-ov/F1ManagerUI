package com.digitreko.ui.f1managerui;


import java.io.IOException;
import java.io.InputStream;

import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.digitreko.games.model.F1GameManager;
import com.digitreko.games.model.RaceCar;
import com.digitreko.games.model.Team;
import com.example.f1managerui.R;
 
public class UpgradeCarFragment extends Fragment {
 
	private final String LEVEL = "LVL ";
	private F1GameManager godClass;
	private Team pcTeam;
	
	TextView brakesLevel;
	TextView suspensionLevel;
	TextView aerodynamicsLevel;
	TextView tiresLevel;
	TextView engineLevel;
	
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
    	godClass = F1GameManager.getInstance();
    	pcTeam = godClass.getPCTeam();
 
        View rootView = inflater.inflate(R.layout.fragment_race_car, container, false);
        Button marketBtn = (Button) rootView.findViewById(R.id.upgradeTires);
        marketBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            	popAlertUpgradeTires(v);            	
            }
        });
    
        
        fillFields(rootView);
        return rootView;
    }
    
    private void fillFields(View rootView){
    	
    	int funds = pcTeam.getFunds();
    	
    	
    	InputStream is = null;
    	try {
			is = getActivity().getAssets().open("car_top_view2.jpg");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
    	
    	//os dois carros do time sao iguis entao pode pegar qualquer 1
    	//na hora do update fazer um foreach na lista de carros
    	//o dummy
    	RaceCar dummy = pcTeam.getCars().get(0);
    	brakesLevel =  (TextView) rootView.findViewById(R.id.brakesLevel);
    	brakesLevel.setText(LEVEL+dummy.getBreaks().getPower());

    	engineLevel =  (TextView) rootView.findViewById(R.id.engineLevel);
    	engineLevel.setText(LEVEL+dummy.getEngine().getPower()); 
    	
    	aerodynamicsLevel =  (TextView) rootView.findViewById(R.id.aerodynamicsLevel);
    	aerodynamicsLevel.setText(LEVEL+dummy.getAerodynamics().getPower());
    	
    	tiresLevel =  (TextView) rootView.findViewById(R.id.tiresLevel);
    	tiresLevel.setText(LEVEL+dummy.getTires().getPower());
    	
    	suspensionLevel =  (TextView) rootView.findViewById(R.id.suspensionLevel);
    	suspensionLevel.setText(LEVEL+dummy.getSuspension().getPower());
    	
    	TextView fundsText =  (TextView) rootView.findViewById(R.id.playerCashToUpgrade);
    	fundsText.setText("BALANCE: "+String.valueOf(funds));
    	
    	ImageView iv = (ImageView) rootView.findViewById(R.id.raceCarUpgrade);
    	iv.setImageBitmap(BitmapFactory.decodeStream(is));
    	//iv.setScaleType(ScaleType.FIT_END);
    	
    }
    
    
    public void popAlertUpgradeTires(View view){
    	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    	// Add the buttons
    	builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	               // if cost > balance send create another alert with ok button saying that not enough funds to upgrade
    	        	   // if projected balance < cost send alert of bankrupcy at end of season. proceed? yes no
    	        	   //for (RaceCar car: pcTeam.getCars()){
    	        		//   car.getBreaks().setPower(car.getBreaks().getPower()+1);
    	        	   //}
    	        	   pcTeam.setFunds(pcTeam.getFunds()-100000);
    	        	   for (RaceCar car:pcTeam.getCars()){
    	        		   car.getTires().setPower(car.getTires().getPower()+1);
    	        	   }
    	        	   
    	           }
    	       });
    	builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	               // User cancelled the dialog
    	           }
    	       });

    	// Create the AlertDialog
    	AlertDialog dialog = builder.create();
    	dialog.setTitle("Do you wish to upgrade Tires?\n" + "Cost: 100000 Mangos");
    	dialog.show();
    }
    public void popAlertUpgradeBrakes(){
    	

    }
    public void popAlertUpgradeAerodynamics(){

    }
    public void popAlertUpgradeTires(){

    }
    public void popAlertUpgradeEngine(){

    }
 
}
