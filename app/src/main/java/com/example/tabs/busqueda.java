package com.example.tabs;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Pattern;

public class busqueda extends AppCompatActivity {

    private static final int VOICE_RECOGNITION_REQUEST_CODE = 1;
    EditText editTextSearch;
    Button btnBuscarTexto, btnvolver;
    ImageButton btnBuscarVoz;
    ImageView imageResult;
    ImageButton nav_lecciones, nav_glosario, nav_voz, nav_matematicas, nav_espanol;
    ImageButton home;
    private FirebaseStorage storage;
    private StorageReference storageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_busqueda);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


        editTextSearch = findViewById(R.id.editTextSearch);
        btnBuscarTexto = findViewById(R.id.btnBuscarTexto);
        btnBuscarVoz = findViewById(R.id.btnBuscarVoz);
        nav_lecciones = findViewById(R.id.nav_lecciones);
        nav_glosario = findViewById(R.id.nav_glosario);
        nav_voz = findViewById(R.id.nav_voz);
        nav_matematicas = findViewById(R.id.nav_matematicas);
        nav_espanol = findViewById(R.id.nav_espanol);
        home = findViewById(R.id.btnHome);

        btnBuscarTexto.setOnClickListener(v -> buscarPorTexto());

        btnBuscarVoz.setOnClickListener(v -> iniciarReconocimientoVoz());

        imageResult = findViewById(R.id.imageResult);
        btnBuscarTexto.setOnClickListener(view -> {
            buscarPorTexto();
        });

        nav_lecciones.setOnClickListener(view -> {
            Intent intent = new Intent(busqueda.this, Lecciones.class);
            startActivity(intent);
        });

        nav_glosario.setOnClickListener(view -> {
            Intent intent = new Intent(busqueda.this, Diccionario.class);
            startActivity(intent);
        });

        nav_voz.setOnClickListener(view -> {
            Intent intent = new Intent(busqueda.this, busqueda.class);
            startActivity(intent);
        });

        nav_matematicas.setOnClickListener(view -> {
            Intent intent = new Intent(busqueda.this, NumerosON.class);
            startActivity(intent);
        });
        nav_espanol.setOnClickListener(view -> {
            Intent intent = new Intent(busqueda.this, Lecturas.class);
            startActivity(intent);
        });

        home.setOnClickListener(view -> {
            Intent intent = new Intent(busqueda.this, Menu.class);
            startActivity(intent);
        });
    }

    private void buscarPorTexto() {
        String texto = editTextSearch.getText().toString().trim();
        if (!texto.isEmpty()) {
            Toast.makeText(this, "Buscando: " + texto, Toast.LENGTH_SHORT).show();
            storage = FirebaseStorage.getInstance();
            storageRef = storage.getReference().child(texto+".png");
            storageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                Glide.with(busqueda.this)
                        .load(uri.toString())
                        .into(imageResult);
            }).addOnFailureListener(exception -> {
                Toast.makeText(this, "No se encontró la imagen en nuestra base de datos", Toast.LENGTH_SHORT).show();
            });
        } else {
            Toast.makeText(this, "Escribe algo para buscar", Toast.LENGTH_SHORT).show();
        }
    }

    private void iniciarReconocimientoVoz() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Habla ahora...");

        try {
            startActivityForResult(intent, VOICE_RECOGNITION_REQUEST_CODE);
        } catch (Exception e) {
            Toast.makeText(this, "No se pudo iniciar reconocimiento de voz", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == VOICE_RECOGNITION_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            String textoReconocido = result.get(0);
            editTextSearch.setText(textoReconocido);  // Mostrar el resultado en el EditText

            Toast.makeText(this, "Buscando: " + textoReconocido, Toast.LENGTH_SHORT).show();

            String palabraSinAcentos = quitarAcentos(textoReconocido).toLowerCase();

            //  Nombre de la imagen en Storage (ej: "aguila.png")
            String nombreImagen = palabraSinAcentos + ".png";

            storage = FirebaseStorage.getInstance();
            storageRef = storage.getReference().child(nombreImagen);
            storageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                Glide.with(busqueda.this)
                        .load(uri.toString())
                        .into(imageResult);
            }).addOnFailureListener(exception -> {
                Toast.makeText(this, "No se encontró la imagen en nuestra base de datos", Toast.LENGTH_SHORT).show();
            });

        }
    }

    private String quitarAcentos(String texto) {
        String normalizado = Normalizer.normalize(texto, Normalizer.Form.NFD);
        Pattern patron = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return patron.matcher(normalizado).replaceAll("");
    }
}