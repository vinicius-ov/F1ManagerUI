package com.digitreko.ui.auxiliar;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.digitreko.ui.f1managerui.FinanceFragment;
import com.digitreko.ui.f1managerui.ManagerFragment;
import com.digitreko.ui.f1managerui.NextRaceFragment;
import com.digitreko.ui.f1managerui.PilotsFragment;
import com.digitreko.ui.f1managerui.TeamFragment;
import com.digitreko.ui.f1managerui.UpgradeCarFragment;

public class TabsPagerAdapter extends FragmentStatePagerAdapter {

	private static final int NUMBER_OF_FRAGMENTS = 6;
	//public static FinanceFragment fm = ;

	public TabsPagerAdapter(FragmentManager fm) {		
		super(fm);
	}

	@Override
	public Fragment getItem(int index) {

		switch (index) {
		case 0:			
			return new NextRaceFragment();			
		case 1:
			return new TeamFragment();
		case 2:        				
			return new PilotsFragment();
		case 3:		
			return new UpgradeCarFragment();
		case 4:
			//return new UpgradeFinanceFragment();
			return new FinanceFragment();
		case 5:			
			return new ManagerFragment();			
		}

		return null;
	}

	@Override
	public int getCount() {
		// get item count - equal to number of tabs
		return NUMBER_OF_FRAGMENTS;
	}


}