package com.example.lab1appmoviles;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.util.DisplayMetrics;
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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.List;
import java.util.UUID;

public class PlatoRecyclerAdapter extends RecyclerView.Adapter<PlatoRecyclerAdapter.PlatoViewHolder> {
    private List<PlatoApi> mDataset;
    private AppCompatActivity activity;
    String  estadoBtn;
    PlatoApi plato;
    private static Context mContext;


    public class PlatoViewHolder extends RecyclerView.ViewHolder {
        CardView card;
        ImageView imgPlato;
        TextView tituloPlato;
        TextView precioPlato;
        TextView descPlato;
        TextView calorias;
        Button btnElegirPlato;
        TextView etiquetaPrecio;
        TextView idPlato;
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
            idPlato = itemView.findViewById(R.id.idPlato);
        }

    }



    public PlatoRecyclerAdapter(List<PlatoApi> list, PlatoRecyclerActivity platoRecyclerActivity, String valor, Context ctx) {
        mDataset = list;
        activity = platoRecyclerActivity;
        estadoBtn = valor;
        mContext = ctx;
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
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(dm);
        holder.imgPlato.setMinimumHeight(dm.heightPixels);
        holder.imgPlato.setMinimumWidth(dm.widthPixels);

        // Creamos una referencia al storage con la Uri de la img
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();

        StorageReference platosImagesRef = storageRef.child("/images/"+plato.getId().toString()+".jpg");

        final long THREE_MEGABYTE = 3 * 1024 * 1024;
        platosImagesRef.getBytes(THREE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {

            @Override
            public void onSuccess(byte[] bytes) {
                holder.imgPlato.setImageBitmap(BitmapFactory.decodeByteArray(bytes, 0, bytes.length));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                holder.imgPlato.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.milanga));
            }
        });

        holder.tituloPlato.setText(plato.getTitulo());
        holder.precioPlato.setText(plato.getPrecio().toString());
        holder.descPlato.setText(plato.getDescripcion());
        holder.calorias.setText(plato.getCalorias().toString());
        holder.idPlato.setText(plato.getId().toString());
        if(estadoBtn.equals("true")){
            holder.btnElegirPlato.setVisibility(View.VISIBLE);
        }
        holder.btnElegirPlato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("titulo",holder.tituloPlato.getText().toString());
                returnIntent.putExtra("descripcion",holder.descPlato.getText().toString());
                returnIntent.putExtra("precio",holder.precioPlato.getText().toString());
                returnIntent.putExtra("calorias",holder.calorias.getText().toString());
                returnIntent.putExtra("id",holder.idPlato.getText().toString());

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
