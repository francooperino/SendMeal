package com.example.lab1appmoviles;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private SeekBar barra;
    private TextView valorActual, campoCredito;
    private Switch switchCarga;
    private CheckBox checkTerminos;
    private Button registrar;
    private EditText correoElectronico;
    private RadioGroup radioTarjeta;
    private RadioButton tipoTarjeta;
    private EditText numeroTarjeta;
    private EditText ccv;
    private EditText clave;
    private EditText clave2;
    private EditText mesVenc;
    private EditText anioVenc;
    String valor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);

        barra = (SeekBar) findViewById(R.id.barrita);
        switchCarga = findViewById(R.id.switchCarga);
        campoCredito = findViewById(R.id.campoCredito);
        checkTerminos = findViewById(R.id.checkTerminos);
        registrar = findViewById(R.id.buttonRegistrar);
        valorActual = (TextView) findViewById(R.id.textito);




        correoElectronico = findViewById(R.id.textCorreo);
        clave = findViewById(R.id.textContrasena);
        clave2 = findViewById(R.id.textcontrasena2);
        numeroTarjeta = findViewById(R.id.textNumeroTarjeta);
        ccv = findViewById(R.id.textCCV);
        mesVenc = findViewById(R.id.textMesVenc);
        anioVenc = findViewById(R.id.textAnioVenc);
        radioTarjeta = findViewById(R.id.radioGroup);

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
        checkTerminos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    registrar.setEnabled(true);

                }
                else {
                    registrar.setEnabled(false);

                }
            }
        });

        numeroTarjeta.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                // hay que apretar dos veces borrar para que quede vacio
                if (numeroTarjeta.getText().toString().isEmpty()) {
                    ccv.setEnabled(false);
                    return false;

                } else {
                    ccv.setEnabled(true);
                    return false;

                }

            }
        }
        );



        registrar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                tipoTarjeta = findViewById(radioTarjeta.getCheckedRadioButtonId());
                validarCampos((EditText) findViewById(R.id.textNombre));

            }
        });

        }


        public void validarCampos(View v){

            if (!isValidEmail(correoElectronico.getText().toString())) {
                correoElectronico.setError("El correo electronico no es valido");
            }
            if (numeroTarjeta.getText().toString().isEmpty()) {
                numeroTarjeta.setError("No puede estar vacio el "+numeroTarjeta.getHint());
            }
            if (ccv.getText().toString().isEmpty()) {
                ccv.setError("No puede estar vacio el campo "+ccv.getHint());
            }
            if (clave.getText().toString().isEmpty()) {
                clave.setError("No puede estar vacia la "+clave.getHint());
            }
            if (clave2.getText().toString().isEmpty()) {
                clave2.setError("No puede estar vacio el campo "+clave2.getHint());
            }

            if (anioVenc.getText().toString().isEmpty()) {
                anioVenc.setError("No puede estar vacio el campo año de vencimiento");
            }
            if (!clave.getText().toString().equals(clave2.getText().toString())) {
                clave2.setError("Las claves tienen que ser iguales");
            }

            if (mesVenc.getText().toString().isEmpty()) {
                mesVenc.setError("No puede estar vacio el campo mes de vencimiento");
            }else {
            if (anioVenc.getText().toString().isEmpty()) {
                anioVenc.setError("No puede estar vacio el campo año de vencimiento");
            }else {

            if(tipoTarjeta.getText().toString().equals("Credito")){

                Calendar cal = Calendar.getInstance();
                Calendar vencimiento = Calendar.getInstance();
                //long fechaActual = cal.getTimeInMillis();
               // int diaActual = cal.get(Calendar.DAY_OF_MONTH);
                cal.add(Calendar.DATE,90);
                Integer mesVencimiento = Integer.parseInt(mesVenc.getText().toString());
                Integer anioVencimiento = Integer.parseInt(anioVenc.getText().toString());

                vencimiento.set(Calendar.YEAR,anioVencimiento);
                vencimiento.set(Calendar.MONTH,mesVencimiento);
                vencimiento.set(Calendar.DATE,vencimiento.getActualMaximum(Calendar.DATE));

                if(cal.before(vencimiento)){
                    Toast toast2 = Toast.makeText(getApplicationContext(),"tarjeta supera 3 meses desde la fecha"+vencimiento.get(Calendar.DATE)+""+vencimiento.get(Calendar.MONTH)+""+vencimiento.get(Calendar.YEAR), Toast.LENGTH_LONG);
                    Toast toast3 = Toast.makeText(getApplicationContext(),"fecha con 3 meses mas "+cal.get(Calendar.DATE)+""+cal.get(Calendar.MONTH)+""+cal.get(Calendar.YEAR), Toast.LENGTH_LONG);
                    toast2.show();
                    toast3.show();
                   // tipoTarjeta.setError("la tarjeta no esta vencida");
                };
            }}}


        }
    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }


    }




