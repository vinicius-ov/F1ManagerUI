package com.digitreko.ui.f1managerui;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
            	//gotoMarketScreen(v);
            	Context context = v.getContext();
            	CharSequence text = "Not implemented yet...";
            	int duration = Toast.LENGTH_SHORT;

            	Toast toast = Toast.makeText(context, text, duration);
            	toast.show();
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
    	TextView moral1 = (TextView) rootView.findViewById(R.id.moral1);
    	TextView age1 = (TextView) rootView.findViewById(R.id.age1);
    	TextView skill1 = (TextView) rootView.findViewById(R.id.skill1);

    	InputStream is = null;    	
    	try {
			is = getActivity().getAssets().open(cars.get(0).getDriver().getFileName().toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
    	
    	pilotPic1.setImageBitmap(BitmapFactory.decodeStream(is));
    	Log.w("Pilot", playerTeam.toString());
    	nameText.setText(cars.get(0).getDriver().getName().toString());
       	target = "Season Points: " + String.valueOf(cars.get(0).getDriver().getSeasonPoints());
    	points1.setText(target);    	 
    	target = "Reputation: " + String.valueOf(cars.get(0).getDriver().getReputation());
    	repo1.setText(target);
    	target = "Salary: " +String.valueOf(cars.get(0).getDriver().getCurrentSalary());
    	salary1.setText(target);
    	target = "Moral: " +String.valueOf(cars.get(0).getDriver().getMoral());
    	moral1.setText(target);
    	target = "Age: " +String.valueOf(cars.get(0).getDriver().getAge());
    	age1.setText(target);
    	target = "Overall Skill: " +String.valueOf(cars.get(0).getDriver().getSkill());
    	skill1.setText(target);
    	//TODO: verificar o salario gerado se esta dando certo com toString()
    	//verificar se esta indo para tela todo o valor do salario e formatar a saida para ficar mais amigaevl
    	
    	
    	
    	ImageView pilotPic2 = (ImageView) rootView.findViewById(R.id.pilotPic2);
    	TextView nameText2 = (TextView) rootView.findViewById(R.id.pilotName2);
    	target = "Season Points ";
    	TextView points2 = (TextView) rootView.findViewById(R.id.pilotPoints22);
    	TextView repo2 = (TextView) rootView.findViewById(R.id.reputation22);
    	TextView salary2 = (TextView) rootView.findViewById(R.id.salary2);
    	TextView moral2 = (TextView) rootView.findViewById(R.id.moral2);
    	TextView age2 = (TextView) rootView.findViewById(R.id.age2);
    	TextView skill2 = (TextView) rootView.findViewById(R.id.skill2);
    	
    	
    	try {
    		is = getActivity().getAssets().open(cars.get(1).getDriver().getFileName().toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	pilotPic2.setImageBitmap(BitmapFactory.decodeStream(is));
    	nameText2.setText(cars.get(1).getDriver().getName().toString());
    	target = "Season Points: " + String.valueOf(cars.get(1).getDriver().getSeasonPoints());
    	points2.setText(target);    	 
    	target = "Reputation: " + String.valueOf(cars.get(1).getDriver().getReputation());
    	repo2.setText(target);
    	target = "Salary: " +String.valueOf(cars.get(1).getDriver().getCurrentSalary());
    	salary2.setText(target);
    	target = "Moral: " +String.valueOf(cars.get(1).getDriver().getMoral());
    	moral2.setText(target);
    	target = "Age: " +String.valueOf(cars.get(1).getDriver().getAge());
    	age2.setText(target);
    	target = "Overall Skill: " +String.valueOf(cars.get(1).getDriver().getSkill());
    	skill2.setText(target);
    }
    
    public void gotoMarketScreen(View view){
    	
    	
    	//Intent intent = new Intent(view.getContext(),SelectTeamActivity.class);
		//getActivity().startActivity(intent);
	}
 
}