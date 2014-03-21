package com.digitreko.ui.f1managerui;


import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.digitreko.games.model.F1GameManager;
import com.digitreko.games.model.Track;
import com.example.f1managerui.R;
 
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
    	List<Track> tracks = godClass.getTracks();
    	int num = godClass.getCurrentRace();
    	Track tr = tracks.get(num);
    	TextView countryName =  (TextView) rootView.findViewById(R.id.textViewCountryName);
    	countryName.setText(tr.getName());
    	
    	
    }
 
}
