package com.digitreko.ui.f1managerui;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.digitreko.f1manager.R;
import com.digitreko.games.model.F1GameManager;
import com.digitreko.games.model.Manager;
import com.digitreko.games.model.Team;
 
public class FinanceFragment extends Fragment {
	
	private static TextView improvementsExpense;
	private static TextView repairsExpense;
	private static TextView pilotsExpense;
	private static TextView researchExpense;
	private F1GameManager godClass;
	private TextView currentLoan;
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
    	View rootView = inflater.inflate(R.layout.fragment_finance, container, false);
    	
    	godClass = F1GameManager.getInstance();
    	
        fillFinance(rootView);
        
        return rootView;
    }
    
    public interface OnRefreshListener {
	    public void onRefresh();
	}
 
    public void fillFinance(View rootView){    	    	
    	Manager player = godClass.getPlayer();
    	Team playerTeam = godClass.getPCTeam();
    	
    	TextView pilotsExpense = (TextView)rootView.findViewById(R.id.pilotsExpense);
    	//TextView researchExpense = (TextView)rootView.findViewById(R.id.researchExpense);
    	improvementsExpense = (TextView)rootView.findViewById(R.id.improvementsExpense);
    	TextView hiresExpense = (TextView)rootView.findViewById(R.id.hiresExpense);
    	repairsExpense = (TextView)rootView.findViewById(R.id.repairsExpense);
    	
    	pilotsExpense.setText("Pilots: "+playerTeam.getPilotsExpense()+" (end of year)");
    	//researchExpense.setText("Research: "+playerTeam.getFinances().getResearchExpense());
    	improvementsExpense.setText("Improvements: "+playerTeam.getFinances().getImprovementExpense());
    	hiresExpense.setText("Hires: "+playerTeam.getFinances().getHiresExpense());
    	repairsExpense.setText("Repairs: "+playerTeam.getFinances().getRepairsExpense());
    	
    	TextView sponsorsIncome = (TextView)rootView.findViewById(R.id.sponsorsIncome);
    	TextView fansIncome = (TextView)rootView.findViewById(R.id.fansIncome);
    	TextView merchandiseIncome = (TextView)rootView.findViewById(R.id.merchandiseIncome);
    	
    	sponsorsIncome.setText("Sponsors: "+playerTeam.getSponsorsIncome());
    	fansIncome.setText("Fans: "+playerTeam.getFinances().getFansIncome());
    	merchandiseIncome.setText("Merchandise: "+playerTeam.getFinances().getMerchandiseIncome());
    	
    	TextView projectedBalance = (TextView)rootView.findViewById(R.id.projectedFundsFinance);
    	projectedBalance.setText("Projected Balance (end of year):\n"+playerTeam.getProjectedBalance());
    	
    	currentLoan = (TextView)rootView.findViewById(R.id.currentLoanText);
    	currentLoan.setText("Current Loan: "+playerTeam.getLoans());
    	TextView interest = (TextView)rootView.findViewById(R.id.interestText);
    	interest.setText("Interest: "+godClass.generateInterest()+"%");
    	
    	Button takeLoan = (Button)rootView.findViewById(R.id.takeLoanBtn);
    	takeLoan.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				popAlertTakeLoan(v);				
			}
		});
    	
    	Button payLoan = (Button)rootView.findViewById(R.id.payLoanBt);
    	payLoan.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (godClass.getPCTeam().getLoans() > 0){
					popAlertPayLoan(v);				
				}
				else{
					Toast.makeText(getView().getContext(), "No loans to repay!", Toast.LENGTH_LONG).show();
				}
			}
		});
    	
    	
    	//TODO: o que vai mostrar, a acumulada ou a atual?
    	//a atual vai calcular na hora e mostrar, vai mostrar os gastos na temporada?
    	//ou a atual? precisa decidir se vai ser acumulada ou na hora
    	
    }

	public static void updateRepairExpense(int expense) {		
		repairsExpense.setText("Repairs: "+expense);
	}

	public static void updateResearchExpense(int expense) {
		// TODO Auto-generated method stub
		
	}

	public static void updateImprovementExpense(int expense) {
		improvementsExpense.setText("Improvements: "+expense);		
	}
	
	public void popAlertTakeLoan(View view){
    	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());    	
    	// Add the buttons
    	builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	        	   godClass.getPCTeam().setFunds(godClass.getPCTeam().getFunds()+50000);
    	               godClass.getPCTeam().setLoans(godClass.getPCTeam().getLoans()+50000);
    	               currentLoan.setText("Total Loaned: "+godClass.getPCTeam().getLoans());
    	           }
    	       });
    	builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	               // User cancelled the dialog
    	           }
    	       });

    	// Create the AlertDialog
    	AlertDialog dialog = builder.create();
    	dialog.setTitle("Loan $50000?");
    	dialog.show();
    }
	
	public void popAlertPayLoan(View view){
    	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());    	
    	// Add the buttons
    	builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	        	   if (godClass.getPCTeam().getFunds() >= (int)(50000*(1+godClass.generateInterest()/100))){
    	        	   godClass.getPCTeam().setFunds(godClass.getPCTeam().getFunds()-50000);
    	               godClass.getPCTeam().setLoans(godClass.getPCTeam().getLoans()-50000);
    	               currentLoan.setText("Total Loaned: "+godClass.getPCTeam().getLoans());
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
    	dialog.setTitle("Pay Loan portion with "+(int)godClass.generateInterest()+" interest? Cost: "+(int)(50000*(1+godClass.generateInterest()/100))+"?");
    	dialog.show();
    }
}