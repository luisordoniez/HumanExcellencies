package com.demo.luisordoniez.humanapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.luisordoniez.humanapp.R;
import com.demo.luisordoniez.humanapp.models.Usuario;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * Created by luisordoniez on 8/03/17.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>  {

    /**
     * Atributos
     */

    public ArrayList<Usuario> list;
    Context context;

    // Pass in the contact array into the constructor
    public RecyclerViewAdapter(ArrayList<Usuario> list) {
        this.list = list;
    }


    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder viewHolder, int position) {
        // Set item views based on the data model
        ImageView imagen = viewHolder.imagen;
        TextView nombre = viewHolder.nombre;
        TextView edad = viewHolder.edad;
        TextView email = viewHolder.email;

        nombre.setText(list.get(position).getNombre());
        edad.setText(list.get(position).getEdad());
        email.setText(list.get(position).getEmail());
        Picasso.with(context).load("asdsad").error(R.drawable.ic_no_image_gray).fit().into(viewHolder.imagen);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public ImageView imagen;
        public TextView nombre;
        public TextView edad;
        public TextView email;


        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            imagen = (ImageView) itemView.findViewById(R.id.idImage);
            nombre = (TextView) itemView.findViewById(R.id.nombre);
            edad = (TextView) itemView.findViewById(R.id.edad);
            email = (TextView) itemView.findViewById(R.id.email);



//            texto.setOnClickListener(this);
//            imagen.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
//            int position = getLayoutPosition(); // gets item position
//
//            Intent myIntent = new Intent();
//            myIntent.setClass(context, Detail.class);
//            Bundle myBundle = new Bundle();
//            myBundle.putSerializable("data", list.get(position));
//            myIntent.putExtras(myBundle);
//            context.startActivity(myIntent);
        }
    }
}
