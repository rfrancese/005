package com.example.aa;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.GregorianCalendar;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;

public class Evento {
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
	private Drawable imageSmall;
	private Drawable imageBig;
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
		this.srcImgSmall = srcImgSmall;
		imageSmall = loadImageFromWebOperations(srcImgSmall);
		 
		
	}
	public String getSrcImgBig() {
		return srcImgBig;
	}
	public void setSrcImgBig(String srcImgBig) {
		this.srcImgBig = srcImgBig;
		
		imageBig = loadImageFromWebOperations(srcImgBig);
		
		
	}
	public Drawable getImageSmall() {
		return imageSmall;
	}
	
	public Drawable getImageBig() {
		return imageBig;
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
	
	private Drawable loadImageFromWebOperations(String url) 
	{ 
		try 
		{ 
			InputStream is = (InputStream) new URL(url).getContent(); 
			Drawable d = Drawable.createFromStream(is, "src name"); 
			return d; 
		}catch (Exception e) { 
			e.printStackTrace();
			return null; 
		} 
	} 
	public static Bitmap getBitmapFromURL() {
	    try {
	   /*     URL url = new URL(src); */
	        URL url=getUrlImg();
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setDoInput(true);
	        connection.connect();
	        InputStream input = connection.getInputStream();
	        Bitmap myBitmap = BitmapFactory.decodeStream(input);
	        return myBitmap;
	    } catch (IOException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	public void setUrlImg(URL s){
		urlImgBig=s;
	}
	public static URL getUrlImg(){
		return urlImgBig;
	}
}
