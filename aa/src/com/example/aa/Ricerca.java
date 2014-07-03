package com.example.aa;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import android.widget.Spinner;

public class Ricerca extends ListFragment{

private Spinner spinner;
private int cod;
private int ric;
private int day;
private int month;
private int year;
private DatePicker datePicker;
private Button butt;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.ricerca,container, false);
		  datePicker = (DatePicker) rootView.findViewById(R.id.datePicker1);

		 RadioGroup radioGroup = (RadioGroup) rootView.findViewById(R.id.radioGroup);
		 radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() 
		    {
		        public void onCheckedChanged(RadioGroup group, int checkedId) {
		            // checkedId is the RadioButton selected

		            switch(checkedId) {
		                  case R.id.radioButton1:
		                       ric=0;             
		                       break;
		                  case R.id.radioButton2:
		                      ric=1;
		                      break;
		                  case R.id.radioButton3:
		                      ric=2;
		                    day = datePicker.getDayOfMonth();
		             		month = datePicker.getMonth() + 1;
		             		 year = datePicker.getYear();
		                      break;
		            }   
		        }
		    }); 
        spinner = (Spinner)rootView.findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
		        this.getActivity(), R.array.spinnerplaces, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
		    @Override
		    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
		    	   switch (position){
			       case 0: 
			    	   cod=0;
			    	   break;
			       case 1:
			    	   cod=1;
			    	   break;
			       case 2:
			    	   cod=2;
			    	   break;
			           
			       }
		    }

		    @Override
		    public void onNothingSelected(AdapterView<?> parentView) {
		        // your code here
		    }

		});
	        spinner.setAdapter(adapter);
		butt= (Button) rootView.findViewById(R.id.button1);
		butt.setOnClickListener(new View.OnClickListener() {
			  public void onClick(View view) { 
				  Bundle bundle1 = new Bundle();
			       bundle1.putInt("dove", cod);
			       bundle1.putInt("quando", ric);
			       bundle1.putInt("anno", year);
			       bundle1.putInt("mese", month);
			       bundle1.putInt("giorno",day);
			       Log.i("BUNDLE", bundle1.toString());
			       
			       ListFragment fragment2 = new ricercaEventi();
					fragment2.setArguments(bundle1); 
					android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
					android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					fragmentTransaction.replace(R.id.frame_container, fragment2);
					
					fragmentTransaction.addToBackStack(null);
					fragmentTransaction.commit();
			  }
			});
		return rootView;
	}
	
	public void onStart(){
		super.onStart();
		
	}
	
	   
	 
	

	    public void onNothingSelected(AdapterView<?> parent) {
	        // Another interface callback
	    }
	
	
}