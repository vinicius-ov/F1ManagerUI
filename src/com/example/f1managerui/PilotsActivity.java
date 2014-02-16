package com.example.f1managerui;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class PilotsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pilots);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pilots, menu);
		return true;
	}
	
	/*
	public boolean setManagerMode(){
		setGameMode(GameMode.MANAGER_MODE);
	}
	public boolean setSingleTeamMode(){
		setGameMode(GameMode.SINGLE_TEAM_MODE);
	}
	*/
}
