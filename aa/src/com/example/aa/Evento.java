package com.example.aa;

import java.io.IOException;
import java.net.URL;
import java.util.GregorianCalendar;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
		 
		try {
			URL imageurl = new URL(srcImgSmall);
			Bitmap bitmap = BitmapFactory.decodeStream(imageurl.openConnection().getInputStream());
			this.setImgBtmSmall(bitmap);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getSrcImgBig() {
		return srcImgBig;
	}
	public void setSrcImgBig(String srcImgBig) {
		this.srcImgBig = srcImgBig;
		
		try {
			URL imageurl = new URL(srcImgBig);
			this.setImgBtmBig(BitmapFactory.decodeStream(imageurl.openConnection().getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
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
	public CharSequence getNome(){
		CharSequence nomechar=(CharSequence)nome;
		return nomechar;
	}
	public CharSequence getLuogo(){
		CharSequence nomechar=(CharSequence)luogo;
		return nomechar;
	}
}
