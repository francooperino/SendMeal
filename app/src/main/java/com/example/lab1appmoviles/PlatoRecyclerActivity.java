package com.example.lab1appmoviles;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab1appmoviles.dao.DaoPlatos;

public class PlatoRecyclerActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    Toolbar toolbarOpcionPlatos;

    DaoPlatos daoPlato;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolbarOpcionPlatos = findViewById(R.id.toolbarRecycler);
        setContentView(R.layout.activity_recycler_plato);
        toolbarOpcionPlatos = findViewById(R.id.toolbarRecycler);
        daoPlato = new DaoPlatos();
        setSupportActionBar(toolbarOpcionPlatos);
        //para mostrar icono flecha atrás
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //String datoGenero = getIntent().getExtras().getString("GENERO");
        recyclerView = (RecyclerView) findViewById(R.id.recyclerPlato);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mAdapter = new PlatoRecyclerAdapter(daoPlato.list(),this);
        recyclerView.setAdapter(mAdapter);
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
