package com.example.tabs;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity2 extends AppCompatActivity {
    Button btnCrearDatos;
    Button btnIniciarSesion;
    Button btnSinConexion;
    EditText editTextemail;
    EditText editTextpassword;
    FirebaseAuth Auth;
    ImageView gif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


        btnCrearDatos = findViewById(R.id.btnCrearDatos);
        btnCrearDatos.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity2.this, MainActivity3.class));

        });

        btnSinConexion = findViewById(R.id.btnSinConexion);
        btnSinConexion.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity2.this, Glosario.class));
                });

        editTextemail = findViewById(R.id.editTextemail);
        editTextpassword = findViewById(R.id.editTextpassword);
        Auth = FirebaseAuth.getInstance();


        btnIniciarSesion = findViewById(R.id.btnIniciarSesion);
        btnIniciarSesion.setOnClickListener(v -> {
            String emailuser= editTextemail.getText().toString();
            String passworduser= editTextpassword.getText().toString();

            if(emailuser.isEmpty() || passworduser.isEmpty()){
                Toast.makeText(MainActivity2.this, "Llene todos los campos", Toast.LENGTH_SHORT).show();
            }else{
                login(emailuser, passworduser);
            }
        });

        gif = findViewById(R.id.imageView2);
        Glide.with(this)
                .asGif()
                .load(R.drawable.logoanimado) // Puedes usar un GIF en drawable o una URL
                .into(gif);


    }

    private void login(String emailuser, String passworduser){
        Auth.signInWithEmailAndPassword(emailuser,passworduser).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    finish();
                    startActivity(new Intent(MainActivity2.this, Menu.class));
                    Toast.makeText(MainActivity2.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity2.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity2.this, "Error al iniciar sesion", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
