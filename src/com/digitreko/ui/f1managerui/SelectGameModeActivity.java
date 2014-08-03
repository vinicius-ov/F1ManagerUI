package com.digitreko.ui.f1managerui;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.View;

import com.digitreko.f1manager.R;

public class SelectGameModeActivity extends Activity {
	String gameMode = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_game_mode);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.select_game_mode, menu);
		return true;
	}
	public void gotoSelectTeamScreen(View view){		
			Intent intent = null;
			SharedPreferences data = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
			SharedPreferences.Editor editor = data.edit();
			
			if (gameMode == "Single Team"){
				intent = new Intent(this,SelectTeamActivity.class);				
			}
			else if (gameMode == "Manager"){
				intent = new Intent(this,ConfirmStartGameActivity.class);				
			}
			editor.putString("gameMode", gameMode);
			editor.commit();
			startActivity(intent);		
	}
	
	public void setManagerMode(View view){
		gameMode = "Manager";
		SharedPreferences data = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		SharedPreferences.Editor editor = data.edit();		
		editor.putString("customTeamName", "empty");
		editor.commit();
		
		gotoSelectTeamScreen(view);
	}
	
	public void setSingleTeamMode(View view){
		gameMode = "Single Team";
		gotoSelectTeamScreen(view);
	}
	
}

