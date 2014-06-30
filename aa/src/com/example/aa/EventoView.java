package com.example.aa;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;

import com.example.aa.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Event;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class EventoView extends Fragment {

private ImageView im;
private TextView titolo;
private TextView descr;
private TextView cost;
private TextView ind;
private Button b1;
private Button b2;
private Evento p1;
private Evento event;

final String NAME_XPATH = "//div[@class='null']/div[@class='clearfix']/h1";

	public void onActivityCreated(Bundle savedInstanceState) {
	    super.onActivityCreated(savedInstanceState);
	    
	  }

	  @Override
	
	
      public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		  View v= inflater.inflate(R.layout.eventoview, container, false);
    	  im=(ImageView) v.findViewById(R.id.imageView1);
    	  titolo=(TextView) v.findViewById(R.id.descr1);
    	  descr = (TextView)  v.findViewById(R.id.titolo1);
    	  cost=(TextView)  v.findViewById(R.id.costo);
    	  ind=(TextView)  v.findViewById(R.id.indirizzo);
    	  b1=(Button) v.findViewById(R.id.button1);
    	  b2=(Button) v.findViewById(R.id.button2);
    	  
    	  event=new Evento();
    	  Bundle bundle = getArguments();
    	  String href=bundle.getString("href");
    	  EventsDescriptionParser p = new EventsDescriptionParser(event,href,getActivity());
			p.execute();
		/*	 */
			b1.setOnClickListener(new View.OnClickListener() {
				  public void onClick(View view) { 
					  Intent intent = new Intent(android.content.Intent.ACTION_VIEW, 
							    Uri.parse("http://maps.google.com/maps?daddr="+event.getIndi()));
							startActivity(intent);
				  }
				});
			
			b2.setOnClickListener(new View.OnClickListener() {
				  public void onClick(View view) { 
					  Intent i = new Intent(Intent.ACTION_SEND);
						i.setType("text/plain");
						i.putExtra(Intent.EXTRA_TEXT,"EVENT FROM CLUBADVISOR APP \n"+  event.getNome().toUpperCase()+"\n Dettagli: "+event.getBigDescription() +"\n Indirizzo: "+event.getAddress()+"\n Prezzo: "+event.getCost());
						startActivity(Intent.createChooser(i, "Condividi"));
				  }
				});
			im.setOnClickListener(new View.OnClickListener() {

		          @Override
		          public void onClick(View v) {
		        	  Intent intent = new Intent();
		              intent.setAction(Intent.ACTION_VIEW);
		              intent.addCategory(Intent.CATEGORY_BROWSABLE);
		              intent.setData(Uri.parse(event.getSrcImgBig()));
		              startActivity(intent);
 		          }

		        });   
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
		    private ProgressDialog pDialog;

		   
		    
		    public EventsDescriptionParser(Evento event,Context cont ){
		    	this.event = event;
		    	URL = event.getHref();
		    	this.cont = cont;
		    	pDialog = new ProgressDialog(cont);
		    }
		    public EventsDescriptionParser(Evento e,String href,Context cont ){
		    	
		    	URL = href;
		    	this.cont = cont;
		    	event=e;
		    	pDialog = new ProgressDialog(cont);
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
			   protected void onPostExecute(String result) {
				    
				

						try{
						      	
						
						      	// Si prende il titolo e funziona 
						         Object[] name_event=rootNode.evaluateXPath("/body/header/div[@id='navContainer']/div[@id='sectionHead']/h1");
						         for(int x = 0 ; x< name_event.length;x++){
						         if (name_event.length > 0) {
						             // CASTED TO A TAGNODE
						             TagNode info_node = (TagNode) name_event[x];
						             // HOW TO RETRIEVE THE CONTENTS AS A STRING
						             String info = info_node.getText().toString();
						             event.setNome(info);
						         	}
						         
						         }
						         
						         
						         Object[] costo_event=rootNode.evaluateXPath("/body/main/ul[@class='content-list']/li[@class='nobreak']/section[@class='contentDetail clearfix']/div[@class='clearfix']/div[@class='col4-6 small']/aside[@class='clearfix']/ul[@class='clearfix']/li/");
						         for(int x = 0 ; x< costo_event.length;x++){
						         if (costo_event.length > 0) {
						             // CASTED TO A TAGNODE
						        	
						             TagNode info_node = (TagNode) costo_event[x];
						             // HOW TO RETRIEVE THE CONTENTS AS A STRING
						           
						           String info=info_node.getText().toString();
						          if( info.startsWith("Cost /", 0)){
						        	  info.replace("Cost /", "");
						           event.setCosto(info);
						          }
						            
						         }
						         }
						         Object[] address_event=rootNode.evaluateXPath("/body/main/ul[@class='content-list']/li[@class='nobreak']/section[@class='contentDetail clearfix']/div[@class='clearfix']/div[@class='col4-6 small']/aside[@class='clearfix']/ul[@class='clearfix']/li[@class='wide']");
						         for(int x = 0 ; x< address_event.length;x++){
						         if (address_event.length > 0) {
						             // CASTED TO A TAGNODE
						             TagNode info_node = (TagNode) address_event[x];
						             // HOW TO RETRIEVE THE CONTENTS AS A STRING
						           
						           String info=info_node.getText().toString();
						           if( info.startsWith("Venue /", 0)){
						        	   
						        	   String newS = info.substring(7);
						        	   event.setAddress(newS);
						        	   
						        	   int index = newS.indexOf("Via");
						        	String  ini = newS.substring(index);
						        	   ini.trim();
						        	   event.setIndi(ini);
						           }
						            
						         }
						         }
						         Object[] foto_event=rootNode.evaluateXPath("/body/main/ul[@class='content-list']/li[@class='alt']/div[@class='content clearfix']/div[@id='event-item']/div[@class='flyer']/a/img");
						         for(int x = 0 ; x< foto_event.length;x++){
						         if (foto_event.length > 0) {
						             // CASTED TO A TAGNODE
						             TagNode info_node = (TagNode) foto_event[x];
						             // HOW TO RETRIEVE THE CONTENTS AS A STRING
						           
						           String info="http://www.residentadvisor.net"+info_node.getAttributeByName("src").toString();
						           
						           event.setSrcImgBig(info);
						           GetXMLTask2 task = new GetXMLTask2();
						           task.execute(info);

						            
						         }
						         }

						      titolo.setText("DESCRIZIONE: "+event.getBigDescription()+"\n");
								cost.setText(event.getCosto()+"\n");
								ind.setText(event.getAddress()+"\n");
						      descr.setText(event.getNome()+"\n");

						      
								
				        	  
				        	  
								

						}
						 catch(Exception e)
				        {
				            e.printStackTrace();
				        }
						 if (pDialog.isShowing())
				             pDialog.dismiss();
							
			    }

	  }
	  private class GetXMLTask2 extends AsyncTask<String, Void, Bitmap[]> {

	        @Override
	       
	        protected Bitmap[] doInBackground(String... urls) {
	        	
	            Bitmap map[] = new Bitmap[1];
	            for (String url : urls) {
	                map[0] = downloadImage(url);
	                
	              
	            }
	            return map;
	        }
	 
	       
	        @Override
	        protected void onPostExecute(Bitmap[] result) {
	        	
	        	
	        	im.setImageBitmap(result[0]);
	        	
	        }
	 
	        // Creates Bitmap from InputStream and returns it
	        private Bitmap downloadImage(String url) {
	            Bitmap bitmap = null;
	            InputStream stream = null;
	            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
	            bmOptions.inSampleSize = 1;
	 
	            try {
	                stream = getHttpConnection(url);
	                bitmap = BitmapFactory.
	                        decodeStream(stream, null, bmOptions);
	                stream.close();
	            } catch (IOException e1) {
	                e1.printStackTrace();
	            }
	          
	            return bitmap;
	        }
	 
	        // Makes HttpURLConnection and returns InputStream
	        private InputStream getHttpConnection(String urlString)
	                throws IOException {
	            InputStream stream = null;
	            URL url = new URL(urlString);
	            URLConnection connection = url.openConnection();
	 
	            try {
	                HttpURLConnection httpConnection = (HttpURLConnection) connection;
	                httpConnection.setRequestMethod("GET");
	                httpConnection.connect();
	 
	                if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
	                    stream = httpConnection.getInputStream();
	                }
	            } catch (Exception ex) {
	                ex.printStackTrace();
	            }
	            return stream;
	        }
	    }
}
