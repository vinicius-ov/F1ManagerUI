package com.digitreko.ui.f1managerui;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.digitreko.f1manager.R;
import com.digitreko.games.model.AppLifecycleManager;

public class LoadGameActivity extends ListActivity{
	
	private String saveName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_load_game);
		ArrayAdapter<String> teamAdapter =new ArrayAdapter<String>(getApplicationContext(), R.layout.listview_loadgame,R.id.stringLoadGameEntry,AppLifecycleManager.readSaveSlots(getApplicationContext()));
		getListView().setAdapter(teamAdapter);		
		getListView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long itemId) {
				
					saveName = (String) getListView().getItemAtPosition(position);//(TextView) findViewById(R.id.stringLoadGameEntry);
					//saveName = (String)tvTemp.getText();
					System.out.println("Save name= "+saveName);
					//Toast.makeText(view.getContext(), ((String)tvTemp.getText()), Toast.LENGTH_LONG).show();// TODO Auto-generated method stub
					if (!saveName.equals("EMPTY") ){
						//if app is not recovering skip load confirmation
						if (AppLifecycleManager.isRecovering(getApplicationContext())){
							popAlertLoadGame();
						}
						else{
							loadGame();
						}
					}
				}
			
		});
	}
	
	public void popAlertLoadGame(){
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);    	
    	// Add the buttons
    	builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {    	        	   
						loadGame();       
    	           }
    	       });
    	builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	               // User cancelled the dialog
    	           }
    	       });

    	// Create the AlertDialog
    	AlertDialog dialog = builder.create();
    	dialog.setTitle("This will erase your progress so far. Confirm load game?");
    	dialog.show();

    }
	private void loadGame(){
		AppLifecycleManager.loadGameByName(getApplicationContext(), saveName);
		AppLifecycleManager.setRecoveringDisabled(getApplicationContext());
		Intent intent = new Intent(getApplicationContext(),BaseTabbedNavigationActivity.class);
		startActivity(intent);
	}

}
