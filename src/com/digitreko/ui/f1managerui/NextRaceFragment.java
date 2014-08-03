package com.digitreko.ui.f1managerui;


import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.digitreko.f1manager.R;
import com.digitreko.games.model.F1GameManager;
import com.digitreko.games.model.Track;
 
public class NextRaceFragment extends Fragment {
 
	
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_next_race, container, false);
    
        
        fillFields(rootView);
        return rootView;
    }
    
    private void fillFields(View rootView){
    	final F1GameManager godClass;
    	godClass = F1GameManager.getInstance();
    	//List<Track> tracks = godClass.getTracks();
    	int num = godClass.getSessionManager().getCurrentRace();
    	Track tr = null;
		try {
			tr = godClass.getTrack(num);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    	InputStream is = null;
    	try {
			is = getActivity().getAssets().open(tr.getFileName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
    	
    	TextView countryName =  (TextView) rootView.findViewById(R.id.textViewCountryName);
    	countryName.setText(tr.getName());
    	
    	ImageView iv = (ImageView) rootView.findViewById(R.id.raceMap);
    	iv.setImageBitmap(BitmapFactory.decodeStream(is));
    	
    	ListView teamsStandings = (ListView) rootView.findViewById(R.id.listConstructorStanding);
    	ArrayAdapter<String> teamAdapter =new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.listview_standings,R.id.stringStandings,godClass.getTeamNamesAndPoints());    	
    	teamsStandings.setAdapter(teamAdapter);
    	
    	ListView pilotsStandings = (ListView) rootView.findViewById(R.id.listPilotStandings);
    	ArrayAdapter<String> pilotsAdapter =new ArrayAdapter<String>(getActivity().getApplicationContext(),R.layout.listview_standings,R.id.stringStandings,godClass.getPilotNamesAndPoints());
    	pilotsStandings.setAdapter(pilotsAdapter);    	
    	
    }
    
    private class StableArrayAdapter extends ArrayAdapter<String> {

		public StableArrayAdapter(Context context, int resource,
				int textViewResourceId, String[] objects) {
			super(context, resource, textViewResourceId, objects);
			// TODO Auto-generated constructor stub
		}

		
    	
    }
 
}
















