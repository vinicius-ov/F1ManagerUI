package com.digitreko.ui.f1managerui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.digitreko.f1manager.R;
import com.digitreko.games.model.F1GameManager;
import com.digitreko.games.model.Manager;
import com.digitreko.games.model.Team;
 
public class ManagerFragment extends Fragment {
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        
    	View rootView = inflater.inflate(R.layout.fragment_manager, container, false);
        fillManager(rootView);
        
        return rootView;
    }
 
    public void fillManager(View rootView){
    	final F1GameManager godClass = F1GameManager.getInstance();    	
    	Manager player = godClass.getPlayer();
    	Team playerTeam = godClass.getPCTeam();
    	
    	TextView managerName = (TextView)rootView.findViewById(R.id.nameManager);
    	TextView managerAge = (TextView)rootView.findViewById(R.id.ageManager);
    	TextView managerCountry = (TextView)rootView.findViewById(R.id.nationalityManager);
    	TextView managerTeam = (TextView)rootView.findViewById(R.id.teamManager);
    	TextView managerYears = (TextView)rootView.findViewById(R.id.yearsManager);
    	
    	managerName.setText(player.getName().toString());
    	managerAge.setText(String.valueOf("Age: "+player.getAge()));
    	managerCountry.setText("Country: "+player.getNationality().toString());
    	managerTeam.setText("Current Team: "+playerTeam.getName().toString());
    	managerYears.setText("Years in command: "+ player.getYearsInCommand());
    	
    	
    	

    	
    }
    
}