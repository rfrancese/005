package com.example.aa;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends FragmentActivity {
    ViewPager viewPager=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		viewPager=(ViewPager)findViewById(R.id.pager);
		FragmentManager fragmentManager=getSupportFragmentManager();
		viewPager.setAdapter(new MyAdapter(fragmentManager));
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	class MyAdapter extends FragmentPagerAdapter{

		public MyAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int i) {
			Fragment fragment=null;
			if(i==0){
				fragment=new FragmentA();
			}
			if(i==1){
				fragment=new FragmentB();
			}
			if(i==2){
				fragment=new FragmentC();
			}
			return fragment;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 3;
		}
		
	}
   public CharSequence getPageTitle(int position){
	   if(position==0)
	   {
		   return "Tab 1";
	   }
	   if(position==1)
	   {
		   return "Tab 2";
	   }
	   if(position==2)
	   {
		   return "Tab 3";
	   }
	return null;
   }
}
