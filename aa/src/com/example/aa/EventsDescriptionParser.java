package com.example.aa;



import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

public class EventsDescriptionParser extends AsyncTask<Void, Void, String> {
	
    private Evento event = null;
    private TagNode rootNode;
    private String URL; 
   
    
    public EventsDescriptionParser(Evento event ){
    	this.event = event;
    	URL = event.getHref();
    }
    
  

 

	@Override
	protected String doInBackground(Void... arg0) {
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
					URLConnection conn= url.openConnection();

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
		    
		

				try{
				      	TagNode liElements[] = rootNode.getElementsByName("li", true);
				      	
				      	for(int i = 0 ; liElements!=null && i < liElements.length ; i++){
				      		TagNode divElem[] = liElements[i].getElementsByName("div", true);
				      		
				      		if(divElem[0].getText().equals("Date /")) {
				      			event.setDataText(liElements[i].getText().toString());
				      			
				      		}
				      		if(divElem[0].getText().equals("Venue /")) {
				      			event.setAddress(liElements[i].getText().toString());
				      		}
				      		if(divElem[0].getText().equals("Cost /")) {
				      			event.setCosto(liElements[i].getText().toString());
				      		}
				      		if(divElem[0].getText().equals("Promoters /")) {
				      			event.setCosto(liElements[i].getText().toString());
				      		}
				      	}
				
				     TagNode pElements[] = rootNode.getElementsByName("p",true);
				      String allDescription="";
		        	  for (int x = 0 ; x < pElements.length ; x++) {
		        		  if(pElements[x].getAttributeByName("class").equals("lineup large")) {
		        			  
		        			  
		        			 TagNode childs[] =  pElements[x].getChildTags();
		        			 for(int z = 0 ; z < childs.length ; z++) {
		        				 allDescription = allDescription+childs[z].getText().toString();
		        			 }
		        		  }
		        	  }
		        	  event.setBigDescription(allDescription);
		        	  
		        	   

				}
				 catch(Exception e)
		        {
		            e.printStackTrace();
		        }
					
	    }
}