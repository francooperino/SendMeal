package com.example.lab1appmoviles.room;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lab1appmoviles.Plato;

import java.util.List;



public class ListItemsActivity extends AppCompatActivity implements AppRepository.OnResultCallback {
    /* ..... */
    AppRepository repository;

    public void setPlato(Plato plato) {
        this.plato = plato;
    }

    Plato plato;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        /* ..... */

        super.onCreate(savedInstanceState);
        repository = new AppRepository(this.getApplication(), this);
        repository.insertar(plato);
        Toast.makeText(ListItemsActivity.this, "Exito!", Toast.LENGTH_SHORT).show();
        repository.buscarTodos();
    }

    @Override
    public void onResult(List result) {
        // Vamos a obtener una Lista de items como resultado cuando finalice
        Toast.makeText(ListItemsActivity.this, "Exito!", Toast.LENGTH_SHORT).show();
    }

}