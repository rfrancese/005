package com.example.aa;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;


import android.util.Log;

public class HtmlParser {
	
	// oggetto TagNode radice del file html
	private TagNode rootNode;
	private String url_str = null;
	ArrayList<Evento> eventi=new ArrayList<Evento>();
    Evento nuovo = null;
    
	public HtmlParser(String url_str1)
	{
		//URL di cui si vuole fare il parsing

		this.url_str = url_str1;
		
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
			
			url = new URL(url_str);
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
		
	}
	
	
	ArrayList<Evento> getElement(String elementName)
    {
        ArrayList<Evento> elementList = new ArrayList<Evento>();

        TagNode Elements[] = rootNode.getElementsByName(elementName, true);
        for (int i = 0; Elements != null && i < Elements.length; i++)
        {

            String type = Elements[i].getAttributeByName("title");
            if ( type != null )
            {
            	
            		 nuovo=new Evento();
				     nuovo.setNome(type);
                elementList.add(nuovo);
            }

        }
    
        return elementList;
    }
	
	public ArrayList<Evento> Stampa(String elementName)
    {
    	StringBuffer sb = new StringBuffer();
    	ArrayList<StringBuffer> a=new ArrayList<StringBuffer>();
        try
        {
           
            ArrayList<Evento> elementi = this.getElement(elementName);

           /* sb.append(">>> Stampa contenuto degli elementi '"+elementName+"' per il sito '"+url_str+"'\n");
            TagNode el;
            Iterator<Object> iterator = elementi.iterator();
            while(iterator.hasNext()) {
                 el = (TagNode) iterator.next();
                 sb.append("EVENTO: " + el.getText() + "\n");
                 a.add(el.getText()); 
            }
            
            return sb.toString(); */
            return elementi;
       } 
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return null;
    }

	
}
