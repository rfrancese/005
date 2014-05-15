package com.example.aa;

import java.util.GregorianCalendar;

import android.media.Image;

public class Evento {
	private String luogo;
	private String nome;
	private GregorianCalendar data;
	private Image image;
   public Evento(String name,String luogo1,GregorianCalendar data1,Image image1){
	  nome=name;
	  luogo=luogo1;
	  data=data1;
	  image=image1;
	  
	   
   }
   public Evento(){
		  nome=null;
		  luogo=null;
		  data=null;
		  image=null;
		 }
   public void setLuogo(String luogo1){
	   this.luogo=luogo1;
   }
   public void setNome(String nome1){
	   this.nome=nome1;
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
   public void setImage(Image image1){
	   image=image1;
   }
   public Image getImage(){
	   return this.image;
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
