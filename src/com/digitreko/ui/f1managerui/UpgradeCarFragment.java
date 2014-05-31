package com.digitreko.ui.f1managerui;


import java.io.IOException;
import java.io.InputStream;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.digitreko.games.model.F1GameManager;
import com.digitreko.games.model.RaceCar;
import com.digitreko.games.model.Team;
import com.example.f1managerui.R;
 

public class UpgradeCarFragment extends Fragment {
 
	private final String LEVEL = "LVL ";
	private final String BALANCE = "BALANCE: ";
	private final String UPGRADE_MESSAGE = "Confirm Upgrade: ";
	
	private F1GameManager godClass;
	private Team pcTeam;
	
	TextView brakesLevel;
	TextView suspensionLevel;
	TextView aerodynamicsLevel;
	TextView tiresLevel;
	TextView engineLevel;
	TextView fundsText;	
	
	public interface OnRefreshListener {
	    public void onRefresh();
	}
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
    	godClass = F1GameManager.getInstance();
    	pcTeam = godClass.getPCTeam();   	
    	
        View rootView = inflater.inflate(R.layout.fragment_race_car, container, false);
        
        Button upgradeTires = (Button) rootView.findViewById(R.id.upgradeTires);       
        upgradeTires.setOnClickListener(new View.OnClickListener() {       
            @Override
            public void onClick(View v) {            	
            	popAlertUpgradeTires(v);            	
            }
        });
        Button upgradeSuspension = (Button) rootView.findViewById(R.id.upgradeSuspension);       
        upgradeSuspension.setOnClickListener(new View.OnClickListener() {       
            @Override
            public void onClick(View v) {            	
            	popAlertUpgradeSuspension(v);            	
            }
        });
        Button upgradeBrakes = (Button) rootView.findViewById(R.id.upgradeBrakes);       
        upgradeBrakes.setOnClickListener(new View.OnClickListener() {       
            @Override
            public void onClick(View v) {            	
            	popAlertUpgradeBrakes(v);            	
            }
        });
        Button upgradeEngine = (Button) rootView.findViewById(R.id.upgradeEngine);       
        upgradeEngine.setOnClickListener(new View.OnClickListener() {       
            @Override
            public void onClick(View v) {            	
            	popAlertUpgradeEngine(v);            	
            }
        });
        Button upgradeAerodynamics = (Button) rootView.findViewById(R.id.upgradeAerodynamics);       
        upgradeAerodynamics.setOnClickListener(new View.OnClickListener() {       
            @Override
            public void onClick(View v) {            	
            	popAlertUpgradeAerodynamics(v);            	
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
    	brakesLevel.setText(LEVEL+dummy.getBrakes().getPower());

    	engineLevel =  (TextView) rootView.findViewById(R.id.engineLevel);
    	engineLevel.setText(LEVEL+dummy.getEngine().getPower()); 
    	
    	aerodynamicsLevel =  (TextView) rootView.findViewById(R.id.aerodynamicsLevel);
    	aerodynamicsLevel.setText(LEVEL+dummy.getAerodynamics().getPower());
    	
    	tiresLevel =  (TextView) rootView.findViewById(R.id.tiresLevel);
    	tiresLevel.setText(LEVEL+dummy.getTires().getPower());
    	
    	suspensionLevel =  (TextView) rootView.findViewById(R.id.suspensionLevel);
    	suspensionLevel.setText(LEVEL+dummy.getSuspension().getPower());
    	
    	fundsText =  (TextView) rootView.findViewById(R.id.playerCashToUpgrade);
    	fundsText.setText("BALANCE: "+String.valueOf(funds));
    	
    	ImageView iv = (ImageView) rootView.findViewById(R.id.raceCarUpgrade);
    	iv.setImageBitmap(BitmapFactory.decodeStream(is));
    	//iv.setScaleType(ScaleType.FIT_END);
    	
    }
    
    
    public void popAlertUpgradeTires(View view){
    	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    	final int costToUpgrade = pcTeam.getCars().get(0).getTires().costToUpgrade();
    	// Add the buttons
    	builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {    		
    	           public void onClick(DialogInterface dialog, int id) {
    	        	   
    	               if (pcTeam.getFunds() >= costToUpgrade){
	    	        	   pcTeam.setFunds(pcTeam.getFunds()-costToUpgrade);
	    	        	   for (RaceCar car:pcTeam.getCars()){
	    	        		   car.getTires().setPower(car.getTires().getPower()+1);
	    	        	   }    	        	   
	    	        	   tiresLevel.setText(LEVEL+pcTeam.getCars().get(0).getTires().getPower());
	    	           	   fundsText.setText(BALANCE+pcTeam.getFunds());
    	               }else{
    	            	   Toast.makeText(getView().getContext(), "Not enough funds!", Toast.LENGTH_LONG).show();
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
    	dialog.setTitle(UPGRADE_MESSAGE+"'Tires'?\n" + "Cost: "+costToUpgrade);
    	dialog.show();
    }
    public void popAlertUpgradeBrakes(View view){
    	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    	// Add the buttons
    	builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	               if (pcTeam.getFunds() >= 100000){
	    	        	   pcTeam.setFunds(pcTeam.getFunds()-100000);
	    	        	   for (RaceCar car:pcTeam.getCars()){
	    	        		   car.getBrakes().setPower(car.getBrakes().getPower()+1);
	    	        	   }    	        	   
	    	        	   brakesLevel.setText(LEVEL+pcTeam.getCars().get(0).getBrakes().getPower());
	    	           	   fundsText.setText(BALANCE+pcTeam.getFunds());
    	               }else{
    	            	   Toast.makeText(getView().getContext(), "Not enough funds!", Toast.LENGTH_LONG).show();
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
    	dialog.setTitle(UPGRADE_MESSAGE+"'Brakes'?\n" + "Cost: 100000");
    	dialog.show();

    }
    //TODO: Alert in aerodynamics is too big and not showing everything
    public void popAlertUpgradeAerodynamics(View view){
    	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    	// Add the buttons
    	builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	               if (pcTeam.getFunds() >= 100000){
	    	        	   pcTeam.setFunds(pcTeam.getFunds()-100000);
	    	        	   for (RaceCar car:pcTeam.getCars()){
	    	        		   car.getAerodynamics().setPower(car.getAerodynamics().getPower()+1);
	    	        	   }    	        	   
	    	        	   aerodynamicsLevel.setText(LEVEL+pcTeam.getCars().get(0).getAerodynamics().getPower());
	    	           	   fundsText.setText(BALANCE+pcTeam.getFunds());
    	               }else{
    	            	   Toast.makeText(getView().getContext(), "Not enough funds!", Toast.LENGTH_LONG).show();
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
    	dialog.setTitle(UPGRADE_MESSAGE+"'Aerodynamics'? " + "Cost: 100000");
    	dialog.show();

    }
    public void popAlertUpgradeSuspension(View view){
    	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    	// Add the buttons
    	builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	               if (pcTeam.getFunds() >= 100000){
	    	        	   pcTeam.setFunds(pcTeam.getFunds()-100000);
	    	        	   for (RaceCar car:pcTeam.getCars()){
	    	        		   car.getSuspension().setPower(car.getSuspension().getPower()+1);
	    	        	   }    	        	   
	    	        	   suspensionLevel.setText(LEVEL+pcTeam.getCars().get(0).getSuspension().getPower());
	    	           	   fundsText.setText(BALANCE+pcTeam.getFunds());
    	               }else{
    	            	   Toast.makeText(getView().getContext(), "Not enough funds!", Toast.LENGTH_LONG).show();
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
    	dialog.setTitle(UPGRADE_MESSAGE+"'Suspension'? " + "Cost: 100000");
    	dialog.show();
    }
    public void popAlertUpgradeEngine(View view){
    	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    	// Add the buttons
    	builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	               if (pcTeam.getFunds() >= 100000){
	    	        	   pcTeam.setFunds(pcTeam.getFunds()-100000);
	    	        	   for (RaceCar car:pcTeam.getCars()){
	    	        		   car.getEngine().setPower(car.getEngine().getPower()+1);
	    	        	   }    	        	   
	    	        	   engineLevel.setText(LEVEL+pcTeam.getCars().get(0).getEngine().getPower());
	    	           	   fundsText.setText(BALANCE+pcTeam.getFunds());
    	               }else{
    	            	   Toast.makeText(getView().getContext(), "Not enough funds!", Toast.LENGTH_LONG).show();
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
    	dialog.setTitle(UPGRADE_MESSAGE+"'Engine'?\n" + "Cost: 100000");
    	dialog.show();
    }
 
}
