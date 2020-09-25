package com.example.lab1appmoviles;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PlatoRecyclerAdapter extends RecyclerView.Adapter<PlatoRecyclerAdapter.PlatoViewHolder> {
    private List<Plato> mDataset;
    private AppCompatActivity activity;

    public class PlatoViewHolder extends RecyclerView.ViewHolder {
        CardView card;
        ImageView imgPlato;
        TextView tituloPlato;
        TextView precioPlato;



        public PlatoViewHolder(View itemView) {
            super(itemView);
            card=itemView.findViewById(R.id.cardPlato);
            imgPlato=itemView.findViewById(R.id.fotoComida);
            tituloPlato=itemView.findViewById(R.id.TituloPlato);
            precioPlato=itemView.findViewById(R.id.PrecioPlato);
        }
    }

    public PlatoRecyclerAdapter(List<Plato> list, PlatoRecyclerActivity platoRecyclerActivity) {
        mDataset = list;
        activity = platoRecyclerActivity;
    }

    @Override
    public PlatoRecyclerAdapter.PlatoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila_plato, parent, false);
        PlatoViewHolder  vh = new PlatoViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(PlatoViewHolder holder, int position) {
        holder.precioPlato.setTag(position);
        holder.tituloPlato.setTag(position);
        Plato plato = mDataset.get(position);
        holder.imgPlato.setImageResource(R.drawable.milanga);
        holder.tituloPlato.setText(plato.getTitulo());
        holder.precioPlato.setText(plato.getPrecio().toString());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
