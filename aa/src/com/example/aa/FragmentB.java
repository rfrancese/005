package com.example.aa;
import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import org.htmlcleaner.*;
public class FragmentB extends Fragment {
	String url_str = "http://www.residentadvisor.net/events.aspx?ai=172";
	String elementName = "a";


	/*public void onActivityCreated(Bundle savedInstanceState) {
	    super.onActivityCreated(savedInstanceState);
	    String[] values = new String[] { "o", "o", "WindowsMobile",
	        "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
	        "Linux", "OS/2" };
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, values);
	    setListAdapter(adapter);
	  } */

	  
	  
	  public static final String ARG_SECTION_NUMBER = "section_number";

      private ListView listView;
      private ArrayList<Evento> menuItems;
      private CustomArrayAdapter mAdapter;

      @Override
      public View onCreateView(LayoutInflater inflater, ViewGroup container,
              Bundle savedInstanceState) {
          View rootView = inflater.inflate(R.layout.fragment,
                  container, false);
          listView = (ListView) rootView.findViewById(android.R.id.list);

          return rootView;
      }

      @Override
      public void onActivityCreated(Bundle savedInstanceState) {
          super.onActivityCreated(savedInstanceState);
      /*    int num = getArguments().getInt(ARG_SECTION_NUMBER); */
          // GlobalList is a class that holds global variables, arrays etc
          // getMenuCategories returns global arraylist which is initialized in GlobalList class
     /*     menuItems = GlobalList.getMenuCategories().get(num).getMenu(); */
         HtmlParser hp = new HtmlParser(url_str);
     /*    menuItems= hp.Stampa(elementName);
     
          mAdapter = new CustomArrayAdapter(getActivity(), android.R.id.list, menuItems);
          listView.setAdapter(mAdapter); */
      
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

