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
	
	protected void onProgressUpdate(Integer... progress) {
       Toast t = Toast.makeText(thisContext, "progress : "+progress[0], Toast.LENGTH_SHORT);
       t.show();
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

			
			
			
		ArrayList<String> allSmallDes = new ArrayList<String>();
		
		
		TagNode h1[] = rootNode.getElementsByName("h1", true);
		for(int s = 0; s < h1.length; s++ ) {
			if(  !(h1[s].hasAttribute("imteprop"))) {
				TagNode span[]=h1[s].getElementsByName("span",true);
				if(span.length!=0) {
//					Toast t=Toast.makeText(thisContext,span[0].getText().toString() , Toast.LENGTH_SHORT);
//					t.show();
					allSmallDes.add(span[0].getText().toString());
					
				}
				
			}
		}
		
		Toast t=Toast.makeText(thisContext, "menuItemsSize :"+menuItems.size()+" SmallDescriptionSize:"+allSmallDes.size(), Toast.LENGTH_SHORT);
		t.show();
		
		for(int z1=0; z1 < allSmallDes.size();z1++) menuItems.get(z1).setSmallDescription(allSmallDes.get(z1));
		
		
		
			for(int n = 0 ; n < menuItems.size() ; n++) {
		
				TagNode pic[] = rootNode.getElementsByName("img", true);
				
				
				for (int i = 0; pic != null && i < pic.length; i++) {
					String img_src ="http://www.residentadvisor.net"+ pic[i].getAttributeByName("src").toString();
					menuItems.get(i).setSrcImgSmall(img_src);
					String img_src_big =img_src.replaceFirst("-list.jpg", "-0-front.jpg");
					menuItems.get(i).setSrcImgBig(img_src_big);
					
				}

			}
			
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

	} 

	
}
