package com.example.lab1appmoviles;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;




public class HomeActivity extends AppCompatActivity {

    Toolbar myToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.opcion1:
                Intent i = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(i);
                //Toast.makeText(this,"Click en opcion Registrarme",Toast.LENGTH_LONG).show();
                break;
            case R.id.opcion2:
                Intent h = new Intent(HomeActivity.this, AltaNuevoPlato.class);
                startActivity(h);
                //Toast.makeText(this,"Click en opcion Crear Items",Toast.LENGTH_LONG).show();
                break;
            case R.id.opcion3:
                Toast.makeText(this,"Click en opcion Lista de Items",Toast.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
