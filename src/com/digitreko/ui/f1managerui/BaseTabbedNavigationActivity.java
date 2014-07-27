package com.digitreko.ui.f1managerui;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.List;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.digitreko.games.model.AppLifecycleManager;
import com.digitreko.games.model.F1GameManager;
import com.digitreko.games.model.Manager;
import com.digitreko.games.model.SessionManager;
import com.digitreko.games.model.Team;
import com.digitreko.ui.auxiliar.TabsPagerAdapter;
import com.example.f1managerui.R;
 
public class BaseTabbedNavigationActivity extends FragmentActivity implements
        ActionBar.TabListener {
 
    private ViewPager viewPager;
    private TabsPagerAdapter mAdapter;
    private ActionBar actionBar;
    // Tab titles

    private String[] tabs = { "Next Race","Team" ,"Pilots", "Cars" ,"Finance", "Manager"};

 
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

        AppLifecycleManager.restoreApplicationState(getApplicationContext());
        F1GameManager f1 = F1GameManager.getInstance();        
        f1.setGameMode(1);
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
    
    @Override
	public void onStop()
	{
    	System.out.println("STOP base");
    	//persistence code goes here    	
    	AppLifecycleManager.setRecoveringEnabled(getApplicationContext());
    	AppLifecycleManager.saveBackupToRestoreApp(getApplicationContext());    	
    	//persistence code stops here    	
    	
    	
    	super.onStop();	
	}

    @Override
    public void onResume(){
    	System.out.print("RESUME");
		System.out.println("base");
		//here starts recover code		
		AppLifecycleManager.restoreApplicationState(getApplicationContext());
		//here stops recover code
		super.onResume();
    	
    }
    
    @Override
    public void onRestart(){
    	System.out.print("RESTART");
    	System.out.println("base");
    	super.onRestart();    	

    }
    
    
    //this will ignore back button presses, only activate it in final version
    /*
     * (non-Javadoc)
     * @see android.support.v4.app.FragmentActivity#onBackPressed()
    
    public void onBackPressed(){
    	System.out.println("Back button pressed!!!");
    }
    */
    
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
<<<<<<< HEAD

ByteArrayOutputStream bos = new ByteArrayOutputStream(); 
    	//File file = new File(Activity., "save.sav");
    	FileOutputStream fos = null;
    	try {
			fos = openFileOutput("save.sav", Context.MODE_PRIVATE);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	Team o = F1GameManager.getInstance().getPCTeam();
    	System.out.println(o.toString());
    	//TODO: must save this byte array to a file and recover
    	byte[] buf = null;
        try { 
          ObjectOutput out = new ObjectOutputStream(bos); 
          out.writeObject(o); 
          out.close(); 
     
          // Get the bytes of the serialized object 
          buf = bos.toByteArray();  
          System.out.println(buf.length);
          fos.write(buf);  
        } catch(IOException ioe) { 
          System.out.println("serializeObject " + "error " + ioe.getMessage());
        }
        FileInputStream fis = null;
        byte[] buff =null;
        try {
			fis = openFileInput("save.sav");
			fis.read(buff);
			System.out.println(buf.toString());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Object object = null;
        try { 
            ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(buff)); 
            object = in.readObject(); 
            in.close();           
        
          } catch(ClassNotFoundException cnfe) { 
          } catch(IOException ioe) {
          } 
        
        Team m = (Team) object;
        System.out.println(m.toString());
        
=======
>>>>>>> origin/master
*/