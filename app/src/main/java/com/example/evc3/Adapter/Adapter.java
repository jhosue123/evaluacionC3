package com.example.evc3.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.evc3.R;
import com.example.evc3.Modelo.Rick;
import com.example.evc3.Views.DetailActivity;
import java.util.ArrayList;


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{

    private ArrayList<Rick> dataset;
    private Context context;


    public Adapter(ArrayList<Rick> rick, Context context){

        this.context=context;
        this.dataset=rick;

    }


    // metodo para inflar la vista de item rick
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view=LayoutInflater.from( viewGroup.getContext()).inflate( R.layout.item_rick,viewGroup, false);
        return  new ViewHolder(view);
    }

    //metodo para ver los detalles de las tarjetas
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Rick p=dataset.get(position);
        holder.nombreTextView.setText(String.valueOf(p.getName()));
        holder.idTextView.setText(String.valueOf(p.getId()));
        String url = p.getImage();
        Glide.with(context)
                .load(url)
                .placeholder(R.drawable.ic_launcher_background)
                 .into(holder.fotoImagenView);


        String nombre = p.getName();
        String estado = p.getStatus();
        String species = p.getSpecies();
        String id = p.getId();
        String gender = p.getGender();

        holder.carta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, DetailActivity.class);
                i.putExtra("Nombre", nombre);
                i.putExtra("Estado", estado);
                i.putExtra("Especie",species);
                i.putExtra("Id",id);
                i.putExtra("Gender",gender);
                i.putExtra("Imagen", p.getImage());
                context.startActivity(i);
            }
        });
    }
    //metodo para mostrar las tarjetas en el menu e instanciar las variables de las tarjetas
    @Override
    public int getItemCount()  {

        return dataset.size();
    }

        public static  class ViewHolder extends RecyclerView.ViewHolder{
             ImageView fotoImagenView;
             TextView nombreTextView;
             TextView idTextView;
             CardView tarjetas;
             LinearLayout carta;

            public ViewHolder(View itemView){
                super(itemView);
                fotoImagenView=itemView.findViewById(R.id.fotoImagenView);
                nombreTextView=itemView.findViewById(R.id.nombreTextView);
                idTextView=itemView.findViewById(R.id.idTextView);
                tarjetas=itemView.findViewById(R.id.tarjetas);
                carta=itemView.findViewById(R.id.carta);
            }
        }
    }


