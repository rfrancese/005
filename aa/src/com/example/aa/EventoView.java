package com.example.aa;

import com.example.aa.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Event;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class EventoView extends Fragment {
private ImageView im;
private TextView titolo;
private TextView descr;
private Evento p1;
	public void onActivityCreated(Bundle savedInstanceState) {
	    super.onActivityCreated(savedInstanceState);
	    
	  }

	  @Override
	
	
      public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		  View v= inflater.inflate(R.layout.eventoview, container, false);
    	  im=(ImageView) v.findViewById(R.id.imageView1);
    	  titolo=(TextView) v.findViewById(R.id.titolo1);
    	  descr=(TextView) v.findViewById(R.id.descr1);
    	  return v;
      } 
      
    
	
}
