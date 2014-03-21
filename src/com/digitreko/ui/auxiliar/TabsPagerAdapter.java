package com.digitreko.ui.auxiliar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.digitreko.ui.f1managerui.NextRaceFragment;
import com.digitreko.ui.f1managerui.PilotsFragment;
import com.digitreko.ui.f1managerui.TeamFragment;
 
public class TabsPagerAdapter extends FragmentPagerAdapter {
 
    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }
 
    @Override
    public Fragment getItem(int index) {
 
        switch (index) {
        case 0:
            // Top Rated fragment activity
            return new NextRaceFragment();
        case 1:
            // Games fragment activity
        	return new PilotsFragment();
        case 2:
            // Movies fragment activity        	
        	return new TeamFragment();
        }
 
        return null;
    }
 
    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 3;
    }
 
}