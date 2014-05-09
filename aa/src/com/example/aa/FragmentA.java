package com.example.aa;
import android.app.Activity;
import android.os.Bundle;

import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

	
	import android.app.Activity;
	import android.os.Bundle;
	import android.view.Menu;
	import android.widget.CheckBox;
	import android.widget.*;

	public class FragmentA extends Activity implements CompoundButton.OnCheckedChangeListener {
		CheckBox cb;

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.fragment_a);
			cb=(CheckBox)findViewById(R.id.check);
			cb.setOnCheckedChangeListener(this);
		}
		
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
			if(isChecked)
				cb.setText("Checked");
			else 
				cb.setText("Unchecked");
		}

		

}
