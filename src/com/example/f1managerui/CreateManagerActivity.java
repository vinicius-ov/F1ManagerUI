package com.example.f1managerui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CreateManagerActivity extends Activity {
	
	final static String PREF_NAME = "myPref";
	protected EditText managerName;
	protected EditText managerAge;
	protected EditText managerCountry;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_manager);
		managerName = (EditText)findViewById(R.id.customTeamNameInput);
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
		SharedPreferences data = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		SharedPreferences.Editor editor = data.edit();		
		if (managerName.getText().toString().length() <= 0)
			editor.putString("managerName", "DEBUG");
		else
			editor.putString("managerName", managerName.getText().toString());		
		if (Integer.parseInt(managerAge.getText().toString()) <= 0)
			editor.putInt("managerAge", 99);
		else
			editor.putInt("managerAge", Integer.parseInt(managerAge.getText().toString()));
		if (managerCountry.getText().toString().length() <= 0)
			editor.putString("managerCountry", "DEBUG");
		else 
			editor.putString("managerCountry", managerCountry.getText().toString());
		editor.commit();
	}
}

