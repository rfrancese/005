package com.example.aa;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Contatta extends ListFragment{
	private TextView t1,t2,t3,t4;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {


		View rootView = inflater.inflate(R.layout.contatta,container, false);
		ListView list=(ListView) rootView.findViewById(android.R.id.list);

		 t1 = (TextView)rootView.findViewById(R.id.da);
		 t2 = (TextView)rootView.findViewById(R.id.fran);
		 t3 = (TextView)rootView.findViewById(R.id.gi);
		 t4 = (TextView)rootView.findViewById(R.id.sito);
		 t1.setOnClickListener(new View.OnClickListener() {
			    public void onClick(View v) {
			    	Intent i = new Intent(Intent.ACTION_SEND);
			    	i.setType("message/rfc822");
			    	i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"davidescarano@gmail.com"});
			    	i.putExtra(Intent.EXTRA_SUBJECT,"ASSISTENZA CLUBADVISOR");
			    	i.putExtra(Intent.EXTRA_TEXT   , "");
			    	try {
			    	    startActivity(Intent.createChooser(i, "Invio in corso..."));
			    	} catch (android.content.ActivityNotFoundException ex) {
			    	    Toast.makeText(getActivity(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
			    	}
			    }
			});
		 t2.setOnClickListener(new View.OnClickListener() {
			    public void onClick(View v) {
			    	Intent i = new Intent(Intent.ACTION_SEND);
			    	i.setType("message/rfc822");
			    	i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"franbarbosa90@gmail.com"});
			    	i.putExtra(Intent.EXTRA_SUBJECT, "ASSISTENZA CLUBADVISOR");
			    	i.putExtra(Intent.EXTRA_TEXT   , "");
			    	try {
			    	    startActivity(Intent.createChooser(i, "Invio in corso..."));
			    	} catch (android.content.ActivityNotFoundException ex) {
			    	    Toast.makeText(getActivity(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
			    	}
			    }
			});
		 t3.setOnClickListener(new View.OnClickListener() {
			    public void onClick(View v) {
			    	Intent i = new Intent(Intent.ACTION_SEND);
			    	i.setType("message/rfc822");
			    	i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"dimartinoluigi@live.it"});
			    	i.putExtra(Intent.EXTRA_SUBJECT, "ASSISTENZA CLUBADVISOR");
			    	i.putExtra(Intent.EXTRA_TEXT   , "");
			    	try {
			    	    startActivity(Intent.createChooser(i, "Invio in corso..."));
			    	} catch (android.content.ActivityNotFoundException ex) {
			    	    Toast.makeText(getActivity(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
			    	}
			    }
			});
		 t4.setOnClickListener(new View.OnClickListener() {
			    public void onClick(View v) {
			    	Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.ludimar.altervista.org/clubadvisor/html/index.html"));
			    	startActivity(browserIntent);
			    }
			});
		return rootView;
	}
	
	public void onStart(){
		super.onStart();
		
	}
}
