package com.example.aa;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import com.example.aa.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomArrayAdapter extends ArrayAdapter<Evento> {
	Context context;
	ViewHolder holder;
	ArrayList<ViewHolder> v=new ArrayList<ViewHolder>();

	public CustomArrayAdapter(Context context, int textViewResourceId, ArrayList<Evento> objects) {
		super(context, textViewResourceId,objects);

		// TODO Auto-generated constructor stub
		this.context = context;
	}

	/*private view holder class*/
	private class ViewHolder {
		ImageView imageView;
		TextView txtMenuName;
		TextView txtMenuDesc;
		TextView txtDate;
	} 

	public View getView(int position, View convertView, ViewGroup parent) {
		holder = null; 
		Evento rowItem = getItem(position); 


		LayoutInflater mInflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.list_row, null);
			holder = new ViewHolder(); 
			holder.txtMenuName = (TextView) convertView.findViewById(R.id.menu_name);
			holder.txtMenuName.setText(rowItem.getNome());
			holder.imageView = (ImageView) convertView.findViewById(R.id.list_image);
	/*		 BitmapFactory.Options bmOptions;
			    bmOptions = new BitmapFactory.Options();
			    bmOptions.inSampleSize = 10;
			    Bitmap bm = LoadImage(rowItem.getSrcImgSmall(), bmOptions);

			 
			    holder.imageView.setImageBitmap(bm);
			    holder.imageView.setAdjustViewBounds(true);
			    holder.imageView.setMaxHeight(50);
			    holder.imageView.setMaxWidth(50);   */
			
		/*	holder.imageView.setImageDrawable(rowItem.loadImageFromWebOperations(rowItem.getSrcImgSmall())); */
			
			  // GetXMLTask task = new GetXMLTask();
		        // Execute the task
		       // task.execute(new String[] { rowItem.getSrcImgSmall() });
			         holder.txtDate = (TextView) convertView.findViewById(R.id.data);
			         holder.txtDate.setText(rowItem.getDataString());
			holder.txtMenuDesc = (TextView) convertView.findViewById(R.id.description);
			holder.txtMenuDesc.setText(rowItem.getSmallDescription());
			holder.imageView.setImageBitmap(rowItem.getImgBtmSmall());
			convertView.setTag(holder);

			/*   


          /*  convertView.setTag(holder);  */
		} else{
			/*     holder = (ViewHolder) convertView.getTag(); */
			holder = (ViewHolder) convertView.getTag();

			holder.txtMenuName.setText(rowItem.getNome());
			
       // holder.txtMenuDesc.setText(rowItem.getSmallDescription());
        /*
        holder.txtDate.setText(String.valueOf(rowItem.getData()) + " TL"); */
			//holder.imageView.setImageDrawable(rowItem.getImageBig());
		}
		v.add(holder);
		return convertView;
	}

	
//	 private class GetXMLTask extends AsyncTask<String, Void, Bitmap> {
//
//	        @Override
//	       
//	        protected Bitmap doInBackground(String... urls) {
//	        	
//	            Bitmap map = null;
//	            for (String url : urls) {
//	                map = downloadImage(url);
//	            }
//	            return map;
//	        }
//	 
//	        // Sets the Bitmap returned by doInBackground
//	        @Override
//	        protected void onPostExecute(Bitmap result) {
//	        	
//	        	holder.imageView.setImageBitmap(result);
//	        	
//	        }
//	 
//	        // Creates Bitmap from InputStream and returns it
//	        private Bitmap downloadImage(String url) {
//	            Bitmap bitmap = null;
//	            InputStream stream = null;
//	            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
//	            bmOptions.inSampleSize = 1;
//	 
//	            try {
//	                stream = getHttpConnection(url);
//	                bitmap = BitmapFactory.
//	                        decodeStream(stream, null, bmOptions);
//	                stream.close();
//	            } catch (IOException e1) {
//	                e1.printStackTrace();
//	            }
//	          
//	            return bitmap;
//	        }
//	 
//	        // Makes HttpURLConnection and returns InputStream
//	        private InputStream getHttpConnection(String urlString)
//	                throws IOException {
//	            InputStream stream = null;
//	            URL url = new URL(urlString);
//	            URLConnection connection = url.openConnection();
//	 
//	            try {
//	                HttpURLConnection httpConnection = (HttpURLConnection) connection;
//	                httpConnection.setRequestMethod("GET");
//	                httpConnection.connect();
//	 
//	                if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
//	                    stream = httpConnection.getInputStream();
//	                }
//	            } catch (Exception ex) {
//	                ex.printStackTrace();
//	            }
//	            return stream;
//	        }
//	    }
}