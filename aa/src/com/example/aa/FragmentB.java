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
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import android.app.Activity;
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

public class FragmentB extends ListFragment {
	String url_str = "http://www.residentadvisor.net/events.aspx?ai=172";
	String elementName = "a";


	/*public void onActivityCreated(Bundle savedInstanceState) {
	    super.onActivityCreated(savedInstanceState);
	    String[] values = new String[] { "o", "o", "WindowsMobile",
	        "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
	        "Linux", "OS/2" };
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, values);
	    setListAdapter(adapter);
	  } */

	  
	  
	  public static final String ARG_SECTION_NUMBER = "section_number";

      private ListView listView;
     public static ArrayList<Evento> menuItems=new ArrayList<Evento>();
      private CustomArrayAdapter mAdapter;

      @Override
      public View onCreateView(LayoutInflater inflater, ViewGroup container,
              Bundle savedInstanceState) {
          View rootView = inflater.inflate(R.layout.fragment,
                  container, false);
          listView = (ListView) rootView.findViewById(android.R.id.list);
           HtmlParser parser=new HtmlParser(url_str);
           menuItems=parser.getElement(elementName);
    /*      new LoadEventiTask().execute(); */

     /*     Evento a=new Evento();
          a.setNome("FESTAASD");
          menuItems.add(a); 
          mAdapter = new CustomArrayAdapter(getActivity(), android.R.id.list, menuItems);
          listView.setAdapter(mAdapter); */
          
        
          return rootView;
      }

      @Override
      public void onActivityCreated(Bundle savedInstanceState) {
          super.onActivityCreated(savedInstanceState);
      /*    int num = getArguments().getInt(ARG_SECTION_NUMBER); */
          // GlobalList is a class that holds global variables, arrays etc
          // getMenuCategories returns global arraylist which is initialized in GlobalList class
     /*     menuItems = GlobalList.getMenuCategories().get(num).getMenu(); */
     /*   HtmlParser hp = new HtmlParser(url_str);
       menuItems= hp.Stampa(elementName); */

          mAdapter = new CustomArrayAdapter(getActivity(),android.R.id.list, menuItems);
          listView.setAdapter(mAdapter);  

  }
      private class LoadEventiTask extends AsyncTask<Void, Void, String> {


  		private static final String URL = "http://www.residentadvisor.net/events.aspx?ai=172";
  		private TagNode rootNode;

  		@Override
  		protected void onPreExecute() {
  			
  		}

  		@Override
  		protected String doInBackground(Void... params) {

  			// inizializzazione dell'oggetto HtmlCleaner utile a generare un html pulito
  						HtmlCleaner cleaner = new HtmlCleaner();
  						CleanerProperties props = cleaner.getProperties();
  						props.setAllowHtmlInsideAttributes(true);
  						props.setAllowMultiWordAttributes(true);
  						props.setRecognizeUnicodeChars(true);
  						props.setOmitComments(true);

  						// apertura della connessione
  						URL url;
  						try {

  							url = new URL(URL);
  							URLConnection conn = url.openConnection();

  							//ora utilizziamo l'oggetto cleaner per "ripulire" l'html e inizializzare l'oggetto rootNode
  							rootNode = cleaner.clean(new InputStreamReader(conn.getInputStream()));


  						} catch (MalformedURLException e) {
  							// TODO Auto-generated catch block
  							Log.e("Error", e.getMessage());

  						} catch (IOException e) {
  							// TODO Auto-generated catch block
  							Log.e("Error", e.getMessage());
  						}
  						return null;
  		}
  		protected void onPostExecute(String result) {
  	    Evento nuovo = null;
  	

  			try{
  			  TagNode Elements[] = rootNode.getElementsByName(elementName, true);
  	        for (int i = 0; Elements != null && i < Elements.length; i++)
  	        {

  	            String type = Elements[i].getAttributeByName("title");
  	            if ( type != null )
  	            {
  	            		 nuovo=new Evento();
  					     nuovo.setNome(type);
  	                menuItems.add(nuovo);
  	            } 

  	        } 
		           
		        		   

  			}
  			 catch(Exception e)
		        {
		            e.printStackTrace();
		        }
  		}
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
    	  
      }
      public void onResume(){
    	  super.onResume();
    	  
      }
      public void onDetach(){
    	  super.onDetach();
      }
}

