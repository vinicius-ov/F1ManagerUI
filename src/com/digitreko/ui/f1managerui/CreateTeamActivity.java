package com.digitreko.ui.f1managerui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

import com.digitreko.f1manager.R;

public class CreateTeamActivity extends Activity {
	
	protected EditText nameField;
	protected EditText countryField;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_team);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.create_team, menu);
		return true;
	}
	
	public void gotoConfirmStartGame(View view){
		nameField = (EditText) findViewById(R.id.customTeamNameInput);
		countryField = (EditText) findViewById(R.id.customTeamCountryInput);
		
		SharedPreferences data = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		SharedPreferences.Editor editor = data.edit();		
		editor.putString("customTeamName", nameField.getText().toString());
		editor.putString("customTeamCountry", countryField.getText().toString());
		
		editor.commit();
		
		Intent intent = new Intent(this,ConfirmStartGameActivity.class);
		startActivity(intent);
	}

	
}

