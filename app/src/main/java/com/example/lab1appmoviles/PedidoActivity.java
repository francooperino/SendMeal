package com.example.lab1appmoviles;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab1appmoviles.dao.PlatoService;
import com.example.lab1appmoviles.room.AppRepository;
import com.example.lab1appmoviles.room.PedidoConPlatos;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class PedidoActivity extends AppCompatActivity implements AppRepository.OnResultCallback{

    Toolbar myToolbar;
    Button btnAgregarPlato;
    Button btnConfirmarPlato;
    //TextView platoElegido;
    //ListView platosElegidos;
    Spinner platosE;
    List<Plato> platito;
    ArrayAdapter<String> adapter;
    EditText email;
    EditText direccion;
    String seleccion;
    RadioGroup tipoPedido;
    Pedido pedido;
    PedidoConPlatos pcp;
    AppRepository appRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);
        myToolbar = findViewById(R.id.toolbarPedido);
        btnAgregarPlato = findViewById(R.id.buttonAgregarPlato);
        btnConfirmarPlato = findViewById(R.id.buttonConfirmarPedido);
        //platosElegidos = (ListView) findViewById(R.id.listView2);
        platosE = findViewById(R.id.spinnerPedido);
        setSupportActionBar(myToolbar);
        platito=new ArrayList<>();
        email = findViewById(R.id.textMail);
        direccion = findViewById(R.id.textDirec);
        tipoPedido = findViewById(R.id.radioGroup);
        appRepository = new AppRepository(this.getApplication(), this);
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

        btnConfirmarPlato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioButtonID = tipoPedido.getCheckedRadioButtonId();
                View radioButton = tipoPedido.findViewById(radioButtonID);
                int idx = tipoPedido.indexOfChild(radioButton);
                RadioButton r = (RadioButton)  tipoPedido.getChildAt(idx);
                String selectedtext = r.getText().toString();
                Double sumaTotal=0.0;
                for(Plato a : platito)
                {
                    sumaTotal +=a.getPrecio();
                }

                pedido= new Pedido(null,email.getText().toString(),direccion.getText().toString(),selectedtext,sumaTotal);
                pcp = new PedidoConPlatos(pedido,platito);
                appRepository.insertar(pcp);
                appRepository.buscarTodosLosPedidos();
                new TaskNotificacion().execute();


                PlatoService platoService = UtilsRetrofit.getInstance().retrofit.create(PlatoService.class);
                //ACA SE LLAMARIA AL METODO que necesitariamos

             /*   Call<List<Plato>> callPlatos = platoService.getPlatoList();

                callPlatos.enqueue(
                        new Callback<List<Plato>>() {
                            @Override
                            public void onResponse(Call<List<Plato>> call, Response<List<Plato>> response) {
                                if (response.code() == 200) {
                                    Log.d("DEBUG", "Returno Exitoso");
                                }
                            }

                            @Override
                            public void onFailure(Call<List<Plato>> call, Throwable t) {
                                Log.d("DEBUG", "Returno Fallido");
                            }
                        }
                );*/

            }
        });

    }

    public void actualizarListView (ArrayList arrayPlatos){
        if(!arrayPlatos.isEmpty()){
            adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arrayPlatos);
           // adapter = new ArrayAdapter<String>(this,R.layout.activity_lista_platos,R.id.textView5, arrayPlatos);
            platosE.setAdapter(adapter);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            if(resultCode== RESULT_OK){
                ArrayList<String> arrayPlatos = new ArrayList<String>();
                //String serieTitulo = data.getExtras().getString("serie");
                //Integer indice = data.getExtras().getInt("indiceElegido");
                Plato plato =new Plato(data.getExtras().getString("titulo"),
                        data.getExtras().getString("descripcion").toString(),
                        Integer.parseInt(data.getExtras().getString("calorias")),
                        Double.parseDouble(data.getExtras().getString("precio")),
                        null);
                /*Plato plato = new Plato("hola","hola",56,45.9,null);
                Plato plato2 = new Plato("hola2","hola",56,45.9,null);
                Plato plato3 = new Plato("hola3","hola",56,45.9,null);*/
                platito.add(plato);
                //platito.add(plato2);
                //platito.add(plato3);
                //int cont =0;
                for(Plato a : platito)
                {
                    arrayPlatos.add("Titulo: "+a.getTitulo().toString()+"     "+"Precio: $"+a.getPrecio().toString());
                    //cont++;
                }
                actualizarListView(arrayPlatos);
                //platoElegido.setText("Plato elegido: "+data.getExtras().getString("titulo") +",  "+data.getExtras().getString("precio"));
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

    @Override
    public void onResult(List result) {
        List< Pedido > typedList = (List<Pedido>) result;
        //Toast.makeText(AltaNuevoPlato.this, typedList.get(0).getTitulo().toString()+" - "+typedList.get(1).getTitulo().toString(), Toast.LENGTH_SHORT).show();
       // Toast.makeText(PedidoActivity.this, typedList.get(1).getIdPedido().toString()+" - "+typedList.get(1).getEmail().toString(), Toast.LENGTH_SHORT).show();
    }


    class TaskNotificacion extends AsyncTask<String, Void, String> {
                @Override
                protected String doInBackground(String... params) {
                    //...
                    try {
                        Thread.sleep(5000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }

                    return null;

                    //return params[0];
                }

                @Override
                protected void onPostExecute(String result) {
                    AlarmManager alarmManager;

                    Intent intent = new Intent(PedidoActivity.this, MyNotificationPublisher.class);

                    alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                    PendingIntent pi = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, 0);

                    alarmManager.set(AlarmManager.RTC_WAKEUP, 0, pi);

                }


    }



}