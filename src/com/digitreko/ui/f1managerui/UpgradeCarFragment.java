package com.digitreko.ui.f1managerui;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.digitreko.f1manager.R;
import com.digitreko.games.model.CarPart;
import com.digitreko.games.model.F1GameManager;
import com.digitreko.games.model.RaceCar;
import com.digitreko.games.model.Team;
 

public class UpgradeCarFragment extends Fragment {
 
	private final String LEVEL = "LVL ";
	private final String BALANCE = "Balance: ";
	private final String UPGRADE_MESSAGE = "Confirm Upgrade: ";
	private final String REPAIR_MESSAGE = "Confirm Repair: ";
	
	private F1GameManager godClass;
	private Team pcTeam;
	
	TextView brakesLevel;
	TextView suspensionLevel;
	TextView aerodynamicsLevel;
	TextView tiresLevel;
	TextView engineLevel;
	TextView fundsText;	
	TextView brakesCondition;  
	TextView suspensionCondition;
	TextView aerodynamicsCondition;
	TextView tiresCondition;
	TextView engineCondition;
	Button upgradeTires;
	Button upgradeSuspension;
	Button upgradeBrakes;
	Button upgradeEngine;
	Button upgradeAerodynamics;
	Button repairTires;
	Button repairSuspension;
	Button repairBrakes;
	Button repairEngine;
	Button repairAerodynamics;
	View rootView;
	int carIndex;
	
	public interface OnRefreshListener {
	    public void onRefresh();
	}
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
    	rootView = inflater.inflate(R.layout.fragment_race_car, container, false);
    	
    	godClass = F1GameManager.getInstance();
    	pcTeam = godClass.getPCTeam();   	    	
        
        upgradeTires = (Button) rootView.findViewById(R.id.upgradeTiress);       
        upgradeTires.setOnClickListener(new View.OnClickListener() {       
            @Override
            public void onClick(View v) {            	
            	popAlertUpgradeTires(v);            	
            }
        });
        upgradeSuspension = (Button) rootView.findViewById(R.id.upgradeSuspension);       
        upgradeSuspension.setOnClickListener(new View.OnClickListener() {       
            @Override
            public void onClick(View v) {            	
            	popAlertUpgradeSuspension(v);            	
            }
        });
        upgradeBrakes = (Button) rootView.findViewById(R.id.upgradeBrakes);       
        upgradeBrakes.setOnClickListener(new View.OnClickListener() {       
            @Override
            public void onClick(View v) {            	
            	popAlertUpgradeBrakes(v);            	
            }
        });
        upgradeEngine = (Button) rootView.findViewById(R.id.upgradeEngine);       
        upgradeEngine.setOnClickListener(new View.OnClickListener() {       
            @Override
            public void onClick(View v) {            	
            	popAlertUpgradeEngine(v);            	
            }
        });
        upgradeAerodynamics = (Button) rootView.findViewById(R.id.upgradeAerodynamics);       
        upgradeAerodynamics.setOnClickListener(new View.OnClickListener() {       
            @Override
            public void onClick(View v) {            	
            	popAlertUpgradeAerodynamics(v);            	
            }
        });
        checkUpgradeEnabledButtons();
        
        repairTires = (Button) rootView.findViewById(R.id.repairTires);
        repairTires.setOnClickListener(new View.OnClickListener() {       
            @Override
            public void onClick(View v) {            	
            	popAlertRepairTires(v);            	
            }			
        });
        
        repairBrakes = (Button) rootView.findViewById(R.id.repairBrakes);
        repairBrakes.setOnClickListener(new View.OnClickListener() {       
            @Override
            public void onClick(View v) {            	
            	popAlertRepairBrakes(v);            	
            }
        });
        
        repairSuspension = (Button) rootView.findViewById(R.id.repairSuspension);
        repairSuspension.setOnClickListener(new View.OnClickListener() {       
            @Override
            public void onClick(View v) {            	
            	popAlertRepairSuspension(v);            	
            }
        });
        
        repairEngine = (Button) rootView.findViewById(R.id.repairEngine);
        repairEngine.setOnClickListener(new View.OnClickListener() {       
            @Override
            public void onClick(View v) {            	
            	popAlertRepairEngine(v);            	
            }
        });
        
        repairAerodynamics= (Button) rootView.findViewById(R.id.repairAerodynamics);
        repairAerodynamics.setOnClickListener(new View.OnClickListener() {       
            @Override
            public void onClick(View v) {            	
            	popAlertRepairAerodynamics(v);            	
            }
        });
        checkRepairEnabledButtons();
        

        InputStream is = null;
    	try {
			is = getActivity().getAssets().open("car_top_view2.jpg");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	ImageView iv = (ImageView) rootView.findViewById(R.id.raceCarUpgrade);
    	iv.setImageBitmap(BitmapFactory.decodeStream(is));
    	
    	ArrayList<String> entries = new ArrayList<String>();
    	String name= pcTeam.getCars().get(0).getDriver().getName()+" - Car #0";
    	entries.add(name);
    	name= pcTeam.getCars().get(1).getDriver().getName()+" - Car #1";
    	entries.add(name);
    	ArrayAdapter<String> adapter = new ArrayAdapter<String>(rootView.getContext(), android.R.layout.simple_spinner_item, entries);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinCars = (Spinner) rootView.findViewById(R.id.spinnerDriverCar);
        spinCars.setAdapter(adapter);
    	spinCars.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				carIndex = position;
				fillFields(rootView);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});

        fillFields(rootView);
        
        return rootView;
    }
    
    

	private void fillFields(View rootView){
    	
    	int funds = pcTeam.getFunds();
    	Log.w("DEBUG","Indice: "+carIndex);
    	
    	    	
    	
    	//os dois carros do time sao iguis entao pode pegar qualquer 1
    	//na hora do update fazer um foreach na lista de carros
    	//o dummy
    	RaceCar dummy = pcTeam.getCars().get(carIndex);
    	
    	brakesLevel =  (TextView) rootView.findViewById(R.id.brakesLevel);
    	brakesLevel.setText(LEVEL+dummy.getBrakes().getPower());

    	engineLevel =  (TextView) rootView.findViewById(R.id.engineLevel);
    	engineLevel.setText(LEVEL+dummy.getEngine().getPower()); 
    	
    	aerodynamicsLevel =  (TextView) rootView.findViewById(R.id.aerodynamicsLevel);
    	aerodynamicsLevel.setText(LEVEL+dummy.getAerodynamics().getPower());
    	
    	tiresLevel =  (TextView) rootView.findViewById(R.id.tiresLevel);
    	tiresLevel.setText(LEVEL+dummy.getTires().getPower());
    	
    	suspensionLevel =  (TextView) rootView.findViewById(R.id.suspensionLevel);
    	suspensionLevel.setText(LEVEL+dummy.getSuspension().getPower());
    	
    	fundsText =  (TextView) rootView.findViewById(R.id.playerCashToUpgrade);
    	fundsText.setText(BALANCE+String.valueOf(funds));
    	
    	brakesCondition = (TextView) rootView.findViewById(R.id.brakesCondition);
    	brakesCondition.setText(String.valueOf((int)dummy.getBrakes().getCondition()));
    	
    	suspensionCondition= (TextView) rootView.findViewById(R.id.suspensionCondition);
    	suspensionCondition.setText(String.valueOf((int)dummy.getSuspension().getCondition()));
    	
    	aerodynamicsCondition= (TextView) rootView.findViewById(R.id.aerodynamicsCondition);
    	aerodynamicsCondition.setText(String.valueOf((int)dummy.getAerodynamics().getCondition()));
    	
    	tiresCondition= (TextView) rootView.findViewById(R.id.tiresCondition);
    	tiresCondition.setText(String.valueOf((int)dummy.getTires().getCondition()));
    	
    	engineCondition= (TextView) rootView.findViewById(R.id.engineCondition);
    	engineCondition.setText(String.valueOf((int)dummy.getEngine().getCondition()));
    	
    	checkUpgradeEnabledButtons();
    	checkRepairEnabledButtons();
    	
    }
    
    
    public void popAlertUpgradeTires(View view){
    	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    	final int costToUpgrade = pcTeam.getCars().get(carIndex).getTires().costToUpgrade();
    	// Add the buttons
    	builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {    		
    	           public void onClick(DialogInterface dialog, int id) {
    	               if (pcTeam.getFunds() >= costToUpgrade){
	    	        	   pcTeam.setFunds(pcTeam.getFunds()-costToUpgrade);	    	        	   
	    	        	   pcTeam.getCars().get(carIndex).getTires().setPower(pcTeam.getCars().get(carIndex).getTires().getPower()+1);	    	        	   
	    	        	   tiresLevel.setText(LEVEL+pcTeam.getCars().get(carIndex).getTires().getPower());
	    	           	   fundsText.setText(BALANCE+pcTeam.getFunds());
	    	           	   upgradeTires.setEnabled(!pcTeam.getCars().get(carIndex).getTires().isMaxLevel());
	    	               pcTeam.getFinances().setImprovementExpense(pcTeam.getFinances().getImprovementExpense()+costToUpgrade);
	    	               FinanceFragment.updateImprovementExpense(pcTeam.getFinances().getImprovementExpense());
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
    	dialog.setTitle(UPGRADE_MESSAGE+"'Tires'?\n" + "Cost: "+costToUpgrade);
    	dialog.show();
    }
    public void popAlertUpgradeBrakes(View view){
    	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    	final int costToUpgrade = pcTeam.getCars().get(carIndex).getBrakes().costToUpgrade();
    	// Add the buttons
    	builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	               if (pcTeam.getFunds() >= costToUpgrade){
	    	        	   pcTeam.setFunds(pcTeam.getFunds()-costToUpgrade);
	    	        	   
	    	        	   pcTeam.getCars().get(carIndex).getBrakes().setPower(pcTeam.getCars().get(carIndex).getBrakes().getPower()+1);
	    	        	       	        	   
	    	        	   brakesLevel.setText(LEVEL+pcTeam.getCars().get(carIndex).getBrakes().getPower());
	    	           	   fundsText.setText(BALANCE+pcTeam.getFunds());
	    	           	   upgradeBrakes.setEnabled(!pcTeam.getCars().get(carIndex).getBrakes().isMaxLevel());	
	    	               pcTeam.getFinances().setImprovementExpense(pcTeam.getFinances().getImprovementExpense()+costToUpgrade);
	    	               FinanceFragment.updateImprovementExpense(pcTeam.getFinances().getImprovementExpense());
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
    	dialog.setTitle(UPGRADE_MESSAGE+"'Brakes'?\n" + "Cost: "+costToUpgrade);
    	dialog.show();

    }
    //TODO: Alert in aerodynamics is too big and not showing everything
    public void popAlertUpgradeAerodynamics(View view){
    	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    	
    	final int costToUpgrade = pcTeam.getCars().get(carIndex).getAerodynamics().costToUpgrade();
    	// Add the buttons
    	builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	               if (pcTeam.getFunds() >= costToUpgrade){
	    	        	   pcTeam.setFunds(pcTeam.getFunds()-costToUpgrade);
	    	     
	    	        	   pcTeam.getCars().get(carIndex).getAerodynamics().setPower(pcTeam.getCars().get(carIndex).getAerodynamics().getPower()+1);
	    	         	        	   
	    	        	   aerodynamicsLevel.setText(LEVEL+pcTeam.getCars().get(carIndex).getAerodynamics().getPower());
	    	           	   fundsText.setText(BALANCE+pcTeam.getFunds());
	    	           	   upgradeAerodynamics.setEnabled(!pcTeam.getCars().get(carIndex).getAerodynamics().isMaxLevel());
	    	               pcTeam.getFinances().setImprovementExpense(pcTeam.getFinances().getImprovementExpense()+costToUpgrade);
	    	               FinanceFragment.updateImprovementExpense(pcTeam.getFinances().getImprovementExpense());
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
    	dialog.setTitle(UPGRADE_MESSAGE+"'Aerodynamics'? " + "Cost: "+costToUpgrade);
    	dialog.show();

    }
    public void popAlertUpgradeSuspension(View view){
    	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    	final int costToUpgrade = pcTeam.getCars().get(0).getSuspension().costToUpgrade();
    	// Add the buttons
    	builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	               if (pcTeam.getFunds() >= costToUpgrade){
	    	        	   pcTeam.setFunds(pcTeam.getFunds()-costToUpgrade);
	    	        	   
	    	        	   pcTeam.getCars().get(carIndex).getSuspension().setPower(pcTeam.getCars().get(carIndex).getSuspension().getPower()+1);
	    	        	       	        	   
	    	        	   suspensionLevel.setText(LEVEL+pcTeam.getCars().get(carIndex).getSuspension().getPower());
	    	           	   fundsText.setText(BALANCE+pcTeam.getFunds());
	    	           	   upgradeSuspension.setEnabled(!pcTeam.getCars().get(carIndex).getSuspension().isMaxLevel());
	    	               pcTeam.getFinances().setImprovementExpense(pcTeam.getFinances().getImprovementExpense()+costToUpgrade);
	    	               FinanceFragment.updateImprovementExpense(pcTeam.getFinances().getImprovementExpense());
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
    	dialog.setTitle(UPGRADE_MESSAGE+"'Suspension'? " + "Cost: "+costToUpgrade);
    	dialog.show();
    }
    public void popAlertUpgradeEngine(View view){
    	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    	final int costToUpgrade = pcTeam.getCars().get(carIndex).getEngine().costToUpgrade();
    	// Add the buttons
    	builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	               if (pcTeam.getFunds() >= costToUpgrade){
	    	        	   pcTeam.setFunds(pcTeam.getFunds()-costToUpgrade);
	    	        	   
	    	        	   pcTeam.getCars().get(carIndex).getEngine().setPower(pcTeam.getCars().get(carIndex).getEngine().getPower()+1);
	    	        	       	        	   
	    	        	   engineLevel.setText(LEVEL+pcTeam.getCars().get(carIndex).getEngine().getPower());
	    	           	   fundsText.setText(BALANCE+pcTeam.getFunds());
	    	           	   upgradeEngine.setEnabled(!pcTeam.getCars().get(carIndex).getEngine().isMaxLevel());
	    	               pcTeam.getFinances().setImprovementExpense(pcTeam.getFinances().getImprovementExpense()+costToUpgrade);
	    	               FinanceFragment.updateImprovementExpense(pcTeam.getFinances().getImprovementExpense());
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
    	dialog.setTitle(UPGRADE_MESSAGE+"'Engine'?\n" + "Cost: "+costToUpgrade);
    	dialog.show();
    }
    
    private void popAlertRepairSuspension(View v) {
    	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    	final int costToRepair = pcTeam.getCars().get(carIndex).getSuspension().costToRepair();
    	// Add the buttons
    	builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	               if (pcTeam.getFunds() >= costToRepair){
	    	        	   pcTeam.setFunds(pcTeam.getFunds()-costToRepair);
	    	        	   
	    	        	   pcTeam.getCars().get(carIndex).getSuspension().setCondition(CarPart.getMaxcondition());
	    	        	      	        	   
	    	        	   suspensionCondition.setText(String.valueOf((int)pcTeam.getCars().get(carIndex).getSuspension().getCondition()));
	    	           	   fundsText.setText(BALANCE+pcTeam.getFunds());
	    	           	repairSuspension.setEnabled(!pcTeam.getCars().get(carIndex).getSuspension().isMaxCondition());
	    	               pcTeam.getFinances().setRepairsExpense(pcTeam.getFinances().getRepairsExpense()+costToRepair);
	    	               FinanceFragment.updateRepairExpense(pcTeam.getFinances().getRepairsExpense());
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
    	dialog.setTitle(REPAIR_MESSAGE+"'Suspension'? " + "Cost: "+costToRepair);
    	dialog.show();
	}
    
    private void popAlertRepairTires(View v) {
    	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    	final int costToRepair = pcTeam.getCars().get(carIndex).getTires().costToRepair();
    	// Add the buttons
    	builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	               if (pcTeam.getFunds() >= costToRepair){
	    	        	   pcTeam.setFunds(pcTeam.getFunds()-costToRepair);
	    	        	   
	    	        	   pcTeam.getCars().get(carIndex).getTires().setCondition(CarPart.getMaxcondition());
    	        	   
	    	        	   tiresCondition.setText(String.valueOf((int)pcTeam.getCars().get(carIndex).getTires().getCondition()));
	    	           	   fundsText.setText(BALANCE+pcTeam.getFunds());
	    	           	   repairTires.setEnabled(!pcTeam.getCars().get(carIndex).getTires().isMaxCondition());
	    	               pcTeam.getFinances().setRepairsExpense(pcTeam.getFinances().getRepairsExpense()+costToRepair);
	    	               FinanceFragment.updateRepairExpense(pcTeam.getFinances().getRepairsExpense());
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
    	dialog.setTitle(REPAIR_MESSAGE+"'Tires'? " + "Cost: "+costToRepair);
    	dialog.show();		
	}
 
    private void popAlertRepairEngine(View v) {
    	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    	final int costToRepair = pcTeam.getCars().get(carIndex).getEngine().costToRepair();
    	// Add the buttons
    	builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	               if (pcTeam.getFunds() >= costToRepair){
	    	        	   pcTeam.setFunds(pcTeam.getFunds()-costToRepair);
	    	        	   
	    	        	   pcTeam.getCars().get(carIndex).getEngine().setCondition(CarPart.getMaxcondition());
	    	        	       	        	   
	    	        	   engineCondition.setText(String.valueOf((int)pcTeam.getCars().get(carIndex).getEngine().getCondition()));
	    	           	   fundsText.setText(BALANCE+pcTeam.getFunds());
	    	           	   repairEngine.setEnabled(!pcTeam.getCars().get(carIndex).getEngine().isMaxCondition());
	    	               pcTeam.getFinances().setRepairsExpense(pcTeam.getFinances().getRepairsExpense()+costToRepair);
	    	               FinanceFragment.updateRepairExpense(pcTeam.getFinances().getRepairsExpense());
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
    	dialog.setTitle(REPAIR_MESSAGE+"'Engine'? " + "Cost: "+costToRepair);
    	dialog.show();		
	}
    
    private void popAlertRepairAerodynamics(View v) {
    	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    	final int costToRepair = pcTeam.getCars().get(carIndex).getAerodynamics().costToRepair();
    	// Add the buttons
    	builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	               if (pcTeam.getFunds() >= costToRepair){
	    	        	   pcTeam.setFunds(pcTeam.getFunds()-costToRepair);
	    	        	   
	    	        	   pcTeam.getCars().get(carIndex).getAerodynamics().setCondition(CarPart.getMaxcondition());
	    	        	       	        	   
	    	        	   aerodynamicsCondition.setText(String.valueOf((int)pcTeam.getCars().get(carIndex).getAerodynamics().getCondition()));
	    	           	   fundsText.setText(BALANCE+pcTeam.getFunds());
	    	           	   repairAerodynamics.setEnabled(!pcTeam.getCars().get(carIndex).getAerodynamics().isMaxCondition());
	    	               pcTeam.getFinances().setRepairsExpense(pcTeam.getFinances().getRepairsExpense()+costToRepair);
	    	               FinanceFragment.updateRepairExpense(pcTeam.getFinances().getRepairsExpense());
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
    	dialog.setTitle(REPAIR_MESSAGE+"'Aerodynamics'? " + "Cost: "+costToRepair);
    	dialog.show();		
	}
    
    private void popAlertRepairBrakes(View v) {
    	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    	final int costToRepair = pcTeam.getCars().get(carIndex).getBrakes().costToRepair();
    	// Add the buttons
    	builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	               if (pcTeam.getFunds() >= costToRepair){
	    	        	   pcTeam.setFunds(pcTeam.getFunds()-costToRepair);
	    	        	   
	    	        	   pcTeam.getCars().get(carIndex).getBrakes().setCondition(CarPart.getMaxcondition());
	    	        	      	        	   
	    	        	   brakesCondition.setText(String.valueOf((int)pcTeam.getCars().get(carIndex).getBrakes().getCondition()));
	    	           	   fundsText.setText(BALANCE+pcTeam.getFunds());
	    	           	   repairBrakes.setEnabled(!pcTeam.getCars().get(carIndex).getBrakes().isMaxCondition());
	    	               pcTeam.getFinances().setRepairsExpense(pcTeam.getFinances().getRepairsExpense()+costToRepair);
	    	               FinanceFragment.updateRepairExpense(pcTeam.getFinances().getRepairsExpense());
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
    	dialog.setTitle(REPAIR_MESSAGE+"'Brakes'? " + "Cost: "+costToRepair);
    	dialog.show();	
	}
    
    private void checkUpgradeEnabledButtons(){
    	upgradeTires.setEnabled		  (!pcTeam.getCars().get(carIndex).getTires().isMaxLevel());
        upgradeSuspension.setEnabled  (!pcTeam.getCars().get(carIndex).getSuspension().isMaxLevel());
        upgradeBrakes.setEnabled	  (!pcTeam.getCars().get(carIndex).getBrakes().isMaxLevel());
        upgradeEngine.setEnabled	  (!pcTeam.getCars().get(carIndex).getEngine().isMaxLevel());
        upgradeAerodynamics.setEnabled(!pcTeam.getCars().get(carIndex).getAerodynamics().isMaxLevel());
    }
    private void checkRepairEnabledButtons() {
    	repairTires.setEnabled		 (!pcTeam.getCars().get(carIndex).getTires().isMaxCondition());
        repairSuspension.setEnabled  (!pcTeam.getCars().get(carIndex).getSuspension().isMaxCondition());
        repairBrakes.setEnabled		 (!pcTeam.getCars().get(carIndex).getBrakes().isMaxCondition());
        repairEngine.setEnabled		 (!pcTeam.getCars().get(carIndex).getEngine().isMaxCondition());
        repairAerodynamics.setEnabled(!pcTeam.getCars().get(carIndex).getAerodynamics().isMaxCondition());
	}
    
}
