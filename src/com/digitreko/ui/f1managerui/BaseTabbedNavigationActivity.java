package com.digitreko.ui.f1managerui;


import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.digitreko.ui.auxiliar.TabsPagerAdapter;
import com.example.f1managerui.R;
 
public class BaseTabbedNavigationActivity extends FragmentActivity implements
        ActionBar.TabListener {
 
    private ViewPager viewPager;
    private TabsPagerAdapter mAdapter;
    private ActionBar actionBar;
    // Tab titles
    private String[] tabs = { "Next Race", "Pilots", "Team" , "Cars" , "Manager" , "Finance"};
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_navigation_spinner);
 
        // Initilization
        viewPager = (ViewPager) findViewById(R.id.pager);
        actionBar = getActionBar();
        mAdapter = new TabsPagerAdapter(getSupportFragmentManager());
 
        viewPager.setAdapter(mAdapter);
        //actionBar.setHomeButtonEnabled(false); //stopped working after who knows
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);       
 
        // Adding Tabs
        for (String tab_name : tabs) {
            actionBar.addTab(actionBar.newTab().setText(tab_name)
                    .setTabListener(this));
        }
 
        /**
         * on swiping the viewpager make respective tab selected
         * */
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
 
            @Override
            public void onPageSelected(int position) {
                // on changing the page
                // make respected tab selected
                actionBar.setSelectedNavigationItem(position);
            }
 
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }
 
            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }
 
    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {
    }
 
    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        // on tab selected
        // show respected fragment view
        viewPager.setCurrentItem(tab.getPosition());
    }
 
    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
    }
 
}


/*
import java.util.ArrayList;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.digitreko.ui.auxiliar.NavigationSpinnerAdapter;
import com.digitreko.ui.auxiliar.NavigationSpinnerItem;
import com.example.f1managerui.R;
 
public class BaseNavigationSpinnerActivity extends Activity implements ActionBar.OnNavigationListener{
 
    // action bar
    private ActionBar actionBar;
 
    // Title navigation Spinner data
    private ArrayList<NavigationSpinnerItem> navSpinner;
     
    // Navigation adapter
    private NavigationSpinnerAdapter adapter;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_navigation_spinner);
 
        actionBar = getActionBar();
         
        // Hide the action bar title
        actionBar.setDisplayShowTitleEnabled(false);        
        
        // Enabling Spinner dropdown navigation
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
         
        // Spinner title navigation data
        navSpinner = new ArrayList<NavigationSpinnerItem>();
        navSpinner.add(new NavigationSpinnerItem("Pilots", R.drawable.car1));
        navSpinner.add(new NavigationSpinnerItem("Team", R.drawable.car1));
        navSpinner.add(new NavigationSpinnerItem("Finance", R.drawable.car1));
        navSpinner.add(new NavigationSpinnerItem("Cars", R.drawable.car1));    
        navSpinner.add(new NavigationSpinnerItem("Manager", R.drawable.car1));
        navSpinner.add(new NavigationSpinnerItem("Eram6Telas", R.drawable.car1));
         
        // title drop down adapter
        adapter = new NavigationSpinnerAdapter(getApplicationContext(), navSpinner);
 
        // assigning the spinner navigation    
        actionBar.setListNavigationCallbacks(adapter, this);
    }
 
    
     //On selecting action bar icons
     
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    return true;
    }
 
    
      //Actionbar navigation item select listener
     
    @Override
    public boolean onNavigationItemSelected(int itemPosition, long itemId) {
    // Action to be taken after selecting a spinner item
        return true;
    }
}
*/