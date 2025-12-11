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

public class Glosario extends AppCompatActivity {

    ImageButton btnabe;
    ImageButton btnani;
    ImageButton btnast;
    ImageButton btncol;
    ImageButton btncom;
    ImageButton btnnum;
    ImageButton btnpro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();

        }
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_glosario);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


        btnabe = findViewById(R.id.btnabecedario);
        btnabe.setOnClickListener(v -> {
            startActivity(new Intent(Glosario.this, Abecedario.class));
        });

        btnani = findViewById(R.id.btnanimales);
        btnani.setOnClickListener(v -> {
            startActivity(new Intent(Glosario.this, Animales.class));
        });

        btnast = findViewById(R.id.btnastros);
        btnast.setOnClickListener(v -> {
            startActivity(new Intent(Glosario.this, Astros.class));
        });

        btncol = findViewById(R.id.btncolores);
        btncol.setOnClickListener(v -> {
            startActivity(new Intent(Glosario.this, Colores.class));
        });

        btncom = findViewById(R.id.btncomida);
        btncom.setOnClickListener(v -> {
            startActivity(new Intent(Glosario.this, Comida.class));
        });

        btnnum = findViewById(R.id.btnnumeros);
        btnnum.setOnClickListener(v -> {
            startActivity(new Intent(Glosario.this, Numeros.class));
        });

        btnpro = findViewById(R.id.btnpronombres);
        btnpro.setOnClickListener(v -> {
            startActivity(new Intent(Glosario.this, Pronombres.class));
        });


        }

    }
