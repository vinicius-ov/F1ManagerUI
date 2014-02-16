package com.example.f1managerui;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class NextRaceActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_next_race);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.next_race, menu);
		return true;
	}

}
