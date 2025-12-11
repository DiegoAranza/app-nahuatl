package com.example.tabs;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class NumerosON extends AppCompatActivity {
    ImageButton btnnumbasicos;
    ImageButton btnnumintermedios;
    FirebaseStorage storage;
    ImageButton nav_lecciones, nav_glosario, nav_voz, nav_matematicas, nav_espanol, btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_numeros_on);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


        btnnumbasicos = findViewById(R.id.btnnumbasicos);
        btnnumintermedios = findViewById(R.id.btnnumintermedios);
        nav_lecciones = findViewById(R.id.nav_lecciones);
        nav_glosario = findViewById(R.id.nav_glosario);
        nav_voz = findViewById(R.id.nav_voz);
        nav_matematicas = findViewById(R.id.nav_matematicas);
        nav_espanol = findViewById(R.id.nav_espanol);
        btnHome = findViewById(R.id.btnHome);

        storage = FirebaseStorage.getInstance();

        StorageReference storageRef = storage.getReference().child("numerosbasico.png");
        storageRef.getDownloadUrl().addOnSuccessListener(uri -> {
            Glide.with(NumerosON.this)
                    .load(uri.toString())
                    .into(btnnumbasicos);
        });

        StorageReference storageRef2 = storage.getReference().child("numerosintermedio.png");
        storageRef2.getDownloadUrl().addOnSuccessListener(uri -> {
            Glide.with(NumerosON.this)
                    .load(uri.toString())
                    .into(btnnumintermedios);
        });


        btnnumbasicos.setOnClickListener(v -> {
            Intent intent = new Intent(NumerosON.this, NumerosBasicos.class);
            startActivity(intent);
        });
        btnnumintermedios.setOnClickListener(v -> {
            Intent intent = new Intent(NumerosON.this, NumerosIntermedios.class);
            startActivity(intent);
        });
        nav_lecciones.setOnClickListener(view -> {
            Intent intent = new Intent(NumerosON.this, Lecciones.class);
            startActivity(intent);
        });
        nav_glosario.setOnClickListener(view -> {
            Intent intent = new Intent(NumerosON.this, Diccionario.class);
            startActivity(intent);
        });
        nav_voz.setOnClickListener(view -> {
            Intent intent = new Intent(NumerosON.this, busqueda.class);
            startActivity(intent);
        });
        nav_matematicas.setOnClickListener(view -> {
            Intent intent = new Intent(NumerosON.this, NumerosON.class);
            startActivity(intent);
        });
        nav_espanol.setOnClickListener(view -> {
            Intent intent = new Intent(NumerosON.this, Lecturas.class);
            startActivity(intent);
        });
        btnHome.setOnClickListener(view -> {
            Intent intent = new Intent(NumerosON.this, Menu.class);
            startActivity(intent);
        });


    }
}