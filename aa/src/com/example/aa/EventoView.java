package com.example.aa;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class EventoView extends Fragment {

	public void onActivityCreated(Bundle savedInstanceState) {
	    super.onActivityCreated(savedInstanceState);
	   
	  }

	  @Override
	
	
      public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
    	  return inflater.inflate(R.layout.eventoview, container, false);
      } 
      
    
	
}
