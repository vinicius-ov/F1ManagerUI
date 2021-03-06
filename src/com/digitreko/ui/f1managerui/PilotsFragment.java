package com.digitreko.ui.f1managerui;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.digitreko.f1manager.R;
import com.digitreko.games.model.F1GameManager;
import com.digitreko.games.model.RaceCar;
import com.digitreko.games.model.Team;

 
public class PilotsFragment extends Fragment {
 
	private final String SKILL_MESSAGE = "Overall Skill: ";
	private Team playerTeam;	
	private TextView skill1;
	private TextView skill2;
	private Button trainPilot1;
	private Button trainPilot2;
	private TextView fundsText;
	
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
        trainPilot1 = (Button) rootView.findViewById(R.id.trainPilot1);
        trainPilot1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            	//gotoMarketScreen(v);
            	popAlertTrainPilot(v, 0);
            }
        });
        trainPilot2 = (Button) rootView.findViewById(R.id.trainPilot2);
        trainPilot2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            	popAlertTrainPilot(v, 1);
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
    	skill1 = (TextView) rootView.findViewById(R.id.skill1);

    	InputStream is = null;    	
    	try {
			is = getActivity().getAssets().open(cars.get(0).getDriver().getFileName().toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
    	
    	pilotPic1.setImageBitmap(BitmapFactory.decodeStream(is));
    	//Log.w("Pilot", playerTeam.toString());
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
    	skill2 = (TextView) rootView.findViewById(R.id.skill2);
    	
    	
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
    	
    	fundsText =  (TextView) rootView.findViewById(R.id.balancePilots);
    	fundsText.setText("Balance: "+String.valueOf(playerTeam.getFunds()));
    	
    	
    	
    }
    
    public void gotoMarketScreen(View view){
    	
    	
    	//Intent intent = new Intent(view.getContext(),SelectTeamActivity.class);
		//getActivity().startActivity(intent);
	}
    
    private void popAlertTrainPilot(View v, final int pilotNumber) {
    	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    	final int costToUpgrade = playerTeam.getCars().get(pilotNumber).getDriver().costToUpgrade();
    	// Add the buttons
    	builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	               if (playerTeam.getFunds() >= costToUpgrade){
	    	        	   playerTeam.setFunds(playerTeam.getFunds()-costToUpgrade);	    	        	   
	    	        	   playerTeam.getCars().get(pilotNumber).getDriver().setSkill(playerTeam.getCars().get(pilotNumber).getDriver().getSkill()+1);
	    	        	   skill1.setText(SKILL_MESSAGE+String.valueOf((int)playerTeam.getCars().get(0).getDriver().getSkill()));	    	        	   
	    	        	   skill2.setText(SKILL_MESSAGE+String.valueOf((int)playerTeam.getCars().get(1).getDriver().getSkill()));	        		   
	    	        	   fundsText.setText("Balance: "+playerTeam.getFunds());
	    	        	   playerTeam.getFinances().setImprovementExpense(playerTeam.getFinances().getImprovementExpense()+costToUpgrade);
    	               }else{
    	            	   Toast.makeText(getView().getContext(), "Not enough funds!", Toast.LENGTH_LONG).show();
    	               }
    	               trainPilot1.setEnabled(!playerTeam.getCars().get(pilotNumber).getDriver().isMaxLevel());    	               
    	           }
    	       });
    	builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	               // User cancelled the dialog
    	           }
    	       });

    	// Create the AlertDialog
    	AlertDialog dialog = builder.create();
    	dialog.setTitle("Train "+playerTeam.getCars().get(pilotNumber).getDriver().getName() + "? Cost: "+costToUpgrade);
    	dialog.show();		
	}
 
}