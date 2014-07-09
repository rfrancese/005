package com.example.aa;

import java.util.Calendar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

public class calendario extends ListFragment {
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {


		View rootView = inflater.inflate(R.layout.calendario,container, false);
		ListView list=(ListView) rootView.findViewById(android.R.id.list);
		Toast.makeText(getActivity(), "QUESTA PAGINA NON é DISPONIBILE!" , 
				   Toast.LENGTH_LONG).show();
		return rootView;
	}

}
