package com.digitreko.ui.auxiliar;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.digitreko.f1manager.R;




 
public class NavigationSpinnerAdapter extends BaseAdapter {
 
    private ImageView imgIcon;
    private TextView txtTitle;
    private ArrayList<NavigationSpinnerItem> spinnerNavItem;
    private Context context;
 
    public NavigationSpinnerAdapter(Context context,
            ArrayList<NavigationSpinnerItem> spinnerNavItem) {
        this.spinnerNavItem = spinnerNavItem;
        this.context = context;
    }
 
    @Override
    public int getCount() {
        return spinnerNavItem.size();
    }
 
    @Override
    public Object getItem(int index) {
        return spinnerNavItem.get(index);
    }
 
    @Override
    public long getItemId(int position) {
        return position;
    }
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.navigation_spinner_list, null);
        }
         
        imgIcon = (ImageView) convertView.findViewById(R.id.imgIcon);
        txtTitle = (TextView) convertView.findViewById(R.id.txtTitle);
         
        imgIcon.setImageResource(spinnerNavItem.get(position).getIcon());
        imgIcon.setVisibility(View.GONE);
        txtTitle.setText(spinnerNavItem.get(position).getTitle());
        return convertView;
    }
     
 
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.navigation_spinner_list, null);
        }
         
        imgIcon = (ImageView) convertView.findViewById(R.id.imgIcon);
        txtTitle = (TextView) convertView.findViewById(R.id.txtTitle);
         
        imgIcon.setImageResource(spinnerNavItem.get(position).getIcon());       
        txtTitle.setText(spinnerNavItem.get(position).getTitle());
        return convertView;
    }
 
}
