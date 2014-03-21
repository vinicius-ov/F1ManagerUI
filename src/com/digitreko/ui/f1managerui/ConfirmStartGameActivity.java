package com.digitreko.ui.f1managerui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.digitreko.games.model.F1GameManager;
import com.example.f1managerui.R;

public class ConfirmStartGameActivity extends Activity {

	String selectedTeam;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_confirm_start_game);
		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		String managerName = settings.getString("managerName", "ImError");
		int managerAge = settings.getInt("managerAge", 99);
		String managerCountry = settings.getString("managerCountry", "ImError");
		selectedTeam = settings.getString("selectedTeam", "ImError");
		String selectedMode = settings.getString("gameMode", "ImError");		
		TextView tvGameMode = (TextView) findViewById(R.id.textViewGameMode);
		tvGameMode.setText(selectedMode);
		TextView tvManName = (TextView) findViewById(R.id.textViewManagerNamee);
		tvManName.setText(managerName);
		TextView tvManAge = (TextView) findViewById(R.id.textViewManagerAge);
		tvManAge.setText(String.valueOf(managerAge));
		TextView tvManCou = (TextView) findViewById(R.id.textViewManagerCountry);
		tvManCou.setText(managerCountry);

		TextView tvSelTeam = (TextView) findViewById(R.id.textViewTeamName);
		String isCustom = settings.getString("customTeamName", "empty");
		if (!isCustom.equals("empty")){
			TextView customTeamCountry = (TextView) findViewById(R.id.textViewSelectedTeamCountry);
			TextView customTeamCountryLabel = (TextView) findViewById(R.id.textView5);
			customTeamCountryLabel.setVisibility(1);
			customTeamCountry.setVisibility(1);
			customTeamCountry.setText(settings.getString("customTeamCountry", "NOgood"));
			
			tvSelTeam.setText(settings.getString("customTeamName", "CustomNameError"));
		}
		else
		{
			if (selectedMode.equals("Manager")){
				tvSelTeam.setText("Random");
			} else{				
				selectedTeam = selectedTeam.replace('_', ' ');
				tvSelTeam.setText(selectedTeam);
			}
		}


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.confirm_start_game, menu);
		return true;
	}

	public void gotoStartGame(View view){
		final F1GameManager godClass;
    	godClass = F1GameManager.getInstance();
    	//call all initialization methods
    	
    	//initialize all structures
    	godClass.initializeAllTracks();    	
    	try {
			godClass.initializeTeams(selectedTeam);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Intent intent = new Intent(this,BaseTabbedNavigationActivity.class);
		startActivity(intent);
	}

}
