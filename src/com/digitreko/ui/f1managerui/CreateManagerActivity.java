package com.digitreko.ui.f1managerui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

import com.digitreko.games.model.F1GameManager;
import com.digitreko.games.model.Manager;
import com.example.f1managerui.R;

public class CreateManagerActivity extends Activity {
	
	final static String PREF_NAME = "myPref";
	protected EditText managerName;
	protected EditText managerAge;
	protected EditText managerCountry;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_manager);
		managerName = (EditText)findViewById(R.id.managerNameInput);
		managerAge = (EditText)findViewById(R.id.managerAgeInput);
		managerCountry = (EditText)findViewById(R.id.customTeamCountryInput);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_manager, menu);
		return true;
	}

	public void gotoSelectGameMode(View view){
		recordManagerData();
		
		Intent intent = new Intent(this,SelectGameModeActivity.class);
		startActivity(intent);		
	}
	
	private void recordManagerData(){		
		final F1GameManager godClass = F1GameManager.getInstance();    	
    	Manager player = new Manager();
    	player.setName(managerName.getText().toString());
    	player.setAge(Integer.parseInt(managerAge.getText().toString()));
    	player.setNationality(managerCountry.getText().toString());
    	godClass.setPlayer(player);

	}
}

