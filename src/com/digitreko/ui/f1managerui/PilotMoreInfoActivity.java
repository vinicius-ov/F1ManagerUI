package com.digitreko.ui.f1managerui;

import com.example.f1managerui.R;
import com.example.f1managerui.R.layout;
import com.example.f1managerui.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class PilotMoreInfoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pilot_more_info);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pilot_more_info, menu);
		return true;
	}
	
	

}
