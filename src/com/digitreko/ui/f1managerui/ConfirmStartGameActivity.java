package com.digitreko.ui.f1managerui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.digitreko.f1manager.R;
import com.digitreko.games.model.AppLifecycleManager;
import com.digitreko.games.model.F1GameManager;
import com.digitreko.games.model.Manager;

public class ConfirmStartGameActivity extends Activity {

	private String selectedTeam;
	private F1GameManager godClass;
	private enum randomTeams {
			MARUSSIA("Marussia"),
			CATHERHAM("Catherham"),
			SAUBER("Sauber"),
			TORO_ROSSO("Toro Rosso");

			private String team;
			
			private randomTeams(String team){
				this.team = team;
			}
			private String getTeam(){
				return team;
			}
			};
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_confirm_start_game);
		
		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
	
		godClass = F1GameManager.getInstance();
    	Manager player = godClass.getPlayer();
    	System.out.println("Getting player... "+player.toString());
    	
		selectedTeam = settings.getString("selectedTeam", "ImError");
		String selectedMode = settings.getString("gameMode", "ImError");		
		TextView tvGameMode = (TextView) findViewById(R.id.textViewGameMode);
		tvGameMode.setText(selectedMode);
		TextView tvManName = (TextView) findViewById(R.id.textViewManagerNamee);
		tvManName.setText(player.getName().toString());
		TextView tvManAge = (TextView) findViewById(R.id.textViewManagerAge);
		tvManAge.setText(String.valueOf(player.getAge()));
		TextView tvManCou = (TextView) findViewById(R.id.textViewManagerCountry);
		tvManCou.setText(player.getNationality().toString());

		TextView tvSelTeam = (TextView) findViewById(R.id.textViewTeamName);
		String isCustom = settings.getString("customTeamName", "empty");
		if (!isCustom.equals("empty")){
			TextView customTeamCountry = (TextView) findViewById(R.id.textViewSelectedTeamCountry);
			TextView customTeamCountryLabel = (TextView) findViewById(R.id.playerCashToUpgrade);
			customTeamCountryLabel.setVisibility(1);
			customTeamCountry.setVisibility(1);
			customTeamCountry.setText(settings.getString("customTeamCountry", "NOgood"));
			
			tvSelTeam.setText(settings.getString("customTeamName", "CustomNameError"));
		}
		else
		{
			if (selectedMode.equals("Manager")){
				tvSelTeam.setText("Random");
				//set Random and decide now what team the player will control
				//between 4 of worse campaing in 2013
				char letter = player.getName().charAt(1); //may return exception
				if (letter == 'm'){
					selectedTeam = randomTeams.MARUSSIA.getTeam();
				}
				else if (letter == 'c'){
					selectedTeam = randomTeams.CATHERHAM.getTeam();
				}
				else if (letter == 's'){
					selectedTeam = randomTeams.SAUBER.getTeam();
				}
				else if (letter == 't'){
					selectedTeam = randomTeams.TORO_ROSSO.getTeam();
				}
				else 
					selectedTeam = "Lotus";
				
			} else{				
				System.out.println("name of selected team: "+selectedTeam);
				selectedTeam = selectedTeam.replace('_', ' ');
				tvSelTeam.setText(selectedTeam);
				
				System.out.println("Player team: "+godClass.getPCTeam());
			}
		}


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.confirm_start_game, menu);
		return true;
	}
	
	@Override
	public void onStop()
	{
		System.out.println("STOP confirm");
		super.onStop();		
	}
	@Override
	public void onPause()
	{
		System.out.println("PAUSE confirm");
		super.onPause();		
	}
	@Override
    public void onResume(){
    	System.out.print("RESUME");
		System.out.println("confirm");
    	super.onResume();
    }
    
    @Override
    public void onRestart(){
    	System.out.print("RESTART");
    	System.out.println("confirm");
    	super.onRestart();    	
    }

	public void gotoStartGame(View view){
    	System.out.println("Team: "+selectedTeam);
    	godClass.setPlayerControlledTeam(selectedTeam);
    	//godClass.initializeTeams();	//to restart point co

    	//set application to start from beginning 
    	AppLifecycleManager.setRecoveringDisabled(getApplicationContext());
    	
		Intent intent = new Intent(this,BaseTabbedNavigationActivity.class);
		startActivity(intent);
	}

}
