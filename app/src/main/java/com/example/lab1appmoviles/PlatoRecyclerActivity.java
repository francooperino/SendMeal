package com.example.lab1appmoviles;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab1appmoviles.dao.DaoPlatos;

public class PlatoRecyclerActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    String valor;
    Toolbar toolbarOpcionPlatos;
    DaoPlatos daoPlato;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolbarOpcionPlatos = findViewById(R.id.toolbarRecycler);
        setContentView(R.layout.activity_recycler_plato);
        toolbarOpcionPlatos = findViewById(R.id.toolbarRecycler);
        daoPlato = new DaoPlatos();
        valor = getIntent().getExtras().getString("habilitar boton pedir");
        setSupportActionBar(toolbarOpcionPlatos);
        //para mostrar icono flecha atrás
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setDisplayShowTitleEnabled(false);

        //String datoGenero = getIntent().getExtras().getString("GENERO");
        recyclerView = (RecyclerView) findViewById(R.id.recyclerPlato);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mAdapter = new PlatoRecyclerAdapter(daoPlato.list(),this, valor);
        recyclerView.setAdapter(mAdapter);

    }

    //para aplicar funcionalidad flecha atrás
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            case R.id.opcion1:
                Intent i = new Intent(PlatoRecyclerActivity.this, PedidoActivity.class);
                startActivity(i);
                //Toast.makeText(this,"Click en opcion Registrarme",Toast.LENGTH_LONG).show();
                break;


        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar_listaplato,menu);
        return super.onCreateOptionsMenu(menu);
    }
    public String obtenerValor() {
        return valor;
    }

}
