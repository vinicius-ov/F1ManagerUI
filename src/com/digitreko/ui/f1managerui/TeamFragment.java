package com.digitreko.ui.f1managerui;




import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.digitreko.f1manager.R;
import com.digitreko.games.model.F1GameManager;
import com.digitreko.games.model.Team;
 
/**
 * TeamFragment.java
 * 
 * View containing all data relative to team selected by the player.
 * This screen have the buttons for upgrade team stats, to check mail
 * and news. 
 * 
 * @author Vinicius
 * 
 *
 */
public class TeamFragment extends Fragment {
 

	protected static final String PLANNERS_LEVEL = "Planners Level ";
	protected static final String RANDD_LEVEL = "R&D Level ";
	private final String UPGRADE_MESSAGE = "Confirm Upgrade: ";
	private final String ENGINEER_LEVEL = "Engineering Level ";
	private final String BALANCE = "Current Balance: ";
	
	private F1GameManager godClass;    	
	private Team playerTeam;
	private TextView textEngineers;
	private TextView textMoney;
	private Button trainEngineers;
	private Button trainPlanners;	
	private Button trainRandD;
	private Button checkNews;
	private Button checkMail;
	private TextView textRandD;
	private TextView textPlanners;
	private TextView textProjected;
	private TextView balanceSmall;
	
	

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_team, container, false);
      
        godClass = F1GameManager.getInstance();
        
        playerTeam = godClass.getPCTeam();

        fillTeamData(rootView);
        
        return rootView;
    }
 
    /**
     * Fill the fields in the view.
     * 
     * @param rootView
     */
    public void fillTeamData(View rootView){    	
    	TextView textName = (TextView) rootView.findViewById(R.id.teamNameTeam);    	
    	TextView textEngine = (TextView) rootView.findViewById(R.id.textEngineTeam);
    	TextView textPoints = (TextView) rootView.findViewById(R.id.constructorPointsTeam);
    	TextView standings = (TextView) rootView.findViewById(R.id.standingTeam);
    	textMoney = (TextView) rootView.findViewById(R.id.teamBalanceTeam);
    	//calcular o balanço projetado pelo atual menos os gastos do ano
    	textProjected = (TextView) rootView.findViewById(R.id.projectedBalanceTeam);
    	
    	textEngineers = (TextView) rootView.findViewById(R.id.engineerLevel);
    	textRandD = (TextView) rootView.findViewById(R.id.randdLevel);
    	textPlanners = (TextView) rootView.findViewById(R.id.plannersLevel);
    	

    	TextView textFansCount = (TextView) rootView.findViewById(R.id.textFanCountTeam);
    	TextView textReputation = (TextView) rootView.findViewById(R.id.textReputacaoTeam);



    	textName.setText(playerTeam.getName().toString());
    	textEngine.setText("Engine: "+String.valueOf(playerTeam.getEngine())); 
    	textPoints.setText("Constructor Points: " + String.valueOf(playerTeam.getSeasonPoints())+" ");
    	int position = godClass.getPlayerTeamPosition()+1;
    	standings.setText("("+position+"th)");	//need to adjust th nd st
    	textMoney.setText(BALANCE+String.valueOf(playerTeam.getFunds())); 
    	
    	//calcular o balanço projetado pelo atual menos os gastos do ano
    	//balanco projetado = funds - (salario1 + salario2) + (receita patrocinadores) 
    	textProjected.setText("Project Balance (End of Season): "+playerTeam.getProjectedBalance());
    	
    	textEngineers.setText("Engineering Level: "+String.valueOf(playerTeam.getEngineers()));
    	textRandD.setText("R&D Level "+String.valueOf(playerTeam.getRandD()));
    	textPlanners.setText("Planners Level: "+String.valueOf(playerTeam.getPlanners()));
    	
    	textFansCount.setText("Fans Count: "+String.valueOf(playerTeam.getFanCount()));
    	textReputation.setText("Reputation: "+String.valueOf(playerTeam.getReputation()));
    	
    	balanceSmall = (TextView) rootView.findViewById(R.id.teamBalanceTeamSmall);
    	balanceSmall.setText(BALANCE.split(" ")[1]+String.valueOf(playerTeam.getFunds()));

    	trainEngineers = (Button) rootView.findViewById(R.id.trainEngineers);
    	trainEngineers.setEnabled(!playerTeam.isEngineersMaxLevel());
    	trainEngineers.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				popAlertUpgradeEngineers(v);
				
			}
		});
    	trainRandD = (Button) rootView.findViewById(R.id.trainReD);
    	trainRandD.setEnabled(!playerTeam.isRandDMaxLevel());
    	trainRandD.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				popAlertUpgradeRandD(v);
				
			}
		});
    	trainPlanners = (Button) rootView.findViewById(R.id.trainPlanners);
    	trainPlanners.setEnabled(!playerTeam.isPlannersMaxLevel());
    	trainPlanners.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				popAlertUpgradePlanners(v);
				
			}
		});    	
        checkMail = (Button) rootView.findViewById(R.id.checkMail);
        checkMail.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			startActivityCheckMail(v);
			
		}

		private void startActivityCheckMail(View v) {
			Toast.makeText(getView().getContext(), "Not implemented yet!", Toast.LENGTH_LONG).show();
			
		}
	});
        checkNews = (Button) rootView.findViewById(R.id.checkNews);
        checkNews.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			startActivityCheckNews(v);
			
		}

		private void startActivityCheckNews(View v) {
			Toast.makeText(getView().getContext(), "Not implemented yet!", Toast.LENGTH_LONG).show();
			
		}
	});
    	
    }
    
    /**
     * Alert for upgrading engineers.
     * @param view
     */
    public void popAlertUpgradeEngineers(View view){
    	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    	final int costToUpgrade = playerTeam.getUpgradeEngineerCost();
    	// Add the buttons
    	builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	               if (playerTeam.getFunds() >= costToUpgrade){
	    	        	   playerTeam.setFunds(playerTeam.getFunds()-costToUpgrade);
	    	        	   playerTeam.setEngineers(playerTeam.getEngineers()+1);
	    	        	   textEngineers.setText(ENGINEER_LEVEL+playerTeam.getEngineers());
	    	           	   textMoney.setText(BALANCE+playerTeam.getFunds());
	    	           	   textProjected.setText("Project Balance (End of Season): "+playerTeam.getProjectedBalance());
	    	           	   trainEngineers.setEnabled(!playerTeam.isEngineersMaxLevel());
	    	   	           balanceSmall.setText("Balance:"+playerTeam.getFunds());
	    	           	   playerTeam.getFinances().setImprovementExpense(playerTeam.getFinances().getImprovementExpense()+costToUpgrade);
    	               }else{
    	            	   Toast.makeText(getView().getContext(), "Not enough funds!", Toast.LENGTH_LONG).show();
    	               }
    	               
    	           }
    	       });
    	builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	               // User cancelled the dialog
    	           }
    	       });

    	// Create the AlertDialog
    	AlertDialog dialog = builder.create();
    	dialog.setTitle(UPGRADE_MESSAGE+"'Engineers'?\n" + "Cost: "+costToUpgrade);
    	dialog.show();
    }
    
    /**
     * Alert for upgrading race planners.
     * 
     * @param view
     */
    public void popAlertUpgradePlanners(View view){
    	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    	final int costToUpgrade = playerTeam.getUpgradePlannerCost();
    	// Add the buttons
    	builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	               if (playerTeam.getFunds() >= costToUpgrade){
	    	        	   playerTeam.setFunds(playerTeam.getFunds()-costToUpgrade);
	    	        	   playerTeam.setPlanners(playerTeam.getPlanners()+1);
	    	        	   textPlanners.setText(PLANNERS_LEVEL+playerTeam.getPlanners());
	    	           	   textMoney.setText(BALANCE+playerTeam.getFunds());
	    	           	   trainPlanners.setEnabled(!playerTeam.isPlannersMaxLevel());
	    	           	   textProjected.setText("Project Balance (End of Season): "+playerTeam.getProjectedBalance());
	    	   	           balanceSmall.setText("Balance:"+playerTeam.getFunds());
	    	           	   playerTeam.getFinances().setImprovementExpense(playerTeam.getFinances().getImprovementExpense()+costToUpgrade);
    	               }else{
    	            	   Toast.makeText(getView().getContext(), "Not enough funds!", Toast.LENGTH_LONG).show();
    	               }
    	               
    	           }
    	       });
    	builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	               // User cancelled the dialog
    	           }
    	       });
    	// Create the AlertDialog
    	AlertDialog dialog = builder.create();
    	dialog.setTitle(UPGRADE_MESSAGE+"'Planners'?\n" + "Cost: "+costToUpgrade);
    	dialog.show();
    }
    
    /**
     * Alert for upgrading R&D
     * 
     * @param view
     */
    public void popAlertUpgradeRandD(View view){
    	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    	final int costToUpgrade = playerTeam.getUpgradeRandDCost();
    	// Add the buttons
    	builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	               if (playerTeam.getFunds() >= costToUpgrade){
	    	        	   playerTeam.setFunds(playerTeam.getFunds()-costToUpgrade);
	    	        	   playerTeam.setrAndD(playerTeam.getRandD()+1);
	    	        	   textRandD.setText(RANDD_LEVEL+playerTeam.getRandD());
	    	           	   textMoney.setText(BALANCE+playerTeam.getFunds());
		    	           trainRandD.setEnabled(!playerTeam.isRandDMaxLevel());
		    	           textProjected.setText("Project Balance (End of Season): "+playerTeam.getProjectedBalance());
		    	           balanceSmall.setText("Balance:"+playerTeam.getFunds());
		    	           playerTeam.getFinances().setImprovementExpense(playerTeam.getFinances().getImprovementExpense()+costToUpgrade);
    	               }else{
    	            	   Toast.makeText(getView().getContext(), "Not enough funds!", Toast.LENGTH_LONG).show();
    	               }    	               
    	           }
    	       });
    	builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	               // User cancelled the dialog
    	           }
    	       });

    	// Create the AlertDialog
    	AlertDialog dialog = builder.create();
    	dialog.setTitle(UPGRADE_MESSAGE+"'R & D'?\n" + "Cost: "+costToUpgrade);
    	dialog.show();
    }
}

