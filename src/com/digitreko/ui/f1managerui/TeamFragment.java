package com.digitreko.ui.f1managerui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.digitreko.games.model.F1GameManager;
import com.digitreko.games.model.Team;
import com.example.f1managerui.R;
 
public class TeamFragment extends Fragment {
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_team, container, false);
        
        fillTeamData(rootView);
        
        return rootView;
    }
 
    public void fillTeamData(View rootView){
    	final F1GameManager godClass = F1GameManager.getInstance();    	
    	Team playerTeam = godClass.getPCTeam();
    	
    	TextView textName = (TextView) rootView.findViewById(R.id.teamNameTeam);
    	TextView textCountry = (TextView) rootView.findViewById(R.id.teamCountryTeam);
    	TextView textEngine = (TextView) rootView.findViewById(R.id.textEngineTeam);
    	TextView textPoints = (TextView) rootView.findViewById(R.id.constructorPointsTeam);
    	TextView textMoney = (TextView) rootView.findViewById(R.id.teamBalanceTeam);
    	//calcular o balanço projetado pelo atual menos os gastos do ano
    	TextView textEngineers = (TextView) rootView.findViewById(R.id.textEngineersTeam);
    	TextView textRandD = (TextView) rootView.findViewById(R.id.textRandDTeam);
    	TextView textPlanners = (TextView) rootView.findViewById(R.id.textRacePlannersTeam);
    	TextView textFansCount = (TextView) rootView.findViewById(R.id.textFanCountTeam);
    	TextView textReputation = (TextView) rootView.findViewById(R.id.textReputacaoTeam);


    	textName.setText(playerTeam.getName().toString()); 
    	textCountry.setText("Country: "+String.valueOf(playerTeam.getEngine()));
    	textEngine.setText("Engine: "+String.valueOf(playerTeam.getEngine())); 
    	textPoints.setText("Points this Season: " + String.valueOf(playerTeam.getSeasonPoints()));
    	textMoney.setText("Balance "+String.valueOf(playerTeam.getFunds()));
    	//calcular o balanço projetado pelo atual menos os gastos do ano
    	textEngineers.setText("Engeneering Level: "+String.valueOf(playerTeam.getEngineers()));
    	textRandD.setText("R&D Level "+String.valueOf(playerTeam.getRandD()));
    	textPlanners.setText("Race Planners Level: "+String.valueOf(playerTeam.getPlanners()));
    	textFansCount.setText("Fans Count: "+String.valueOf(playerTeam.getFanCount()));
    	textReputation.setText("Reputation: "+String.valueOf(playerTeam.getReputation()));

    	
    }
    
}