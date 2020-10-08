package com.example.lab1appmoviles;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PedidoActivity extends AppCompatActivity {

    Toolbar myToolbar;
    Button btnAgregarPlato;
    Button btnConfirmarPlato;
    TextView platoElegido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);
        myToolbar = findViewById(R.id.toolbarPedido);
        btnAgregarPlato = findViewById(R.id.buttonAgregarPlato);
        btnConfirmarPlato = findViewById(R.id.buttonConfirmarPedido);
        platoElegido = findViewById(R.id.textView2);
        setSupportActionBar(myToolbar);

        //para mostrar icono flecha atrás
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        btnAgregarPlato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int LAUNCH_SECOND_ACTIVITY = 1;
                Intent i = new Intent(PedidoActivity.this, PlatoRecyclerActivity.class);
                i.putExtra("habilitar boton pedir" , "true");
                startActivityForResult(i, LAUNCH_SECOND_ACTIVITY);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            if(resultCode== RESULT_OK){
                //String serieTitulo = data.getExtras().getString("serie");
                //Integer indice = data.getExtras().getInt("indiceElegido");
                platoElegido.setText("Plato elegido: "+data.getExtras().getString("titulo") +",  "+data.getExtras().getString("precio"));
            }
        }
    }

    //para aplicar funcionalidad flecha atrás
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;


        }

        return super.onOptionsItemSelected(item);

    }

}