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
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class FragmentB extends ListFragment {



	String url_str = "http://www.residentadvisor.net/events.aspx?ai=172";
	String elementName = "a";
	private Dialog pDialog;
	public static final String ARG_SECTION_NUMBER = "section_number";
	private ListView listView;
	public static ArrayList<Evento> menuItems=new ArrayList<Evento>();
	private CustomArrayAdapter mAdapter;




	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
//		EventsParser parser=new EventsParser(menuItems,elementName,this.getActivity());
//		parser.execute();
//		
		View rootView = inflater.inflate(R.layout.fragment,container, false);
		listView = (ListView) rootView.findViewById(android.R.id.list);
//		
//		
////		if( menuItems.size() == 0 ) {
////			//Do nothings 
////			Toast t = Toast.makeText(getActivity(), "ATTENDI", Toast.LENGTH_LONG);
////			t.show();
////		}
//		
////		for(int i = 0 ; i < menuItems.size() ; i++) {
////			EventsDescriptionParser p = new EventsDescriptionParser(menuItems.get(i));
////			p.execute();
////			// QUESTO CICLO FOR EFFETTUA IL PARSER DELLA PAGINA DI OGNI EVENTO E SALVA TUTTO NELL OGGETTO PASSATOGLI(EVENTO)
////			// IL PROBLEMA � CHE � COME SE NON VENISSE EFFETTUATO. BISOGNA CAPIRE IL PERCH�.
////			//DAto che questo non viene eseguito, tutte le stringhe che si prende il parser , risultano vuote nella visualizzazione.
////		}

//		


		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		




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
			EventsParser parser=new EventsParser(menuItems,elementName,this.getActivity());
			parser.execute();
			
			
			
	
			
		}else {
			mAdapter = new CustomArrayAdapter(getActivity(),android.R.id.list, menuItems);
			mAdapter.notifyDataSetChanged();
			listView.setAdapter(mAdapter);
		}
		
	/*	if(menuItems.size() != 0) {

			for(int i = 0 ; i < menuItems.size() ; i++) {
				
			EventsDescriptionParser p = new EventsDescriptionParser(menuItems.get(i),getActivity());
			p.execute();
			
			
			}
		} */
		
//		EventsParser parser=new EventsParser(menuItems,elementName,this.getActivity());
//		parser.execute();
//		
//		mAdapter = new CustomArrayAdapter(getActivity(),android.R.id.list, menuItems);
//		mAdapter.notifyDataSetChanged();
//		listView.setAdapter(mAdapter);
		
		

	}
	public void onResume(){
		super.onResume();

	}
	public void onDetach(){
		super.onDetach();
	}
	public void onListItemClick(ListView l, View v, int position, long id) {
       String col= menuItems.get(position).getHref();
       final Bundle bundle = new Bundle();
       bundle.putString("href", col);
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

