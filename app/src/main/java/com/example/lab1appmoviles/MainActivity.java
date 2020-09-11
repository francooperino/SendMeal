package com.example.lab1appmoviles;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SeekBar barra;
    private TextView valorActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        barra = (SeekBar) findViewById(R.id.barrita);
        valorActual = (TextView) findViewById(R.id.textito);

        valorActual.setText(barra.getProgress());

    }



}