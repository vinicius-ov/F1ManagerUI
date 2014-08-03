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
import android.util.Log;

public class AppLifecycleManager {
	
	//sets if application is recovering state (true) or starting over (false)
	final static String ISRECOVERING_KEY = "isRecovering";	
	final static String BACKUP_FOLDER_NAME = "backup";
	final static int NUMBER_OF_SAVES = 10;
	final static String SAVE_NAME =  "save_game_";
	private static final String EMPTY_SAVE = "EMPTY";
	
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
    	String[] saveList = readSaveSlots(appContext);
    	
		
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

	public static void saveGameWithName(Context appContext, String saveGameName) {
		insertSaveInSaveSlot(appContext, saveGameName);
		System.out.println("Will now save game with name ["+saveGameName+"].");
		F1GameManager godClass = F1GameManager.getInstance();
    	Manager player = godClass.getPlayer();
    	List<Team> teams = godClass.getTeams();
    	SessionManager sessionManager = godClass.getSessionManager();
    	
    	File saveDir = appContext.getDir(saveGameName,Context.MODE_PRIVATE);
    	File playerData = new File(saveDir,"player");
    	FileOutputStream fos;
		try {
			System.out.println("Saving manager...");
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
		System.out.println("Saving session info...");
		File sessionData = new File(saveDir,"session");
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
		
		System.out.println("Saving teams...");
    	for (Team t : teams){    		
	    	File teamData = new File(saveDir,t.getName());
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
			AppLifecycleManager.setRecoveringDisabled(appContext);			
	    }		
	}			
	
	public static void loadGameByName(Context appContext, String saveGameName) {
		//if (isRecovering(appContext)){
			F1GameManager godClass = F1GameManager.getInstance();
			File myDir = appContext.getDir(saveGameName,Context.MODE_PRIVATE);			
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
			
			Log.w("DEBUG",godClass.getPlayer().toString());
			Log.w("DEBUG",godClass.getSessionManager().toString());
			for (Team t: godClass.getTeams()) 
				System.out.println(t.toString());
		}
	
	public static String[] readSaveSlots(Context appContext){
		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(appContext);
		String[] results = new String[NUMBER_OF_SAVES];
		for (int i=0;i<NUMBER_OF_SAVES;i++)
		results[i] = settings.getString(SAVE_NAME+i, EMPTY_SAVE);
		return results;
	}
	
	private static void insertSaveInSaveSlot(Context appContext, String saveName){
		System.out.println("Checking if save slot already exists!");
		SharedPreferences data = PreferenceManager.getDefaultSharedPreferences(appContext);
		String[] saves = readSaveSlots(appContext);
		boolean saveEncontrado = false;
		int position = 0;
		System.out.println("Searching if save with player name exists!");
		for (int i=0;i<NUMBER_OF_SAVES;i++){
			if (saves[i].matches(saveName)){
				System.out.println("Save name ["+saveName+"] found.");
				saveEncontrado = true;
				position = i;
				break;
			}			
		}
		SharedPreferences.Editor editor = data.edit();
		//save with same name was found, overwriting
		if (saveEncontrado){
			System.out.println("Save name ["+saveName+"] being overwrited...");
			editor.putString(SAVE_NAME+position, saveName);	
		}
		//save was not found, will write in the first available position
		else{
			System.out.print("New Save name. Creating save ["+saveName+"]");
			for (int i=0;i<NUMBER_OF_SAVES;i++){
				if (saves[i].matches("EMPTY")){
					editor.putString(SAVE_NAME+i, saveName);
					System.out.println(" and setting it to position ["+position+"].");
					break;
				}
			}
		}
		editor.commit();
	}
	
	public static void cleanAllSaves(Context appContext){
		SharedPreferences data = PreferenceManager.getDefaultSharedPreferences(appContext);				
		System.out.println("Deleting saves one by one...");
		SharedPreferences.Editor editor = data.edit();
		for (int i=0;i<NUMBER_OF_SAVES;i++){
			editor.remove(SAVE_NAME+i);
		}		
		editor.commit();
	
	}
	
	/*
	private static boolean saveDirExists(Context appContext,String saveName){
		String[] saves = readSaveSlots(appContext);
		for (String save : saves) {
			return save.equals(saveName);
		}
		return false;
	}
	
	private static int positionToSave(Context appContext){
		String[] saves = readSaveSlots(appContext);
		for (int i=0;i<NUMBER_OF_SAVES;i++) 
			if (saves[i].equals(EMPTY_SAVE)){
				return i;
			}
		return -1;
	}
	private void setNewSaveSlot(){
		
	}
	*/
	
}