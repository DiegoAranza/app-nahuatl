package com.example.tabs;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class Colores extends AppCompatActivity {

    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_colores);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {

                }
            }
        });

        Locale locale = new Locale("en", "US");
        tts.setLanguage(locale);

        findViewById(R.id.imageButton41).setOnClickListener(v -> {
            tts.speak("coztic", TextToSpeech.QUEUE_FLUSH, null);
        });

        findViewById(R.id.imageButton42).setOnClickListener(v -> {
            tts.speak("yahuitl, o, maltlaltic", TextToSpeech.QUEUE_FLUSH, null);
        });

        findViewById(R.id.imageButton43).setOnClickListener(v -> {
            tts.speak("iztac", TextToSpeech.QUEUE_FLUSH, null);
        });

        findViewById(R.id.imageButton44).setOnClickListener(v -> {
            tts.speak("tlaltic, o, coyoctic", TextToSpeech.QUEUE_FLUSH, null);
        });

        findViewById(R.id.imageButton45).setOnClickListener(v -> {
            tts.speak("nextic", TextToSpeech.QUEUE_FLUSH, null);
        });

        findViewById(R.id.imageButton46).setOnClickListener(v -> {
            tts.speak("camohtic", TextToSpeech.QUEUE_FLUSH, null);
        });

        findViewById(R.id.imageButton47).setOnClickListener(v -> {
            tts.speak("tliltic", TextToSpeech.QUEUE_FLUSH, null);
        });

        findViewById(R.id.imageButton48).setOnClickListener(v -> {
            tts.speak("chichiltic", TextToSpeech.QUEUE_FLUSH, null);
        });

        findViewById(R.id.imageButton49).setOnClickListener(v -> {
            tts.speak("ixchichipahuac", TextToSpeech.QUEUE_FLUSH, null);
        });

        findViewById(R.id.imageButton50).setOnClickListener(v -> {
            tts.speak("xoxouhtic", TextToSpeech.QUEUE_FLUSH, null);
        });




        Button regresar = findViewById(R.id.button6);
        regresar.setOnClickListener(view -> {
            Intent intent = new Intent(Colores.this, Glosario.class);
            startActivity(intent);
        });
    }
}