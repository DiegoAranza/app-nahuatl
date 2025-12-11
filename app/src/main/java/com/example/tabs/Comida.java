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

public class Comida extends AppCompatActivity {

    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_comida);
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

        findViewById(R.id.imageButton13).setOnClickListener(view ->{
            tts.speak("atoli", TextToSpeech.QUEUE_FLUSH, null);
        });

        findViewById(R.id.imageButton14).setOnClickListener(view ->{
            tts.speak("altilpozonali", TextToSpeech.QUEUE_FLUSH, null);
        });

        findViewById(R.id.imageButton15).setOnClickListener(view ->{
            tts.speak("pitzoehuatlehuatzali", TextToSpeech.QUEUE_FLUSH, null);
        });

        findViewById(R.id.imageButton16).setOnClickListener(view ->{
            tts.speak("yemoli", TextToSpeech.QUEUE_FLUSH, null);
        });

        findViewById(R.id.imageButton17).setOnClickListener(view ->{
            tts.speak("ahuacamoli", TextToSpeech.QUEUE_FLUSH, null);
        });

        findViewById(R.id.imageButton18).setOnClickListener(view ->{
            tts.speak("tlemoli", TextToSpeech.QUEUE_FLUSH, null);
        });

        findViewById(R.id.imageButton19).setOnClickListener(view ->{
            tts.speak("ayohuachmole", TextToSpeech.QUEUE_FLUSH, null);
        });

        findViewById(R.id.imageButton20).setOnClickListener(view ->{
            tts.speak("pioxcanatlehuatzali", TextToSpeech.QUEUE_FLUSH, null);
        });

        findViewById(R.id.imageButton21).setOnClickListener(view ->{
            tts.speak("cuacuahhnacatlehuatzali", TextToSpeech.QUEUE_FLUSH, null);
        });

        findViewById(R.id.imageButton22).setOnClickListener(view ->{
            tts.speak("tamali", TextToSpeech.QUEUE_FLUSH, null);
        });

        Button regresar = findViewById(R.id.button6);
        regresar.setOnClickListener(view -> {
            Intent intent = new Intent(Comida.this, Glosario.class);
            startActivity(intent);
        });
    }
}