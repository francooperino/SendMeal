package com.example.lab1appmoviles;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AltaNuevoPlato extends AppCompatActivity {

    Toolbar toolbarOpcion2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plato);
        toolbarOpcion2 = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbarOpcion2);



    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
    toolbarOpcion2.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);

    toolbarOpcion2.setNavigationOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }
    });
}
