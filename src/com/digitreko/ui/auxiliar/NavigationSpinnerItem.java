package com.digitreko.ui.auxiliar;

public class NavigationSpinnerItem {



	private String title;
	private int icon;

	public NavigationSpinnerItem(String title, int icon){
		this.title = title;
		this.icon = icon;
	}

	public String getTitle(){
		return this.title;     
	}

	public int getIcon(){
		return this.icon;
	}
}

