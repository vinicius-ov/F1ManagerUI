package com.digitreko.ui.f1managerui;


import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.digitreko.games.model.F1GameManager;
import com.digitreko.games.model.RaceCar;
import com.digitreko.games.model.Team;
import com.example.f1managerui.R;

 
public class PilotsFragment extends Fragment {
 
	Team playerTeam;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_pilots, container, false);
        Button marketBtn = (Button) rootView.findViewById(R.id.gotoAllPilots);
        marketBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            	gotoMarketScreen(v);
            }
        });
        
        fillPilotsData(rootView); 
        return rootView;
    }
    
    public void fillPilotsData(View rootView){
    	final F1GameManager godClass = F1GameManager.getInstance();    	
    	playerTeam = godClass.getPCTeam();
    	List<RaceCar> cars = playerTeam.getCars();
    	String target = "";
    	
    	ImageView pilotPic1 = (ImageView) rootView.findViewById(R.id.pilotPic1);    	
    	TextView nameText = (TextView) rootView.findViewById(R.id.pilotName1); 
    	TextView points1 = (TextView) rootView.findViewById(R.id.pilotPoints1);
    	TextView repo1 = (TextView) rootView.findViewById(R.id.reputation11);
    	TextView salary1 = (TextView) rootView.findViewById(R.id.salary1);

    	pilotPic1.setImageResource(R.drawable.di_resta);    	
    	nameText.setText(cars.get(0).getDriver().getName().toString());
       	target = "Season Points " + String.valueOf(cars.get(0).getDriver().getSeasonPoints());
    	points1.setText(target);    	 
    	target = "Reputation " + String.valueOf(cars.get(0).getDriver().getReputation());
    	repo1.setText(target);
    	target = "Salary " +String.valueOf(cars.get(0).getDriver().getCurrentSalary());
    	salary1.setText(target);
    	
    	ImageView pilotPic2 = (ImageView) rootView.findViewById(R.id.pilotPic2);
    	TextView nameText2 = (TextView) rootView.findViewById(R.id.pilotName2);
    	target = "Season Points ";
    	TextView points2 = (TextView) rootView.findViewById(R.id.pilotPoints22);
    	TextView repo2 = (TextView) rootView.findViewById(R.id.reputation22);
    	TextView salary2 = (TextView) rootView.findViewById(R.id.salary2);
    	
    	pilotPic2.setImageResource(R.drawable.di_resta);    	
    	nameText2.setText(cars.get(1).getDriver().getName().toString());
    	target = "Season Points " + String.valueOf(cars.get(1).getDriver().getSeasonPoints());
    	points2.setText(target);    	 
    	target = "Reputation " + String.valueOf(cars.get(1).getDriver().getReputation());
    	repo2.setText(target);
    	target = "Salary " +String.valueOf(cars.get(1).getDriver().getCurrentSalary());
    	salary2.setText(target);
    
    }
    
    public void gotoMarketScreen(View view){
    	Intent intent = new Intent(view.getContext(),SelectTeamActivity.class);
		getActivity().startActivity(intent);
	}
 
}