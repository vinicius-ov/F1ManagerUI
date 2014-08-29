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

/**
 * Class that manages saving and recovering app state, saving game and
 * loading. Also sets in shared preferences if app should recover or 
 * not.
 * 
 * Conditions: 
 * When starting a game, key isRecovering must be set to
 * false using setRecovingDisabled().
 * When app goes background, state is saved in backup folder and
 * isRecoveringEnabled() is called.
 * When app is back in front, checks if isRecovering key is set to true, causing the game
 * to recover its state from the backup folder. If false, recover from backup is ignored.
 * When saving data, get player (manager) name and use it as save name. Create a folder with the name
 * and save all data. This name is added to the first save slot available.
 * TODO: must alert the player in case all save slots are occupied.
 * When loading a game, recover data from folder with save name chosen and launch Base activity.
 * TODO: add an option to erase saved games.
 * TODO: only save slots manage data, folders are kept. Must delete these folder to avoid
 * bloating system.
 * TODO: join both save and load methods to avoid repetition.
 * TODO: join isRecoveringEnabled and isRecoveringDisabled to avoid code repetition.
 * 
 * @author Vinicius
 *
 */
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
	
	/**
	 * Check if app is recovering from paused state or starting over.
	 * 
	 * @param appContext
	 * @return
	 */
	public static boolean isRecovering(Context appContext){
		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(appContext);
		return settings.getBoolean(ISRECOVERING_KEY, false);
	}

	/**
	 * Restore app from pause/stop/destroy. Working with all states.
	 * TODO: merge with load game procedure.
	 * 
	 * @param appContext
	 */
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
	
	/**
	 * Save game data to context to recover later when onResume is called.
	 * TODO: merge with save game.
	 * @param appContext
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

	/**
	 * Save player data manually when player pushes save button.
	 *  
	 * @param appContext
	 * @param saveGameName
	 */
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
	
	/**
	 * Load game data when player select game saved in the list.
	 * 
	 * @param appContext
	 * @param saveGameName
	 */	
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
				System.out.println(saveGameName+" PLAYER-File not found. App starting from beginning.");
			} catch (StreamCorruptedException e) {
				//if could not recover serial class
				System.out.println(saveGameName+" PLAYER-Failed to recover working stream from file!");
			} catch (IOException e) {
				//in case error accessing files
				System.out.println(saveGameName+" PLAYER-Failed to access files!");
			} catch (ClassNotFoundException e) {			
				//in case version of structure is different than found
				System.out.println(saveGameName+" PLAYER-Error with mismatch in class versions.");
			}
			//get session info from file
			try {
				fis = new FileInputStream(new File(myDir,"session"));
				ObjectInputStream is = new ObjectInputStream(fis);
				SessionManager sessionManager = (SessionManager) is.readObject();
				godClass.setSessionManager(sessionManager);
				is.close();
			} catch (FileNotFoundException e) {
				System.out.println(saveGameName+" SESSION-File not found. App starting from beginning.");
			} catch (StreamCorruptedException e) {
				//if could not recover serial class
				System.out.println(saveGameName+" SESSION-Failed to recover working stream from file!");
			} catch (IOException e) {
				//in case error accessing files
				System.out.println(saveGameName+" SESSION-Failed to access files!");
			} catch (ClassNotFoundException e) {			
				//in case version of structure is different than found
				System.out.println(saveGameName+" SESSION-Error with mismatch in class versions.");
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
					System.out.println(saveGameName+" TEAM-File not found. App starting from beginning.");
				} catch (StreamCorruptedException e) {
					//if could not recover serial class
					System.out.println(saveGameName+" TEAM-Failed to recover working stream from file!");
				} catch (IOException e) {
					//in case error accessing files
					System.out.println(saveGameName+" TEAM-Failed to access files!");
				} catch (ClassNotFoundException e) {			
					//in case version of structure is different than found
					System.out.println(saveGameName+" TEAM-Error with mismatch in class versions.");
				}
			}
			godClass.setTeams(newTeamList);
			
			Log.w("DEBUG",godClass.getPlayer().toString());
			Log.w("DEBUG",godClass.getSessionManager().toString());
			for (Team t: godClass.getTeams()) 
				System.out.println(t.toString());
		}
	
	/**
	 * Read all save slots from preferences.
	 * 
	 * @param appContext
	 * @return a list of strings containing all save names or empty
	 */
	public static String[] readSaveSlots(Context appContext){
		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(appContext);
		String[] results = new String[NUMBER_OF_SAVES];
		for (int i=0;i<NUMBER_OF_SAVES;i++)
		results[i] = settings.getString(SAVE_NAME+i, EMPTY_SAVE);
		return results;
	}
	
	/**
	 * Inserts manager name into save slots. Search the first occurrence of EMPTY and stores in that position.
	 * TODO: must alert the player if no save slots are available.
	 * 
	 * @param appContext
	 * @param saveName
	 */
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
	
	/**
	 * Clean all save slots but keep save data.
	 * TODO: clean save slots AND folders.
	 * 
	 * @param appContext
	 */
	public static void cleanAllSaves(Context appContext){
		SharedPreferences data = PreferenceManager.getDefaultSharedPreferences(appContext);				
		System.out.println("Deleting saves one by one...");
		SharedPreferences.Editor editor = data.edit();
		for (int i=0;i<NUMBER_OF_SAVES;i++){
			File dir = appContext.getDir(data.getString(SAVE_NAME+i,"EMPTY"),Context.MODE_PRIVATE);
			dir.delete();
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