package com.example.aa;

import com.example.aa.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

  @SuppressLint("NewApi")
   public class FB_Fragment extends ListFragment {
private ListView listView;

/* public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
	 View rootView = inflater.inflate(R.layout.fb_fragment,
               container, false);
	 listView = (ListView) rootView.findViewById(android.R.id.list); 
       return rootView;
 }    */

	 public void onActivityCreated(Bundle savedInstanceState) {
		    super.onActivityCreated(savedInstanceState);
		/*    String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
		        "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
		        "Linux", "OS/2" };
		   ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, values);
		    setListAdapter(adapter); */
		    android.support.v4.app.Fragment fragment2 = new FragmentB();
		    android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
		    android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		    fragmentTransaction.replace(R.id.frame_container, fragment2);
		    fragmentTransaction.commit();
		  } 
 
		  @Override
		 public void onListItemClick(ListView l, View v, int position, long id) {
			  switch(position){
			  case 0: 
				  android.support.v4.app.Fragment fragment2 = new FragmentB();
				    android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
				    android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
				    fragmentTransaction.replace(R.id.frame_container, fragment2);
				    fragmentTransaction.commit();
		      break;
		      default:
		    	  break;
		      }
		  } 
		  public void onAttach(Activity activity){
			  super.onAttach(activity);
			  
		  }
		  
		  public void onCreate(Bundle savedInstanceState){
			  super.onCreate(savedInstanceState);
		  }
		  
		  
	    
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