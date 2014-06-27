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

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

public class EventsParser extends AsyncTask<Void, Void, String> {
   
	private ProgressDialog pDialog;
	private ArrayList<Evento> menuItems;
	private TagNode rootNode;
	private String URL = "http://www.residentadvisor.net/events.aspx?ai=172" ; 
	private String elementName;
	private Context thisContext;

	public EventsParser(ArrayList<Evento> menuItems , String elementName,Context context){
		this.menuItems = menuItems;
		this.elementName = elementName;
		thisContext = context;
		pDialog = new ProgressDialog(context);
	}
	protected void onPreExecute() {
        super.onPreExecute();
        this.pDialog.setMessage("Progress start");
        this.pDialog.show();
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
							if(li[liNumber].getElementsByName("p", true).length == 0 ) {
								
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
