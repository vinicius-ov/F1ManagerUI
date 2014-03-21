package com.digitreko.ui.f1managerui;

import com.example.f1managerui.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class FinanceFragment extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_finance);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.finance, menu);
		return true;
	}

}
