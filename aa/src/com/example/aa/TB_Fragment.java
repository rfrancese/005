package com.example.aa;

import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TB_Fragment extends ListFragment {

	  public void onActivityCreated(Bundle savedInstanceState) {
		    super.onActivityCreated(savedInstanceState);
		    ArrayList<String> values = new ArrayList<String>();
		    
		    
		    File files[] = getActivity().getExternalFilesDir("ClubAdvisorPartecipa").listFiles();
		    for(int x = 0 ; x < files.length ; x++) {
		    	
		    	values.add(files[x].getName().replaceAll(".TXT", ""));
		    	
		    }
		    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, values);
		    setListAdapter(adapter);
		  }

		  @Override
		  public void onListItemClick(ListView l, View v, int position, long id) {
		      
		  }
		  public void onAttach(Activity activity){
			  super.onAttach(activity);
			  
		  }
		  
		  public void onCreate(Bundle savedInstanceState){
			  super.onCreate(savedInstanceState);
			  
		  }
		  
		  
	    /*  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
	    	  return inflater.inflate(R.layout.fragment_a, container, false);
	      } */
	      public void onPause(){
	    	  super.onPause();
	      }
	      public void onSaveIstanceState(Bundle outState){
	    	  super.onSaveInstanceState(outState);
	      }
	      public void onStop(){
	    	  super.onStop();
	      }
	      public void onDestroyView(){
	    	  super.onDestroyView();
	      }
	      public void onDestroy(){
	    	  super.onDestroy();
	      }
	      public void onStart(){
	    	  super.onStart();
	      }
	      public void onResume(){
	    	  super.onResume();
	      }
	      public void onDetach(){
	    	  super.onDetach();
	      }
}
