package com.example.lab1appmoviles;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.lab1appmoviles.dao.PedidoService;
import com.example.lab1appmoviles.dao.PlatoService;
import com.example.lab1appmoviles.room.AppRepository;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.UUID;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AltaNuevoPlato extends AppCompatActivity implements AppRepository.OnResultCallback{

    Toolbar toolbarOpcion2;
    Button btnSave;
    EditText nombrePlato;
    EditText descPlato;
    EditText precio;
    EditText calorias;
    PlatoApi plato;
    AppRepository appRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plato);
        toolbarOpcion2 = findViewById(R.id.toolbar2);
        btnSave=findViewById(R.id.buttonSave);
        nombrePlato= findViewById(R.id.Titulo);
        descPlato= findViewById(R.id.Descripcion);
        precio= findViewById(R.id.Precio);
        calorias= findViewById(R.id.Calorias);
        setSupportActionBar(toolbarOpcion2);
        appRepository = new AppRepository(this.getApplication(), this);

        //para mostrar icono flecha atrás
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        btnSave.setOnClickListener(new View.OnClickListener(){

            boolean validar=false;
            @Override
            public void onClick(View view) {
                if (nombrePlato.getText().toString().isEmpty()) {
                    nombrePlato.setError("No puede estar vacio el campo " + nombrePlato.getHint());
                    validar=true;
                }
                if (descPlato.getText().toString().isEmpty()) {
                    descPlato.setError("No puede estar vacio el campo " + descPlato.getHint());
                    validar=true;
                }
                if (precio.getText().toString().isEmpty()) {
                    precio.setError("No puede estar vacio el campo " + precio.getHint());
                    validar=true;
                }
                if (calorias.getText().toString().isEmpty()) {
                    calorias.setError("No puede estar vacio el campo " + calorias.getHint());
                    validar=true;
                }

                if(validar==true){
                    validar=false;
                }
                else{
                    //plato= new PlatoApi(nombrePlato.getText().toString(),descPlato.getText().toString(),Integer.parseInt(calorias.getText().toString()),Double.parseDouble(precio.getText().toString()),null);
                    //appRepository.insertar(plato);

                    PlatoService platoService = UtilsRetrofit.getInstance().retrofit.create(PlatoService.class);
                    //ACA SE LLAMARIA AL METODO que necesitariamos
                    JSONObject body = new JSONObject();

                    try {
                        //TODO: el pedido para ellos tiene varios id de platos, mirar eso

                        body.put("titulo", nombrePlato.getText().toString());
                        body.put("descripcion",descPlato.getText().toString());
                        body.put("calorias",Integer.parseInt(calorias.getText().toString()));
                        body.put("precio",Double.parseDouble(precio.getText().toString()));
                        /*

                         */


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }



                    RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),body.toString());


                    Call<PlatoApi> callPlato = platoService.createPlato(requestBody);

                    callPlato.enqueue(
                            new Callback<PlatoApi>() {
                                @Override
                                public void onResponse(Call<PlatoApi> call, Response<PlatoApi> response) {

                                    if (response.code() == 201) {
                                        Log.d("DEBUG", "Returno Exitoso");
                                        Toast.makeText(AltaNuevoPlato.this, "Plato Creado",Toast.LENGTH_LONG).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<PlatoApi> call, Throwable t) {
                                    Log.d("DEBUG", "Returno Fallido");
                                    t.printStackTrace();
                                }
                            }
                    );
                }


                    onBackPressed();
                    //appRepository.buscarTodos();

                //    lia.setPlato(plato);

                    //ACA CREA EL PLATO


            }

            });

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


    @Override
    public void onResult(List result) {
        Log.d("DEBUG", "Plato found2");
        List< Plato > typedList = (List<Plato>) result;
        //Toast.makeText(AltaNuevoPlato.this, typedList.get(0).getTitulo().toString()+" - "+typedList.get(1).getTitulo().toString(), Toast.LENGTH_SHORT).show();
        Toast.makeText(AltaNuevoPlato.this, typedList.get(0).getTitulo().toString()+" - "+typedList.get(1).getTitulo().toString(), Toast.LENGTH_SHORT).show();
    }
}
