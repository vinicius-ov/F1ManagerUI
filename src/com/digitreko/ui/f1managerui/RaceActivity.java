package com.digitreko.ui.f1managerui;


import java.io.IOException;
import java.io.InputStream;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.digitreko.f1manager.R;
import com.digitreko.games.model.AppLifecycleManager;
import com.digitreko.games.model.F1GameManager;
import com.digitreko.games.model.Pilot;
import com.digitreko.games.model.RaceCar;
import com.digitreko.games.model.Track;

public class RaceActivity extends Activity {

	private final int PHOTO_SIZE = 72;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_race);
		fillFields();		
	}

	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_start_race:
                callStartRaceAlert();
                return true;
            case R.id.action_decide_race:
                callSimulateRaceAlert();                
                return true;
            case R.id.action_retire_cars:
            	callRetireRaceAlert();
            	return true;
            case R.id.action_settings_race:
            	callRetireRaceAlert();
            	return true;
            case R.id.action_help_race:
            	callRetireRaceAlert();
            	return true;
            default:
                return super.onOptionsItemSelected(item);
        }
	}

	private void callRetireRaceAlert() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);    	
    	// Add the buttons
    	builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	        	  
    	           }
    	       });
    	builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	               // User cancelled the dialog
    	           }
    	       });

    	// Create the AlertDialog
    	AlertDialog dialog = builder.create();
    	dialog.setTitle("Abandon Race?");
    	dialog.show();
		
	}

	private void callSimulateRaceAlert() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);    	
    	// Add the buttons
    	builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	        	  
    	           }
    	       });
    	builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	               // User cancelled the dialog
    	           }
    	       });

    	// Create the AlertDialog
    	AlertDialog dialog = builder.create();
    	dialog.setTitle("Decide Race now?");
    	dialog.show();
		
	}

	private void callStartRaceAlert() {
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);    	
    	// Add the buttons
    	builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	        	  
    	           }
    	       });
    	builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	               // User cancelled the dialog
    	           }
    	       });

    	// Create the AlertDialog
    	AlertDialog dialog = builder.create();
    	dialog.setTitle("Start Race?");
    	dialog.show();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.race, menu);
		return true;
	}
	
	private void fillFields() {
    	Track tr =  F1GameManager.getInstance().getTrack(F1GameManager.getInstance().getSessionManager().getCurrentRace());    	
    	InputStream is = null;
    	try {
			is = getAssets().open(tr.getFileName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ImageView map = (ImageView) findViewById(R.id.race_racemap);
		map.setImageBitmap(BitmapFactory.decodeStream(is));
				
		Pilot pilot1 = F1GameManager.getInstance().getPCTeam().getCars().get(0).getDriver();
		Pilot pilot2 = F1GameManager.getInstance().getPCTeam().getCars().get(1).getDriver();
		
		TextView namePilot1 = (TextView) findViewById(R.id.racePilotName1);				
		namePilot1.setText(pilot1.getName());
		ImageView picPilot1 = (ImageView) findViewById(R.id.racePilotPic1);		    	
    	try {
			is = getAssets().open(pilot1.getFileName().toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
    	Bitmap b = BitmapFactory.decodeStream(is);
    	b = Bitmap.createScaledBitmap(b, PHOTO_SIZE, PHOTO_SIZE, true);
    	picPilot1.setImageBitmap(b);
		
		TextView namePilot2 = (TextView) findViewById(R.id.racePilotName2);
		namePilot2.setText(pilot2.getName());
		ImageView picPilot2 = (ImageView) findViewById(R.id.racePilotPic2);		
    	try {
			is = getAssets().open(pilot2.getFileName().toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
    	b = BitmapFactory.decodeStream(is);
    	b = Bitmap.createScaledBitmap(b, PHOTO_SIZE, PHOTO_SIZE, true);
    	picPilot2.setImageBitmap(b);
		
    	RaceCar car1 = F1GameManager.getInstance().getPCTeam().getCars().get(0);
		RaceCar car2 = F1GameManager.getInstance().getPCTeam().getCars().get(1);
    	
    	ProgressBar brakesPilot1 = (ProgressBar) findViewById(R.id.barBrakesPilot1);    	
    	//brakesPilot1.setMax((int)car1.getBrakes().getCondition());    	
    	brakesPilot1.setProgress((int)car1.getBrakes().getCondition());
    	ProgressBar brakesPilot2 = (ProgressBar) findViewById(R.id.barBrakesPilot2);
    	//brakesPilot2.setMax((int)car2.getBrakes().getCondition());
    	brakesPilot1.setProgress((int)car2.getBrakes().getCondition());
    	
    	ProgressBar tiresPilot1 = (ProgressBar) findViewById(R.id.barTiresPilot1);
    	//tiresPilot1.setMax((int)car1.getTires().getCondition());
    	tiresPilot1.setProgress((int)car1.getTires().getCondition());
    	ProgressBar tiresPilot2 = (ProgressBar) findViewById(R.id.barTiresPilot2);
    	//tiresPilot2.setMax((int)car2.getTires().getCondition());
    	tiresPilot2.setProgress((int)car2.getTires().getCondition());
    	
    	ProgressBar aeroPilot1 = (ProgressBar) findViewById(R.id.barAeroPilot1);
    	//aeroPilot1.setMax((int)car1.getAerodynamics().getCondition());
    	aeroPilot1.setProgress((int)car1.getAerodynamics().getCondition());
    	ProgressBar aeroPilot2 = (ProgressBar) findViewById(R.id.barAeroPilot2);
    	//aeroPilot2.setMax((int)car2.getAerodynamics().getCondition());
    	aeroPilot2.setProgress((int)car2.getAerodynamics().getCondition());
    	
    	ProgressBar fuelPilot1 = (ProgressBar) findViewById(R.id.barFuelPilot1);
    	//fuelPilot1.setMax((int)car1.getGas());
    	fuelPilot1.setProgress((int)car1.getGas());
    	ProgressBar fuelPilot2 = (ProgressBar) findViewById(R.id.barFuelPilot2);
    	//fuelPilot2.setMax((int)car2.getGas());
    	fuelPilot2.setProgress((int)car2.getGas());    	
    	
    	ProgressBar enginePilot1 = (ProgressBar) findViewById(R.id.barEnginePilot1);
    	//enginePilot1.setMax((int)car1.getEngine().getCondition());
    	enginePilot1.setProgress((int)car1.getEngine().getCondition());
    	ProgressBar enginePilot2 = (ProgressBar) findViewById(R.id.barEnginePilot2);
    	//enginePilot2.setMax((int)car2.getEngine().getCondition());
    	enginePilot2.setProgress((int)car2.getEngine().getCondition());
    	
    	ProgressBar suspensionPilot1 = (ProgressBar) findViewById(R.id.barSuspensionPilot1);
    	//suspensionPilot1.setMax((int)car1.getSuspension().getCondition());
    	suspensionPilot1.setProgress((int)car1.getSuspension().getCondition());
    	ProgressBar suspensionPilot2 = (ProgressBar) findViewById(R.id.barSuspensionPilot2);
    	//suspensionPilot2.setMax((int)car2.getSuspension().getCondition());
    	suspensionPilot2.setProgress((int)car2.getSuspension().getCondition());
		
		
	}

	
	/*
	 * private void saveGamePopupConfirmation() {
    	AlertDialog.Builder builder = new AlertDialog.Builder(getWindow().getContext());    	
    	// Add the buttons
    	builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	               AppLifecycleManager.saveGameWithName(getApplicationContext(),F1GameManager.getInstance().getPlayer().getName());
    	               String saveName= F1GameManager.getInstance().getPlayer().getName();
    	               System.out.println(saveName);
    	               Toast.makeText(getWindow().getContext(), "Game saved as "+saveName+"!", Toast.LENGTH_SHORT).show();
    	           }
    	       });
    	builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	               Toast.makeText(getWindow().getContext(), "Save cancelled!", Toast.LENGTH_SHORT).show();
    	               AppLifecycleManager.loadGameByName(getWindow().getContext(), F1GameManager.getInstance().getPlayer().getName());
    	           }
    	       });

    	// Create the AlertDialog
    	AlertDialog dialog = builder.create();
    	//Add name of player to save prompt
    	dialog.setTitle("Game will be saved as '"+F1GameManager.getInstance().getPlayer().getName()+"'. Confirm?");
    	dialog.show();
	}
	 * 
	 */
	
}
