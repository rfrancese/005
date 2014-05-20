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
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

public class EventsParser extends AsyncTask<Void, Void, String> {

	private ArrayList<Evento> menuItems;
	private TagNode rootNode;
	private String URL = "http://www.residentadvisor.net/events.aspx?ai=172" ; 
	private String elementName;
	private Context thisContext;

	public EventsParser(ArrayList<Evento> menuItems , String elementName,Context context){
		this.menuItems = menuItems;
		this.elementName = elementName;
		thisContext = context;
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
	
	protected void onProgressUpdate(Integer...integers){
		
	}
	protected void onPostExecute(String result) {
		Evento nuovo;


		try{
			

			TagNode Elements[] = rootNode.getElementsByName(elementName, true);
			TagNode time[] = rootNode.getElementsByName("time", true);
			int z=0;
			for (int i = 0; Elements != null && i < Elements.length; i++)
			{
				nuovo = new Evento();

				
				TagNode x = Elements[i];
				if(x.hasAttribute("title") && x.hasAttribute("href")) {
					nuovo.setNome(x.getText().toString());
					nuovo.setHref("http://www.residentadvisor.net"+x.getAttributeByName("href"));
					nuovo.setDataText(time[z].getText().toString().substring(0, 10));
					z++;
					menuItems.add(nuovo);					
				}
				

			}

			
			
		
			for(int n = 0 ; n < menuItems.size() ; n++) {
				for (int i = 0; Elements != null && i < Elements.length; i++) {
					TagNode  y = Elements[i];
					if(y.hasAttribute("href") && (!y.hasAttribute("title"))){
						if(y.getAttributeByName("href").equals(menuItems.get(n).getHref())) {
							String img_src = y.getElementsByName("img", true)[0].getAttributeByName("src");
							menuItems.get(n).setSrcImgSmall("http://www.residentadvisor.net"+img_src);
							menuItems.get(n).setNome(img_src);
							String img_src_big ="http://www.residentadvisor.net"+img_src.replaceFirst("-list.jpg", "-0-front.jpg");
							menuItems.get(n).setSrcImgBig(img_src_big);
							menuItems.get(n).setNome(menuItems.get(n).getSrcImgSmall());
							menuItems.get(n).setDataText("ciaopooooo");		
						}
						
						
					}
				}
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

	} 

	
}
