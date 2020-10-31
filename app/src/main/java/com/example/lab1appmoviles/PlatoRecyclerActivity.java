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
import com.example.lab1appmoviles.room.AppRepository;

import java.util.List;

public class PlatoRecyclerActivity extends AppCompatActivity implements AppRepository.OnResultCallback{
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    String valor;
    Toolbar toolbarOpcionPlatos;
    DaoPlatos daoPlato;
    AppRepository appRepository;
    List<Plato> listaPlato;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolbarOpcionPlatos = findViewById(R.id.toolbarRecycler);
        setContentView(R.layout.activity_recycler_plato);
        toolbarOpcionPlatos = findViewById(R.id.toolbarRecycler);
        daoPlato = new DaoPlatos();
        valor = getIntent().getExtras().getString("habilitar boton pedir");
        setSupportActionBar(toolbarOpcionPlatos);
        appRepository = new AppRepository(this.getApplication(), this);
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
        appRepository.buscarTodos();




    }
    public void actualizarRecycler (List<Plato> platos){
        if(!platos.isEmpty()){
            mAdapter = new PlatoRecyclerAdapter(platos,this, valor);
            recyclerView.setAdapter(mAdapter);
        }
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

    @Override
    public void onResult(List result) {
        List< Plato > typedList = (List<Plato>) result;
        actualizarRecycler(typedList);

    }
}
