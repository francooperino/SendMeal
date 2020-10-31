package com.example.lab1appmoviles.room;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lab1appmoviles.Plato;

import java.util.List;


public class InsertItemsActivity extends AppCompatActivity implements AppRepository.OnResultCallback {
    AppRepository repository;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        /* ..... */
        //super.onCreate(savedInstanceState);
        repository = new AppRepository(this.getApplication(), this);
//        Plato plato = new Plato("milanesa","abundante",12,88.0);

        //repository.insertar(plato);
    }

    @Override
    public void onResult(List result) {
        // Vamos a obtener una Lista de items como resultado cuando finalize
        //Toast.makeText(ListItemsActivity.this, "Exito!", Toast.LENGTH_SHORT).show();
    }

}