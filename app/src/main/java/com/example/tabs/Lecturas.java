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

public class Lecturas extends AppCompatActivity {
    Button btnlec1, btnlec2, btnlec3, btnvolver;
    ImageButton nav_lecciones, nav_glosario, nav_voz, nav_matematicas, nav_espanol, btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lecturas);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        btnlec1 = findViewById(R.id.btnlec1);
        btnlec2 = findViewById(R.id.btnlec2);
        btnlec3 = findViewById(R.id.btnlec3);
        nav_lecciones = findViewById(R.id.nav_lecciones);
        nav_glosario = findViewById(R.id.nav_glosario);
        nav_voz = findViewById(R.id.nav_voz);
        nav_matematicas = findViewById(R.id.nav_matematicas);
        nav_espanol = findViewById(R.id.nav_espanol);
        btnHome = findViewById(R.id.btnHome);

        btnlec1.setOnClickListener(view -> {
            Intent intent = new Intent(Lecturas.this, Lectura1.class);
            startActivity(intent);
        });

        btnlec2.setOnClickListener(view -> {
            Intent intent = new Intent(Lecturas.this, Lectura2.class);
            startActivity(intent);
        });

        btnlec3.setOnClickListener(view -> {
            Intent intent = new Intent(Lecturas.this, Lectura3.class);
            startActivity(intent);
        });
        nav_lecciones.setOnClickListener(view -> {
            Intent intent = new Intent(Lecturas.this, Lecciones.class);
            startActivity(intent);
        });
        nav_glosario.setOnClickListener(view -> {
            Intent intent = new Intent(Lecturas.this, Diccionario.class);
            startActivity(intent);
        });
        nav_voz.setOnClickListener(view -> {
            Intent intent = new Intent(Lecturas.this, busqueda.class);
            startActivity(intent);
        });
        nav_matematicas.setOnClickListener(view -> {
            Intent intent = new Intent(Lecturas.this, NumerosON.class);
            startActivity(intent);
        });
        nav_espanol.setOnClickListener(view -> {
            Intent intent = new Intent(Lecturas.this, Lecturas.class);
            startActivity(intent);
        });
        btnHome.setOnClickListener(view -> {
            Intent intent = new Intent(Lecturas.this, Menu.class);
            startActivity(intent);
        });

    }
}