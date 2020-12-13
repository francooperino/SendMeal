package com.example.lab1appmoviles;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import com.example.lab1appmoviles.dao.PedidoService;
import com.example.lab1appmoviles.dao.PlatoService;
import com.example.lab1appmoviles.room.AppRepository;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
    Button cargarImagen;
    ImageView imagenPlato;
    AppRepository appRepository;
    Uri imageUri;
    Uri downloadUri;
    Boolean tieneImagen=false;
    byte[] datas;
    static final int CAMARA_REQUEST = 1;
    static final int GALERIA_REQUEST = 2;
    private Boolean tipo= false;
    private void lanzarCamara() {
        Intent camaraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camaraIntent, CAMARA_REQUEST);
    }

    private void abrirGaleria() {
        Intent galeriaIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(galeriaIntent, GALERIA_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMARA_REQUEST  && resultCode == RESULT_OK) {
            tieneImagen=true;
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            float proporcion = 600 / (float) imageBitmap.getWidth();
            imageBitmap = Bitmap.createScaledBitmap(imageBitmap,600,(int) (imageBitmap.getHeight() * proporcion),false);
            imagenPlato.setImageBitmap(imageBitmap);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            datas = baos.toByteArray(); // Imagen en arreglo de bytes

        }
        if(requestCode == GALERIA_REQUEST && resultCode == RESULT_OK){
            tieneImagen=true;
            imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                float proporcion = 600 / (float) bitmap.getWidth();
                bitmap = Bitmap.createScaledBitmap(bitmap,600,(int) (bitmap.getHeight() * proporcion),false);
                imagenPlato.setImageBitmap(bitmap);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                datas = baos.toByteArray(); // Imagen en arreglo de bytes

            } catch (IOException e) {
                e.printStackTrace();
            }

            /*imagenPlato.setImageURI(imageUri);
            imagenPlato.setMaxWidth(50);
            imagenPlato.setMaxHeight(50);*/

        }
    }

    private Boolean subirImagen(UUID id) {
        final Boolean[] result = {false};
        // Creamos una referencia a nuestro Storage
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();

        // Creamos una referencia a 'images/plato_id.jpg'
        StorageReference platosImagesRef = storageRef.child("images/"+id.toString()+".jpg");

        // Cual quiera de los tres métodos tienen la misma implementación, se debe utilizar el que corresponda
        UploadTask uploadTask = platosImagesRef.putBytes(datas);
        // UploadTask uploadTask = platosImagesRef.putFile(file);
        // UploadTask uploadTask = platosImagesRef.putStream(stream);

        // Registramos un listener para saber el resultado de la operación
        Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    Toast.makeText(AltaNuevoPlato.this, "error",Toast.LENGTH_LONG).show();
                    throw task.getException();
                }

                // Continuamos con la tarea para obtener la URL
                return platosImagesRef.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {

                if (task.isSuccessful()) {
                    // URL de descarga del archivo
                    downloadUri = task.getResult();
                    result[0] =true;
                }
                else{
                    Toast.makeText(AltaNuevoPlato.this, "No se pudo cargar la imagen",Toast.LENGTH_LONG).show();
                }
            }
        });
        return result[0];
    }

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
        imagenPlato=findViewById(R.id.imagePlato);
        setSupportActionBar(toolbarOpcion2);
        cargarImagen= findViewById(R.id.buttonCargar);
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
                if(!tieneImagen){
                    Toast.makeText(AltaNuevoPlato.this, "Ingrese una imagen para el plato",Toast.LENGTH_LONG).show();
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
                                            subirImagen(response.body().getId());
                                            onBackPressed();
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



                    //appRepository.buscarTodos();

                //    lia.setPlato(plato);

                    //ACA CREA EL PLATO


            }

            });

        cargarImagen.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(AltaNuevoPlato.this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(AltaNuevoPlato.this,
                            new String[]{Manifest.permission.CAMERA},
                            9999);

                }

                AlertDialog.Builder builder= new AlertDialog.Builder(AltaNuevoPlato.this);
                builder.setMessage("Seleccione desde donde desea cargar la imagen")
                        .setTitle("Cargar Imagen")
                        .setPositiveButton("Camara",
                                new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dlgInt, int i) {
                                tipo=true;
                                lanzarCamara();
                            }
                        })
                        .setNegativeButton("Galeria",
                                new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dlgInt, int i) {
                                abrirGaleria();
                                //TODO: pedir permisos galeria
                            }
                        });
                AlertDialog dialog= builder.create();
                dialog.show();

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 9999 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                if (tipo==true){
                    lanzarCamara();
                }
                else{
                    abrirGaleria();
                }

            }

        }
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
