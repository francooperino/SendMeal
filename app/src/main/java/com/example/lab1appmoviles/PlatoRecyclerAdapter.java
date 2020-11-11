package com.example.lab1appmoviles;

import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
    String  estadoBtn;
    Plato plato;

    public class PlatoViewHolder extends RecyclerView.ViewHolder {
        CardView card;
        ImageView imgPlato;
        TextView tituloPlato;
        TextView precioPlato;
        TextView descPlato;
        TextView calorias;
        Button btnElegirPlato;
        TextView etiquetaPrecio;

        public PlatoViewHolder(View itemView) {
            super(itemView);
            card=itemView.findViewById(R.id.cardPlato);
            imgPlato=itemView.findViewById(R.id.fotoComida);
            tituloPlato=itemView.findViewById(R.id.TituloPlato);
            precioPlato=itemView.findViewById(R.id.PrecioPlato);
            btnElegirPlato = itemView.findViewById(R.id.buttonPedir);
            calorias= itemView.findViewById(R.id.caloriasPlato);
            descPlato= itemView.findViewById(R.id.descripcionPlato);
            etiquetaPrecio=itemView.findViewById(R.id.etiquetaPrecio);
        }
    }

    public PlatoRecyclerAdapter(List<Plato> list, PlatoRecyclerActivity platoRecyclerActivity, String valor) {
        mDataset = list;
        activity = platoRecyclerActivity;
        estadoBtn = valor;
    }

    @Override
    public PlatoRecyclerAdapter.PlatoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila_plato, parent, false);
        PlatoViewHolder  vh = new PlatoViewHolder(v);
        return vh;
    }
    @Override
    public void onBindViewHolder(final PlatoViewHolder holder, int position) {
        holder.etiquetaPrecio.setTag(position);
        holder.descPlato.setTag(position);
        holder.calorias.setTag(position);
        holder.precioPlato.setTag(position);
        holder.tituloPlato.setTag(position);
        plato = mDataset.get(position);
        holder.imgPlato.setImageResource(R.drawable.milanga);
        holder.tituloPlato.setText(plato.getTitulo());
        holder.precioPlato.setText(plato.getPrecio().toString());
        holder.descPlato.setText(plato.getDescripcion());
        holder.calorias.setText(plato.getCalorias().toString());
        if(estadoBtn.equals("true")){
            holder.btnElegirPlato.setVisibility(1);
        }
        holder.btnElegirPlato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("titulo",holder.tituloPlato.getText().toString());
                returnIntent.putExtra("descripcion",holder.descPlato.getText().toString());
                returnIntent.putExtra("precio",holder.precioPlato.getText().toString());
                returnIntent.putExtra("calorias",holder.calorias.getText().toString());
                /*returnIntent.putExtra("titulo", plato.getTitulo());
                returnIntent.putExtra("descripcion", plato.getDescripcion());
                returnIntent.putExtra("precio", plato.getPrecio().toString());
                returnIntent.putExtra("calorias", plato.getCalorias().toString());*/
                activity.setResult(PedidoActivity.RESULT_OK,returnIntent);
                activity.finish();
            }
        });


    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


}
