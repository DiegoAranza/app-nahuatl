package com.example.tabs;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

public class Lecciones extends AppCompatActivity {

    private LinearLayout leccionesContainer;
    private FirebaseFirestore db;
    ImageButton nav_lecciones, nav_glosario, nav_voz, nav_matematicas, nav_espanol, btnHome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lecciones);

        // Aplicar márgenes del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.scrollView), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


        leccionesContainer = findViewById(R.id.leccionesContainer);
        nav_lecciones = findViewById(R.id.nav_lecciones);
        nav_glosario = findViewById(R.id.nav_glosario);
        nav_voz = findViewById(R.id.nav_voz);
        nav_matematicas = findViewById(R.id.nav_matematicas);
        nav_espanol = findViewById(R.id.nav_espanol);
        btnHome = findViewById(R.id.btnHome);
        db = FirebaseFirestore.getInstance();

        // Cargar las lecciones desde Firebase
        cargarLecciones();
        nav_lecciones.setOnClickListener(view -> {
            Intent intent = new Intent(Lecciones.this, Lecciones.class);
            startActivity(intent);
        });

        nav_glosario.setOnClickListener(view -> {
            Intent intent = new Intent(Lecciones.this, Diccionario.class);
            startActivity(intent);
        });

        nav_voz.setOnClickListener(view -> {
            Intent intent = new Intent(Lecciones.this, busqueda.class);
            startActivity(intent);
        });
        nav_matematicas.setOnClickListener(view -> {
            Intent intent = new Intent(Lecciones.this, NumerosON.class);
            startActivity(intent);
        });
        nav_espanol.setOnClickListener(view -> {
            Intent intent = new Intent(Lecciones.this, Lecturas.class);
            startActivity(intent);
        });
        btnHome.setOnClickListener(view -> {
            Intent intent = new Intent(Lecciones.this, Menu.class);
            startActivity(intent);
        });
    }

    private void cargarLecciones() {
        CollectionReference leccionesRef = db.collection("lecciones"); // Nombre de tu colección

        leccionesRef.get().addOnSuccessListener(queryDocumentSnapshots -> {
            int index = 1;
            for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                String lessonId = doc.getId(); // ID del documento como identificador de lección

                Button leccionBtn = new Button(this);
                leccionBtn.setText("Lección " + index);
                leccionBtn.setTextSize(30);
                leccionBtn.setTextColor(Color.BLACK);
                leccionBtn.setBackgroundTintList(getResources().getColorStateList(R.color.brown));

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        600,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.setMargins(0, 40, 0, 0);
                leccionBtn.setLayoutParams(params);

                // Abrir la lección al hacer clic
                leccionBtn.setOnClickListener(view -> abrirLeccion(lessonId));

                leccionesContainer.addView(leccionBtn);
                index++;
            }

            if (index == 1) {
                Toast.makeText(this, "No se encontraron lecciones.", Toast.LENGTH_SHORT).show();
            }
            

        }).addOnFailureListener(e -> {
            Toast.makeText(this, "Error al cargar las lecciones: " + e.getMessage(), Toast.LENGTH_LONG).show();
        });
    }

    private void abrirLeccion(String lessonId) {
        Intent intent = new Intent(this, Leccion1.class); // Reemplaza con tu clase correspondiente
        intent.putExtra("lessonId", lessonId); // Enviamos el ID de la lección
        startActivity(intent);
    }

}
