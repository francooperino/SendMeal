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

    public class PlatoViewHolder extends RecyclerView.ViewHolder {
        CardView card;
        ImageView imgPlato;
        TextView tituloPlato;
        TextView precioPlato;
        TextView descPlato;
        TextView calorias;
        Button btnElegirPlato;

        public PlatoViewHolder(View itemView) {
            super(itemView);
            card=itemView.findViewById(R.id.cardPlato);
            imgPlato=itemView.findViewById(R.id.fotoComida);
            tituloPlato=itemView.findViewById(R.id.TituloPlato);
            precioPlato=itemView.findViewById(R.id.PrecioPlato);
            btnElegirPlato = itemView.findViewById(R.id.buttonPedir);
            calorias= itemView.findViewById(R.id.caloriasPlato);
            descPlato= itemView.findViewById(R.id.descripcionPlato);
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
        holder.descPlato.setTag(position);
        holder.calorias.setTag(position);
        holder.precioPlato.setTag(position);
        holder.tituloPlato.setTag(position);
        Plato plato = mDataset.get(position);
        holder.imgPlato.setImageResource(R.drawable.milanga);
        holder.tituloPlato.setText(plato.getTitulo());
        holder.precioPlato.setText("Precio: $"+plato.getPrecio().toString());

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
