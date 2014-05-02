package com.example.aa;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TB_Fragment extends Fragment {

	  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	      {
	              View rootView = inflater .inflate(R.layout.tb_fragment, container, false);  
	              return rootView;
	      }
}
