package com.example.aa;

import java.util.ArrayList;
import java.util.List;

import com.example.aa.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomArrayAdapter extends ArrayAdapter<Evento> {
    Context context;
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
        ViewHolder holder = null; 
       Evento rowItem = getItem(position); 
        
        
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_row, null);
            holder = new ViewHolder(); 
         holder.txtMenuName = (TextView) convertView.findViewById(R.id.menu_name);
          holder.txtMenuName.setText(rowItem.getNome());
          holder.imageView = (ImageView) convertView.findViewById(R.id.list_image);
          holder.imageView.setImageBitmap(rowItem.getImgBtmSmall());
          holder.txtDate = (TextView) convertView.findViewById(R.id.data);
          holder.txtDate.setText(rowItem.getDataString());
          holder.txtMenuDesc = (TextView) convertView.findViewById(R.id.description);
          holder.txtMenuDesc.setText(rowItem.getAddress());
          convertView.setTag(holder);

      /*   
            
           
          /*  convertView.setTag(holder);  */
        } else{
       /*     holder = (ViewHolder) convertView.getTag(); */
            holder = (ViewHolder) convertView.getTag();

        holder.txtMenuName.setText(rowItem.getNome()+"CIAO");
        /*
        holder.txtMenuDesc.setText(rowItem.getLuogo());
        
        holder.txtDate.setText(String.valueOf(rowItem.getData()) + " TL"); */
        //holder.imageView.setImageResource(rowItem.getImageId());
        }
        return convertView;
    }

}