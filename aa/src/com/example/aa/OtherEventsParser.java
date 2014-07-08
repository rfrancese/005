package com.example.aa;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

public class OtherEventsParser extends AsyncTask<Void, Void, String> {
	private int tempo;
	private int luogo;
		private ProgressDialog pDialog;
		private ArrayList<Evento> menuItems;
		private TagNode rootNode;
		private String URL = null ; 
		private String elementName;
		private Context thisContext;
		private int tempo1;
		private int luogo1;
		private int anno;
		private int mese;
		private int giorno;
	
	      
		
				
	

		public OtherEventsParser(ArrayList<Evento> menuItems , String elementName,Context context,int tempo,int luogo,int anno1,int mese1,int giorno1){
			this.menuItems = menuItems;
			this.elementName = elementName;
			thisContext = context;
			pDialog = new ProgressDialog(context);
			tempo1=tempo;
			luogo1=luogo;
			anno=anno1;
			mese=mese1;
			giorno=giorno1;
			
			URL = AGGIORNAURL();
			
		}
		private String AGGIORNAURL() {
		String sito=null;
        if(tempo1==0)
        {
        	if(luogo1==0)
        		sito="http://www.residentadvisor.net/events.aspx?ai=171";
        	if(luogo1==1)
        		sito="http://www.residentadvisor.net/events.aspx?ai=52";
        	if(luogo1==2)
        		sito="http://www.residentadvisor.net/events.aspx?ai=172";
        }
        else 
        	if(tempo1==1)
        	{
        		Calendar gc = Calendar.getInstance();
        		   int giornooggi=gc.get(Calendar.DAY_OF_MONTH);
        		   int meseoggi=gc.get(Calendar.MONTH);
        		   int annooggi=gc.get(Calendar.YEAR);
        		   System.out.print(giornooggi+"/"+meseoggi+"/"+annooggi);
        		if(luogo1==0)
            		sito="http://www.residentadvisor.net/events.aspx?ai=171&v=month&mn="+meseoggi+"&yr="+annooggi+"&dy="+giornooggi;
            	if(luogo1==1)
            		sito="http://www.residentadvisor.net/events.aspx?ai=52&v=month&mn="+meseoggi+"&yr="+annooggi+"&dy="+giornooggi;
            	if(luogo1==2)
            		sito="http://www.residentadvisor.net/events.aspx?ai=172&v=month&mn="+meseoggi+"&yr="+annooggi+"&dy="+giornooggi;
            	Log.i("SITO:", sito.toString());
        	}
        	else
        		if(tempo1==2)
        		{
        			if(luogo1==0)
                		sito="http://www.residentadvisor.net/events.aspx?ai=171&v=day&mn="+mese+"&yr="+anno+"&dy="+giorno;
                	if(luogo1==1)
                		sito="http://www.residentadvisor.net/events.aspx?ai=52&v=day&mn="+mese+"&yr="+anno+"&dy="+giorno;
                	if(luogo1==2)
                		sito="http://www.residentadvisor.net/events.aspx?ai=172&v=day&mn="+mese+"&yr="+anno+"&dy="+giorno;
                	Log.i("SITO:", sito.toString());

        		}
		return sito;
	}
		protected void onPreExecute() {
	        super.onPreExecute();
//	        this.pDialog.setMessage("Progress start");
//	        this.pDialog.show();
	        
	       
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
		
		protected void onProgressUpdate(Integer... progress) {
	       Toast t = Toast.makeText(thisContext, "progress : "+progress[0], Toast.LENGTH_SHORT);
	       t.show();
	    }
		protected void onPostExecute(String result) {
			Evento nuovo;


			try{
				

				
				TagNode ul[] = rootNode.getElementsByName("ul", true);
				
				
				for(int x = 1 ; x < ul.length ; x++ ) {
					if(ul[x].hasAttribute("id")) {
						
						if( ul[x].getAttributeByName("id").compareTo("items") == 0) {
							//allora il tag ul ha id items e ha al suo interno la lista di items
							TagNode li[] = ul[x].getElementsByName("li", true);
							
							
							for(int liNumber = 1; liNumber < li.length ; liNumber ++) 
							{
								if(li[liNumber].getElementsByAttValue("class","event-item clearfix", true, true).length  != 0)
								{
									
									nuovo = new Evento();
									
									nuovo.setDataText(li[liNumber].getElementsByName("time", true)[0].getText().toString());
									nuovo.setSrcImgSmall("http://www.residentadvisor.net"+li[liNumber].getElementsByName("img", true)[0].getAttributeByName("src"));
									TagNode h1[] = li[liNumber].getElementsByName("h1", true);
									nuovo.setSmallDescription(h1[0].getElementsByName("span", true)[0].getText().toString());
									nuovo.setHref("http://www.residentadvisor.net"+h1[0].getElementsByName("a", true)[0].getAttributeByName("href"));
									nuovo.setNome(h1[0].getElementsByName("a", true)[0].getText().toString());
									menuItems.add(nuovo);
								}
								
								
								
							}
							if(menuItems.isEmpty())
								Toast.makeText(thisContext, "NON SONO PRESENTI EVENTI!" , 
										   Toast.LENGTH_LONG).show();
						}
						
					}
						
				} // FINE FOR PRINCIPALE 
				
				
				

				
				
				
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			 if (pDialog.isShowing())
	             pDialog.dismiss();
		} 

		
	}
