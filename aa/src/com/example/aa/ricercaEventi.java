package com.example.aa;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;

import com.example.aa.R;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import android.app.Activity;
import android.app.Dialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ricercaEventi extends ListFragment {
	
	 

	private int tempo;
	private int luogo;
	String elementName = "a";
	private Dialog pDialog;
	public static final String ARG_SECTION_NUMBER = "section_number";
	private ListView listView;
	public static ArrayList<Evento> menuItems=new ArrayList<Evento>();
	private CustomArrayAdapter mAdapter;
	private OtherEventsParser parser;




	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment,container, false);
		Bundle bundle1 = getArguments();
	       tempo=bundle1.getInt("quando");
		    luogo =  bundle1.getInt("dove");
		
		 int anno=bundle1.getInt("anno");
		 int mese=bundle1.getInt("mese");
		 int giorno=bundle1.getInt("giorno");
		listView = (ListView) rootView.findViewById(android.R.id.list);



		return rootView;
	}

	

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		




	}
	
	 public void onBackPressed() {
	        this.getActivity().finish();
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
		if(menuItems.size() == 0) {
			
			 parser=new OtherEventsParser(menuItems,elementName,this.getActivity(),tempo,luogo);
			parser.execute();
			
			
			
	
			
		}else {
			
			
			mAdapter = new CustomArrayAdapter(getActivity(),android.R.id.list, menuItems);
			mAdapter.notifyDataSetChanged();
			listView.setAdapter(mAdapter);
		}
		
	
		

	}
	public void onResume(){
		super.onResume();

	}
	public void onDetach(){
		super.onDetach();
	}
	public void onListItemClick(ListView l, View v, int position, long id) {
       String col= menuItems.get(position).getHref();
       String col1= menuItems.get(position).getSrcImgSmall();
       final Bundle bundle = new Bundle();
       bundle.putString("href", col);
       bundle.putString("srcImgSmall", col1);
       Log.i("BUNDLE", bundle.toString());
       
		Fragment fragment2 = new EventoView();
		fragment2.setArguments(bundle); 
		android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
		android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.replace(R.id.frame_container, fragment2);
		fragmentTransaction.addToBackStack(null);
		fragmentTransaction.commit();


	}
}
