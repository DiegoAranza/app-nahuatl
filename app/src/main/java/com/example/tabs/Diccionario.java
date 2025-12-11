package com.example.tabs;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Diccionario extends AppCompatActivity {
    ImageButton btnabecedario, btnanimales, btnastros, btncolores, btncomida, btnpronombres, btnadjetivos;
    ImageButton nav_lecciones, nav_glosario, nav_voz, nav_matematicas, nav_espanol, btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_diccionario);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


        btnabecedario = findViewById(R.id.btnabecedario);
        btnanimales = findViewById(R.id.btnanimales);
        btnastros = findViewById(R.id.btnastros);
        btncolores = findViewById(R.id.btncolores);
        btncomida = findViewById(R.id.btncomida);
        btnpronombres = findViewById(R.id.btnpronombres);
        btnadjetivos = findViewById(R.id.btnadjetivos);
        nav_lecciones = findViewById(R.id.nav_lecciones);
        nav_glosario = findViewById(R.id.nav_glosario);
        nav_voz = findViewById(R.id.nav_voz);
        nav_matematicas = findViewById(R.id.nav_matematicas);
        nav_espanol = findViewById(R.id.nav_espanol);
        btnHome = findViewById(R.id.btnHome);

        btnabecedario.setOnClickListener(view -> {
            Intent intent = new Intent(Diccionario.this, AbecedarioON.class);
            startActivity(intent);
        });

        btnanimales.setOnClickListener(view -> {
            Intent intent = new Intent(Diccionario.this, AnimalesON.class);
            startActivity(intent);
        });

        btnastros.setOnClickListener(view -> {
            Intent intent = new Intent(Diccionario.this, AstrosON.class);
            startActivity(intent);
        });

        btncolores.setOnClickListener(view -> {
            Intent intent = new Intent(Diccionario.this, ColoresON.class);
            startActivity(intent);
        });

        btncomida.setOnClickListener(view -> {
            Intent intent = new Intent(Diccionario.this, ComidaON.class);
            startActivity(intent);
        });

        btnpronombres.setOnClickListener(view -> {
            Intent intent = new Intent(Diccionario.this, PronombresON.class);
            startActivity(intent);
        });

        btnadjetivos.setOnClickListener(view -> {
            Intent intent = new Intent(Diccionario.this, AdjetivosON.class);
            startActivity(intent);
        });
        nav_lecciones.setOnClickListener(view -> {
            Intent intent = new Intent(Diccionario.this, Lecciones.class);
            startActivity(intent);
        });
        nav_glosario.setOnClickListener(view -> {
            Intent intent = new Intent(Diccionario.this, Diccionario.class);
            startActivity(intent);
        });
        nav_voz.setOnClickListener(view -> {
            Intent intent = new Intent(Diccionario.this, busqueda.class);
            startActivity(intent);
        });
        nav_matematicas.setOnClickListener(view -> {
            Intent intent = new Intent(Diccionario.this, NumerosON.class);
            startActivity(intent);
        });
        nav_espanol.setOnClickListener(view -> {
            Intent intent = new Intent(Diccionario.this, Lecturas.class);
            startActivity(intent);
        });
        btnHome.setOnClickListener(view -> {
            Intent intent = new Intent(Diccionario.this, Menu.class);
            startActivity(intent);
        });

    }
}