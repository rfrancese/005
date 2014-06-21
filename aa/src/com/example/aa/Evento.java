package com.example.aa;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.GregorianCalendar;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.AsyncTask;

public class Evento {
	private String nav;
	private String luogo;
	private String nome;
	private GregorianCalendar data;
	private String data_text;
	private Bitmap imgBtmSmall;
	private Bitmap imgBtmBig;
	private String href;
	private String costo ;
	private String address;
	private String promoters;
	private String srcImgSmall;
	private String srcImgBig;
	private String bigDescription;
	private String lineUp;
	private String smallDesc;
	private int position; 
	private static URL urlImgBig;
	
	
	
	
	public Evento(String name,String luogo1,GregorianCalendar data1){
		nome=name;
		luogo=luogo1;
		data=data1;



	}
	public Evento(){
		nome=null;
		luogo=null;
		data=null;

	}
	public void setCost(String cost){
		this.costo=cost;
	}
	public String getCost(){
		return costo;
	}
	
	public String getSmallDescription(){
		return smallDesc;
	}
	public void setSmallDescription(String bd){
		this.smallDesc = bd;
	}
	public String getBigDescription(){
		return bigDescription;
	}
	public void setBigDescription(String bd){
		this.bigDescription = bd;
	}
	public String getDataString() {
		return data_text;
	}
	public void setDataText(String d){
		data_text = d;
	}
	public String getCosto() {
		return costo;
	}
	public void setCosto(String costo) {
		this.costo = costo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPromoters() {
		return promoters;
	}
	public void setPromoters(String promoters) {
		this.promoters = promoters;
	}
	public String getSrcImgSmall() {
		
		return srcImgSmall;
	}
	public void setSrcImgSmall(String srcImgSmall) {
		GetXMLTask task = new GetXMLTask();
		task.execute(srcImgSmall);
		this.srcImgSmall = srcImgSmall;
		
		 
		
	}
	public String getSrcImgBig() {
		return srcImgBig;
	}
	public void setSrcImgBig(String srcImgBig) {
		this.srcImgBig = srcImgBig;
		
		
		
		
	}
	public void setIndi(String s){
		nav=s;
	}
	public String getIndi(){
		return nav;
	}
	

	public void setHref(String href) {
		this.href = href;

	}
	public void setLuogo(String luogo1){
		this.luogo=luogo1;
	}
	public void setNome(String nome1){
		this.nome=nome1;
	}
	public String getHref(){
		return href;
	}
	public String getNome1(){
		return this.nome;
	}
	public String getLuogo1(){
		return this.luogo;
	}
	public void setData(GregorianCalendar data1){
		data=data1;
	}

	public Bitmap getImgBtmSmall() {
		return imgBtmSmall;
	}
	public void setImgBtmSmall(Bitmap imgBtmSmall) {
		this.imgBtmSmall = imgBtmSmall;
		
	}
	public Bitmap getImgBtmBig() {
		return imgBtmBig;
	}
	public void setImgBtmBig(Bitmap imgBtmBig) {
		this.imgBtmBig = imgBtmBig;
	}
	public GregorianCalendar getData(){
		return this.data;
	}
	public String getNome(){
		
		return nome;
	}
	public CharSequence getLuogo(){
		CharSequence nomechar=(CharSequence)luogo;
		return nomechar;
	}
	
	
	
	public void setUrlImg(URL s){
		urlImgBig=s;
	}
	public static URL getUrlImg(){
		return urlImgBig;
	}
	
	
	
	
	private class GetXMLTask extends AsyncTask<String, Void, Bitmap[]> {

        @Override
       
        protected Bitmap[] doInBackground(String... urls) {
        	
            Bitmap map[] = new Bitmap[2];
            for (String url : urls) {
                map[0] = downloadImage(url);
                String img_src_big =url.replaceFirst("-list.jpg", "-front.jpg");
                map[1] = downloadImage(img_src_big);
            }
            return map;
        }
 
        // Sets the Bitmap returned by doInBackground
        @Override
        protected void onPostExecute(Bitmap[] result) {
        	
        	setImgBtmSmall(result[0]);
        	setImgBtmBig(result[1]);
        	
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
