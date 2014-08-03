package com.digitreko.ui.f1managerui;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.digitreko.f1manager.R;
import com.digitreko.games.model.AppLifecycleManager;

public class F1Manager extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		// Make sure we're running on Honeycomb or higher to use ActionBar APIs
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // Show the Up button in the action bar.
            //getActionBar().setDisplayHomeAsUpEnabled(true);
        }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void gotoCreateManagerScreen(View view){
		Intent intent = new Intent(this,CreateManagerActivity.class);
		startActivity(intent);
	}
	public void gotoLoadGameScreen(View view){
		AppLifecycleManager.setRecoveringDisabled(getApplicationContext());
		Intent intent = new Intent(this,LoadGameActivity.class);
		startActivity(intent);
	}
	public void exitApp(View view){
		System.out.println("App terminated by user via main menu!");
		//makes game restart
		AppLifecycleManager.setRecoveringDisabled(getApplicationContext());
		super.finish();
	}
	
	@Override
	public void onPause()
	{
		System.out.println("PAUSE main");
		super.onPause();
		
	}
	@Override
	public void onStop()
	{
		System.out.println("STOP main");
		super.onStop();		
	}
	@Override
    public void onResume(){
    	System.out.print("RESUME");
		System.out.println("main");
    	super.onResume();
    }
    
    @Override
    public void onRestart(){
    	System.out.print("RESTART");
    	System.out.println("main");
    	super.onRestart();    	
    }
    
}