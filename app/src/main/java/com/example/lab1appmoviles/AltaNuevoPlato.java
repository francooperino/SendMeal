package com.example.lab1appmoviles;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.lab1appmoviles.room.AppDatabase;
import com.example.lab1appmoviles.room.AppRepository;
import com.example.lab1appmoviles.room.InsertItemsActivity;
import com.example.lab1appmoviles.room.ListItemsActivity;

public class AltaNuevoPlato extends AppCompatActivity {

    Toolbar toolbarOpcion2;
    Button btnSave;
    EditText nombrePlato;
    EditText descPlato;
    EditText precio;
    EditText calorias;
    Plato plato;
    AppRepository appRepository;
    AppRepository.OnResultCallback context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plato);
        toolbarOpcion2 = findViewById(R.id.toolbar2);
        btnSave=findViewById(R.id.buttonSave);
        nombrePlato= findViewById(R.id.Titulo);
        descPlato= findViewById(R.id.Descripcion);
        precio= findViewById(R.id.Precio);
        calorias= findViewById(R.id.Calorias);
        setSupportActionBar(toolbarOpcion2);

        //para mostrar icono flecha atrás
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        btnSave.setOnClickListener(new View.OnClickListener(){

            boolean validar=false;
            @Override
            public void onClick(View view) {
                if (nombrePlato.getText().toString().isEmpty()) {
                    nombrePlato.setError("No puede estar vacio el campo " + nombrePlato.getHint());
                    validar=true;
                }
                if (descPlato.getText().toString().isEmpty()) {
                    descPlato.setError("No puede estar vacio el campo " + descPlato.getHint());
                    validar=true;
                }
                if (precio.getText().toString().isEmpty()) {
                    precio.setError("No puede estar vacio el campo " + precio.getHint());
                    validar=true;
                }
                if (calorias.getText().toString().isEmpty()) {
                    calorias.setError("No puede estar vacio el campo " + calorias.getHint());
                    validar=true;
                }

                if(validar==true){
                    validar=false;
                }
                else{
                    plato= new Plato(nombrePlato.getText().toString(),descPlato.getText().toString(),Integer.parseInt(calorias.getText().toString()),Double.parseDouble(precio.getText().toString()));
                   // Toast.makeText(AltaNuevoPlato.this, "Plato Creado",Toast.LENGTH_LONG).show();

                 InsertItemsActivity iia = new InsertItemsActivity();
                 ListItemsActivity lia = new ListItemsActivity();

                //    lia.setPlato(plato);

                    //ACA CREA EL PLATO
                }

            }

            });

    }

    //para aplicar funcionalidad flecha atrás
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
