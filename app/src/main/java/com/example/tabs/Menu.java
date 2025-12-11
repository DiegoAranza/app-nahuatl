package com.example.tabs;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Menu extends AppCompatActivity {

    ImageButton cerrarsesion;
    ImageButton voz;
    ImageButton lecciones;
    ImageButton glosario;
    ImageButton matematicas;
    ImageButton espa;
    FirebaseStorage storage;
    StorageReference storageRef;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


        // Firebase - Obtener nombre del usuario y mostrarlo
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String uid = user.getUid();
            FirebaseFirestore db = FirebaseFirestore.getInstance();

            db.collection("usuario").document(uid).get()
                    .addOnSuccessListener(documentSnapshot -> {
                        if (documentSnapshot.exists()) {
                            String nombre = documentSnapshot.getString("nombre");
                            TextView greetingText = findViewById(R.id.greetingText);
                            greetingText.setText("Bienvenido, " + nombre);
                        } else {
                            Log.d("MenuActivity", "Documento no existe");
                        }
                    })
                    .addOnFailureListener(e -> {
                        Log.e("MenuActivity", "Error al obtener datos", e);
                    });
        }

        voz = findViewById(R.id.voz);
        glosario = findViewById(R.id.glosario);
        lecciones = findViewById(R.id.lecciones);
        matematicas = findViewById(R.id.matematicas);
        espa = findViewById(R.id.espa);

        storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference().child( "busqueda.gif");
        storageRef.getDownloadUrl().addOnSuccessListener(uri -> {
            Glide.with(Menu.this)
                    .asGif()  // Indica que es un GIF
                    .load(uri)  // Usa directamente la URI
                    .into(voz); // Cargar en el ImageButton
        }).addOnFailureListener(e -> {
            Log.e("MenuActivity", "Error al cargar GIF", e);
        });

        StorageReference storageRef2 = storage.getReference().child( "lecciones.gif");
        storageRef2.getDownloadUrl().addOnSuccessListener(uri -> {
            Glide.with(Menu.this)
                    .asGif()  // Indica que es un GIF
                    .load(uri)  // Usa directamente la URI
                    .into(lecciones); // Cargar en el ImageButton
        }).addOnFailureListener(e -> {
            Log.e("MenuActivity", "Error al cargar GIF", e);
        });

        StorageReference storageRef3 = storage.getReference().child( "glosario.gif");
        storageRef3.getDownloadUrl().addOnSuccessListener(uri -> {
            Glide.with(Menu.this)
                    .asGif()  // Indica que es un GIF
                    .load(uri)  // Usa directamente la URI
                    .into(glosario); // Cargar en el ImageButton
        }).addOnFailureListener(e -> {
            Log.e("MenuActivity", "Error al cargar GIF", e);
        });

        StorageReference storageRef4 = storage.getReference().child( "matematicas.gif");
        storageRef4.getDownloadUrl().addOnSuccessListener(uri -> {
            Glide.with(Menu.this)
                    .asGif()  // Indica que es un GIF
                    .load(uri)  // Usa directamente la URI
                    .into(matematicas); // Cargar en el ImageButton
        }).addOnFailureListener(e -> {
            Log.e("MenuActivity", "Error al cargar GIF", e);
        });

        StorageReference storageRef5 = storage.getReference().child( "espanol.gif");
        storageRef5.getDownloadUrl().addOnSuccessListener(uri -> {
            Glide.with(Menu.this)
                    .asGif()  // Indica que es un GIF
                    .load(uri)  // Usa directamente la URI
                    .into(espa); // Cargar en el ImageButton
        }).addOnFailureListener(e -> {
            Log.e("MenuActivity", "Error al cargar GIF", e);
        });



        // CERRAR SESION
        cerrarsesion = findViewById(R.id.cerrarsesion);
        cerrarsesion.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(Menu.this, MainActivity2.class));
            finish();
        });

        // BUSQUEDA POR VOZ

        voz.setOnClickListener(view -> {
            startActivity(new Intent(Menu.this, busqueda.class));
        });

        //GLOSARIO

        glosario.setOnClickListener(view -> {
            startActivity(new Intent(Menu.this, Diccionario.class));
        });

        lecciones.setOnClickListener(view -> {
            startActivity(new Intent(Menu.this, Lecciones.class));
        });

        matematicas.setOnClickListener(view -> {
            startActivity(new Intent(Menu.this, NumerosON.class));
        });
        espa.setOnClickListener(view -> {
            startActivity(new Intent(Menu.this, Lecturas.class));
        });

    }
}