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

public class Numeros extends AppCompatActivity {

    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_numeros);
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

        findViewById(R.id.imageButton1).setOnClickListener(v -> {
            tts.speak("atle", TextToSpeech.QUEUE_FLUSH, null);
        });
        findViewById(R.id.imageButton2).setOnClickListener(v -> {
            tts.speak("ce cententl", TextToSpeech.QUEUE_FLUSH, null);
        });
        findViewById(R.id.imageButton3).setOnClickListener(v -> {
            tts.speak("ome", TextToSpeech.QUEUE_FLUSH, null);
        });
        findViewById(R.id.imageButton4).setOnClickListener(v -> {
            tts.speak("yeyi", TextToSpeech.QUEUE_FLUSH, null);
        });
        findViewById(R.id.imageButton5).setOnClickListener(v -> {
            tts.speak("nahui", TextToSpeech.QUEUE_FLUSH, null);
        });
        findViewById(R.id.imageButton6).setOnClickListener(v -> {
            tts.speak("macuili", TextToSpeech.QUEUE_FLUSH, null);
        });
        findViewById(R.id.imageButton7).setOnClickListener(v -> {
            tts.speak("chicuace", TextToSpeech.QUEUE_FLUSH, null);
        });
        findViewById(R.id.imageButton8).setOnClickListener(v -> {
            tts.speak("chicome", TextToSpeech.QUEUE_FLUSH, null);
        });
        findViewById(R.id.imageButton9).setOnClickListener(v -> {
            tts.speak("chicueyi", TextToSpeech.QUEUE_FLUSH, null);
        });
        findViewById(R.id.imageButton10).setOnClickListener(v -> {
            tts.speak("chicnahui", TextToSpeech.QUEUE_FLUSH, null);
        });
        findViewById(R.id.imageButton11).setOnClickListener(v -> {
            tts.speak("mahtlactli", TextToSpeech.QUEUE_FLUSH, null);
        });
        findViewById(R.id.imageButton12).setOnClickListener(v -> {
            tts.speak("mahtlactli ce", TextToSpeech.QUEUE_FLUSH, null);
        });
        findViewById(R.id.imageButton13).setOnClickListener(v -> {
            tts.speak("mahtlactli ome", TextToSpeech.QUEUE_FLUSH, null);
        });
        findViewById(R.id.imageButton14).setOnClickListener(v -> {
            tts.speak("mahtlactli yeyi", TextToSpeech.QUEUE_FLUSH, null);
        });
        findViewById(R.id.imageButton15).setOnClickListener(v -> {
            tts.speak("mahtlactli nahui", TextToSpeech.QUEUE_FLUSH, null);
        });
        findViewById(R.id.imageButton16).setOnClickListener(v -> {
            tts.speak("caxtolli", TextToSpeech.QUEUE_FLUSH, null);
        });
        findViewById(R.id.imageButton17).setOnClickListener(v -> {
            tts.speak("caxtolli ce", TextToSpeech.QUEUE_FLUSH, null);
        });
        findViewById(R.id.imageButton18).setOnClickListener(v -> {
            tts.speak("caxtolli ome", TextToSpeech.QUEUE_FLUSH, null);
        });
        findViewById(R.id.imageButton19).setOnClickListener(v -> {
            tts.speak("caxtolli yeyi", TextToSpeech.QUEUE_FLUSH, null);
        });
        findViewById(R.id.imageButton20).setOnClickListener(v -> {
            tts.speak("caxtolli nahui", TextToSpeech.QUEUE_FLUSH, null);
        });
        findViewById(R.id.imageButton21).setOnClickListener(v -> {
            tts.speak("cempoali", TextToSpeech.QUEUE_FLUSH, null);
        });
        Button regresar = findViewById(R.id.button6);
        regresar.setOnClickListener(view -> {
            Intent intent = new Intent(Numeros.this, Glosario.class);
            startActivity(intent);
        });
    }
}