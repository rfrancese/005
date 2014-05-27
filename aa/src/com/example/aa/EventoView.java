package com.example.aa;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;

import com.example.aa.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Event;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class EventoView extends Fragment {
private ImageView im;
private TextView titolo;
private TextView descr;
private Evento p1;
private Evento event;
public ProgressDialog pDialog;


	public void onActivityCreated(Bundle savedInstanceState) {
	    super.onActivityCreated(savedInstanceState);
	    
	  }

	  @Override
	
	
      public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		  View v= inflater.inflate(R.layout.eventoview, container, false);
    	  im=(ImageView) v.findViewById(R.id.imageView1);
    	  titolo=(TextView) v.findViewById(R.id.descr1);
    	  descr = (TextView)  v.findViewById(R.id.titolo1);
    	  event=new Evento();
    	  Bundle bundle = getArguments();
    	  String href=bundle.getString("href");
    	  EventsDescriptionParser p = new EventsDescriptionParser(event,href,getActivity());
			p.execute();
		/*	 */
		    
    	  return v;
      }
	  public void onStart(){
		  super.onStart();
		 
	  }
	  public void onAttach(Activity activity){
			super.onAttach(activity);

		}
      
    
	  class EventsDescriptionParser extends AsyncTask<Void, Void, String> {
			
			private Evento event = null;
		    private TagNode rootNode;
		    private String URL; 
		    private Context cont;
		   
		    
		    public EventsDescriptionParser(Evento event,Context cont ){
		    	this.event = event;
		    	URL = event.getHref();
		    	this.cont = cont;
		    }
		    public EventsDescriptionParser(Evento e,String href,Context cont ){
		    	
		    	URL = href;
		    	this.cont = cont;
		    	event=e;
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
						      	
//						      	for(int i = 0 ; liElements!=null && i < liElements.length ; i++){
//						      		
//					      			
//						      		TagNode divElem[] = liElements[i].getElementsByName("div", true);
//						      		
//						      		if(divElem[0].getText().toString().equals("Date /")) {
//						      			event.setDataText(liElements[i].getText().toString()+"CIAO");
//						      			
//						      			
//						      		}
//						      		if(divElem[0].getText().equals("Venue /")) {
//						      			event.setAddress(liElements[i].getText().toString()+"CIAO");
//						      			
//						      		}
//						      		if(divElem[0].getText().equals("Cost /")) {
//						      			event.setCosto(liElements[i].getText().toString());
//						      			Toast r = Toast.makeText(cont, "Entrato nel costo", Toast.LENGTH_SHORT);
//						      			r.show();
//						      		}
//						      		if(divElem[0].getText().equals("Promoters /")) {
//						      			event.setCosto(liElements[i].getText().toString());
//						      		}
//						      	}
						
						     TagNode pElements[] = rootNode.getElementsByName("p",true);
						      String allDescription="";
						      
						      for(int x = 0 ; x< pElements.length;x++){
						    	 
						    	  if(!(pElements[x].hasAttribute("class"))) {
						    		  
						    		  String description = pElements[x].getText().toString();
					    		  
						    		  if(description.length() > 0) {
						    			  event.setBigDescription(description);
						    		  }
						    	  }
						      }
						      

						      titolo.setText("TITOLOOO: "+event.getNome());
								descr.setText("DESCRIZIONE: "+event.getBigDescription());
				        	  
				        	  
				        	   

						}
						 catch(Exception e)
				        {
				            e.printStackTrace();
				        }
							
			    }

	  }
}
