package com.example.aa;

import com.example.aa.R;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

@SuppressLint("NewApi")
public class cont_fragment extends Fragment {

	  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	      {
	              View rootView = inflater .inflate(R.layout.cont_fragment, container, false); 
	              
	              return rootView;
	      }
	 }
