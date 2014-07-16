package com.digitreko.games.model;

import java.io.Serializable;


/**
 * This class will contain all accumulators for team expenses.
 * @author Vinicius
 *
 */
public class Finances implements Serializable {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = -2827091869382665221L;
	private int researchExpense;
	private int improvementExpense;
	private int hiresExpense;
	private int repairsExpense;
	private int fansIncome;
	private int merchandiseIncome;

	public Finances(){	
		researchExpense = 0;
		improvementExpense = 0;
		hiresExpense = 0;
		fansIncome = 0;
		merchandiseIncome = 0;
	}

	public int getResearchExpense() {
		return researchExpense;
	}

	public void setResearchExpense(int researchExpense) {
		this.researchExpense = researchExpense;
	}

	public int getImprovementExpense() {
		return improvementExpense;
	}

	public void setImprovementExpense(int improvementExpense) {
		this.improvementExpense = improvementExpense;
	}

	public int getHiresExpense() {
		return hiresExpense;
	}

	public void setHiresExpense(int hiresExpense) {
		this.hiresExpense = hiresExpense;
	}

	public int getFansIncome() {
		return fansIncome;
	}

	public void setFansIncome(int fansIncome) {
		this.fansIncome = fansIncome;
	}

	public int getMerchandiseIncome() {
		return merchandiseIncome;
	}

	public void setMerchandiseIncome(int merchandiseIncome) {
		this.merchandiseIncome = merchandiseIncome;
	}

	public int getRepairsExpense() {
		return repairsExpense;
	}

	public void setRepairsExpense(int repairsExpense) {
		this.repairsExpense = repairsExpense;
	}

	@Override
	public String toString() {
		return "Finances [researchExpense=" + researchExpense
				+ ", improvementExpense=" + improvementExpense
				+ ", hiresExpense=" + hiresExpense + ", repairsExpense="
				+ repairsExpense + ", fansIncome=" + fansIncome
				+ ", merchandiseIncome=" + merchandiseIncome + "]";
	}
	
	
	
	
}
