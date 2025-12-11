package com.example.tabs;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.tabs.ui.Pregunta;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Leccion1 extends AppCompatActivity {

    private FirebaseFirestore db;
    private List<Pregunta> listaPreguntas;
    private int preguntaActual = 0;

    private TextView tvPregunta;
    private ImageView imgPregunta;
    private EditText etRespuesta;
    private Button btnSiguiente;

    private String lessonId ; // ID de la lección

    ImageButton btnHome,nav_voz,nav_lecciones,nav_matematicas,nav_glosario,nav_espanol;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_leccion1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


        btnHome = findViewById(R.id.btnHome);
        nav_voz = findViewById(R.id.nav_voz);
        nav_lecciones = findViewById(R.id.nav_lecciones);
        nav_matematicas = findViewById(R.id.nav_matematicas);
        nav_glosario = findViewById(R.id.nav_glosario);
        nav_espanol = findViewById(R.id.nav_espanol);

        lessonId = getIntent().getStringExtra("lessonId");

        if (lessonId == null) {
            Toast.makeText(this, "Error: No se encontró la lección", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Inicializar Firebase
        db = FirebaseFirestore.getInstance();
        listaPreguntas = new ArrayList<>();

        // Referencias a los elementos de la UI
        tvPregunta = findViewById(R.id.tvPregunta);
        imgPregunta = findViewById(R.id.imgPregunta);
        etRespuesta = findViewById(R.id.etRespuesta);
        btnSiguiente = findViewById(R.id.btnSiguiente);

        // Cargar preguntas desde Firestore
        cargarPreguntas();

        // Acción al presionar "Siguiente"
        btnSiguiente.setOnClickListener(view -> validarRespuesta());

        // Acción al presionar "Volver"
        Button btnVolver = findViewById(R.id.btnvolver);
        btnVolver.setOnClickListener(view -> {
            Intent intent = new Intent(Leccion1.this, Lecciones.class);
            startActivity(intent);
        });

        btnHome.setOnClickListener(view -> {
            Intent intent = new Intent(Leccion1.this, Menu.class);
            startActivity(intent);
        });

        nav_voz.setOnClickListener(view -> {
            Intent intent = new Intent(Leccion1.this, busqueda.class);
            startActivity(intent);
        });

        nav_lecciones.setOnClickListener(view -> {
            Intent intent = new Intent(Leccion1.this, Lecciones.class);
            startActivity(intent);
        });

        nav_matematicas.setOnClickListener(view -> {
            Intent intent = new Intent(Leccion1.this, NumerosON.class);
            startActivity(intent);
        });

        nav_glosario.setOnClickListener(view -> {
            Intent intent = new Intent(Leccion1.this, Glosario.class);
            startActivity(intent);
        });

        nav_espanol.setOnClickListener(view -> {
            Intent intent = new Intent(Leccion1.this, Lecturas.class);
            startActivity(intent);
        });

    }

    private void cargarPreguntas() {
        db.collection("lecciones").document(lessonId)
                .collection("preguntas")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    for (DocumentSnapshot doc : queryDocumentSnapshots) {
                        Pregunta pregunta = doc.toObject(Pregunta.class);
                        listaPreguntas.add(pregunta);
                    }
                    if (!listaPreguntas.isEmpty()) {
                        mostrarPregunta(); // Mostrar la primera pregunta
                    }
                })
                .addOnFailureListener(e -> Log.e("Firestore", "Error al cargar preguntas", e));
    }

    private void mostrarPregunta() {
        if (preguntaActual < listaPreguntas.size()) {
            Pregunta pregunta = listaPreguntas.get(preguntaActual);
            tvPregunta.setText(pregunta.getPregunta());
            etRespuesta.setText("");

            if ("imagen".equals(pregunta.getTipo())) {
                imgPregunta.setVisibility(View.VISIBLE);
                Glide.with(this).load(pregunta.getImagenUrl()).into(imgPregunta);
            } else {
                imgPregunta.setVisibility(View.GONE);
            }
        } else {
            finalizarLeccion();
        }
    }

    private void validarRespuesta() {
        Pregunta pregunta = listaPreguntas.get(preguntaActual);
        String respuestaUsuario = etRespuesta.getText().toString().trim();
        if (!respuestaUsuario.isEmpty()) {
            if (respuestaUsuario.equalsIgnoreCase(pregunta.getRespuesta())) {
                preguntaActual++; // Avanzar a la siguiente pregunta
                mostrarPregunta();
            } else {
                Toast.makeText(this, "Respuesta incorrecta, intenta de nuevo", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Escribe una respuesta", Toast.LENGTH_SHORT).show();
        }
    }

    private void finalizarLeccion() {
        Toast.makeText(this, "Lección completada", Toast.LENGTH_LONG).show();
        guardarProgresoUsuario();
        finish(); // Cerrar la actividad
    }

    private void guardarProgresoUsuario() {
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DocumentReference userRef = db.collection("usuario").document(userId);

        // Verificar si el usuario tiene el campo 'completedLessons'
        userRef.get().addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot.exists()) {
                if (documentSnapshot.contains("completedLessons")) {
                    // Si el campo existe, agregar la lección completada
                    userRef.update("completedLessons", FieldValue.arrayUnion(lessonId))
                            .addOnSuccessListener(aVoid -> Log.d("Firestore", "Lección completada"))
                            .addOnFailureListener(e -> Log.e("Firestore", "Error al guardar progreso", e));
                } else {
                    // Si el campo no existe, crearlo con la lección completada
                    Map<String, Object> data = new HashMap<>();
                    data.put("completedLessons", Arrays.asList(lessonId));

                    userRef.set(data, SetOptions.merge())
                            .addOnSuccessListener(aVoid -> Log.d("Firestore", "Campo 'completedLessons' creado"))
                            .addOnFailureListener(e -> Log.e("Firestore", "Error al crear el campo", e));
                }
            }
        }).addOnFailureListener(e -> Log.e("Firestore", "Error al obtener usuario", e));
    }
}