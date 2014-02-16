package com.example.f1managerui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.View;
import android.widget.Spinner;

import com.digitreko.games.logica.CustomOnItemSelectedListener;

public class SelectTeamActivity extends Activity {

	private Spinner spinner; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_team);
		spinner = (Spinner) findViewById(R.id.spinner_times);
		spinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_or_pick_team, menu);
		return true;
	}

	public void gotoCreateTeamScreen(View view){		
		Intent intent = new Intent(this,CreateTeamActivity.class);
		startActivity(intent);		
	}

	public void gotoConfirmStartGameScreen(View view){	
		String time = spinner.getSelectedItem().toString();
		SharedPreferences data = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		SharedPreferences.Editor editor = data.edit();
		editor.putString("selectedTeam", time);
		editor.putString("customTeamName", "empty");
		editor.commit();

		Intent intent = new Intent(this,ConfirmStartGameActivity.class);
		startActivity(intent);		
	}


}
