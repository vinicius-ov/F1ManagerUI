package com.digitreko.ui.f1managerui;

import com.digitreko.games.model.AppLifecycleManager;

import android.app.Fragment;

public abstract class BaseFragment extends Fragment {

	
	public void restoreDataState(){
		AppLifecycleManager.restoreApplicationState(getActivity().getApplicationContext());
	}
}
