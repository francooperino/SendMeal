package com.example.lab1appmoviles;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SeekBar barra;
    private TextView valorActual, campoCredito;
    private Switch switchCarga;

    String valor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);

        barra = (SeekBar) findViewById(R.id.barrita);
        switchCarga = findViewById(R.id.switchCarga);
        campoCredito = findViewById(R.id.campoCredito);

        valorActual = (TextView) findViewById(R.id.textito);

        valor = String.valueOf(barra.getProgress());

        valorActual.setText(" $ " + valor);


        barra.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                valorActual.setText(" $ " + String.valueOf(progress));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        switchCarga.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    valorActual.setVisibility(View.VISIBLE);
                    barra.setVisibility(View.VISIBLE);
                    campoCredito.setVisibility(View.VISIBLE);
                } else {
                    barra.setVisibility(View.GONE);
                    valorActual.setVisibility(View.GONE);
                    campoCredito.setVisibility(View.GONE);
                }


            }
        });

    }


}