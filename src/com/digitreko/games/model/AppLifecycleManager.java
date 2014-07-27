package com.digitreko.games.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class AppLifecycleManager {
	
	//sets if application is recovering state (true) or starting over (false)
	final static String ISRECOVERING_KEY = "isRecovering";	
	final static String BACKUP_FOLDER_NAME = "backup";
	
	/**
	 * Sets application status to recover. If this method is called app will get latest data and recover.
	 * 
	 * @param appContext global application context
	 * 	
	 */
	public static void setRecoveringEnabled(Context appContext){
		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(appContext);
    	SharedPreferences.Editor editor = settings.edit();		
		editor.putBoolean("isRecovering", true);
		editor.commit();
	}
	
	/**
	 *	Disable app persistence. App will start over if this method is called.
	 * 
	 * @param appContext global application context
	 */
	public static void setRecoveringDisabled(Context appContext){
		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(appContext);
    	SharedPreferences.Editor editor = settings.edit();		
		editor.putBoolean("isRecovering", false);
		editor.commit();
	}
	
	public static boolean isRecovering(Context appContext){
		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(appContext);
		return settings.getBoolean(ISRECOVERING_KEY, false);
	}

	public static void restoreApplicationState(Context appContext) {
		if (isRecovering(appContext)){
			F1GameManager godClass = F1GameManager.getInstance();
			File myDir = appContext.getDir("backup",Context.MODE_PRIVATE);			
			FileInputStream fis;
			//get player info from file
			try {
				fis = new FileInputStream(new File(myDir,"player"));
				ObjectInputStream is = new ObjectInputStream(fis);
				Manager player = (Manager) is.readObject();
				godClass.setPlayer(player);
				is.close();
			} catch (FileNotFoundException e) {
				System.out.println("PLAYER-File not found. App starting from beginning.");
			} catch (StreamCorruptedException e) {
				//if could not recover serial class
				System.out.println("PLAYER-Failed to recover working stream from file!");
			} catch (IOException e) {
				//in case error accessing files
				System.out.println("PLAYER-Failed to access files!");
			} catch (ClassNotFoundException e) {			
				//in case version of structure is different than found
				System.out.println("PLAYER-Error with mismatch in class versions.");
			}
			//get session info from file
			try {
				fis = new FileInputStream(new File(myDir,"session"));
				ObjectInputStream is = new ObjectInputStream(fis);
				SessionManager sessionManager = (SessionManager) is.readObject();
				godClass.setSessionManager(sessionManager);
				is.close();
			} catch (FileNotFoundException e) {
				System.out.println("SESSION-File not found. App starting from beginning.");
			} catch (StreamCorruptedException e) {
				//if could not recover serial class
				System.out.println("SESSION-Failed to recover working stream from file!");
			} catch (IOException e) {
				//in case error accessing files
				System.out.println("SESSION-Failed to access files!");
			} catch (ClassNotFoundException e) {			
				//in case version of structure is different than found
				System.out.println("SESSION-Error with mismatch in class versions.");
			}
		
			//get cars info from file
			List<Team> newTeamList= new ArrayList<Team>();
			for (Team t: godClass.getTeams()){
				try {
					fis = new FileInputStream(new File(myDir,t.getName()));
					ObjectInputStream is = new ObjectInputStream(fis);
					Team recoveredTeam = (Team) is.readObject();
					newTeamList.add(recoveredTeam);	
					is.close();					
				} catch (FileNotFoundException e) {
					System.out.println("SESSION-File not found. App starting from beginning.");
				} catch (StreamCorruptedException e) {
					//if could not recover serial class
					System.out.println("SESSION-Failed to recover working stream from file!");
				} catch (IOException e) {
					//in case error accessing files
					System.out.println("SESSION-Failed to access files!");
				} catch (ClassNotFoundException e) {			
					//in case version of structure is different than found
					System.out.println("SESSION-Error with mismatch in class versions.");
				}
			}
			godClass.setTeams(newTeamList);
			
			System.out.println(godClass.getPlayer().toString());
			System.out.println(godClass.getSessionManager().toString());
			for (Team t: godClass.getTeams()) 
				System.out.println(t.toString());
		}	
		else{
			System.out.println("Application is not recovering!");
		}
			
	}
	
	/*
	 * This method will be reused to save game data when user uses Save Game functionality.
	 */
	public static void saveBackupToRestoreApp(Context appContext){
    	F1GameManager godClass = F1GameManager.getInstance();
    	Manager player = godClass.getPlayer();
    	List<Team> teams = godClass.getTeams();
    	SessionManager sessionManager = godClass.getSessionManager();
    	
    	File backupDir = appContext.getDir("backup",Context.MODE_PRIVATE);
    	File playerData = new File(backupDir,"player");
    	FileOutputStream fos;
		try {
			fos = new FileOutputStream(playerData);
			ObjectOutputStream os = new ObjectOutputStream(fos);
	    	os.writeObject(player);
	    	os.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		File sessionData = new File(backupDir,"session");
		try {
			fos = new FileOutputStream(sessionData);
			ObjectOutputStream os = new ObjectOutputStream(fos);
	    	os.writeObject(sessionManager);
	    	os.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 
    	for (Team t : teams){    		
	    	File teamData = new File(backupDir,t.getName());
	    	try {
				fos = new FileOutputStream(teamData);
				ObjectOutputStream os = new ObjectOutputStream(fos);
		    	os.writeObject(t);
		    	os.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	    }
    	
    }

	public static void removeBackupFolder() {
		// TODO Auto-generated method stub
		
	}			
	
	
}