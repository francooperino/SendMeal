package com.example.lab1appmoviles;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.messaging.FirebaseMessaging;


public class HomeActivity extends AppCompatActivity {


    Toolbar myToolbar;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        mAuth = FirebaseAuth.getInstance();
        // Iniciar Session como usuario anónimo
        mAuth.signInAnonymously();
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            // Error
                            return;
                        }

                        // FCM token
                        String token = task.getResult();

                        // Imprimirlo en un toast y en logs
                        Log.d("[FCM - TOKEN]", token);
                        //Toast.makeText(HomeActivity.this, token, Toast.LENGTH_SHORT).show();
                    }
                });

    }
    private void signInAnonymously() {
        mAuth.signInAnonymously()
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Exito
                            Log.d("Firebase", "signInAnonymously:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            // Error
                            Log.w("Firebase", "signInAnonymously:failure", task.getException());
                            Toast.makeText(HomeActivity.this, "Authentication failed.",
                                   Toast.LENGTH_SHORT).show();
                        }
                    }
                });
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
                Intent g = new Intent(HomeActivity.this, PlatoRecyclerActivity.class);
                g.putExtra("habilitar boton pedir" , "false");
                startActivity(g);
                //Toast.makeText(this,"Click en opcion Lista de Items",Toast.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
