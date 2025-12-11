package com.example.tabs;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class Pronombres extends AppCompatActivity {

    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pronombres);
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
                if(status != TextToSpeech.ERROR){

                }
            }
        });

        Locale locale = new Locale("es", "MX");
        tts.setLanguage(locale);

        findViewById(R.id.imageButton13).setOnClickListener(v ->{
            tts.speak("ne, o, nehuatl", TextToSpeech.QUEUE_FLUSH, null);
        });
        findViewById(R.id.imageButton14).setOnClickListener(v ->{
            tts.speak("te, o, tehuatl", TextToSpeech.QUEUE_FLUSH, null);
        });
        findViewById(R.id.imageButton15).setOnClickListener(v ->{
            tts.speak("ye, o, yehuatl", TextToSpeech.QUEUE_FLUSH, null);
        });
        findViewById(R.id.imageButton16).setOnClickListener(v ->{
            tts.speak("tehuan", TextToSpeech.QUEUE_FLUSH, null);
        });
        findViewById(R.id.imageButton17).setOnClickListener(v ->{
            tts.speak("tehuatzin", TextToSpeech.QUEUE_FLUSH, null);
        });
        findViewById(R.id.imageButton18).setOnClickListener(v ->{
            tts.speak("nemehuan, o, amehuan", TextToSpeech.QUEUE_FLUSH, null);
        });
        findViewById(R.id.imageButton19).setOnClickListener(v ->{
            tts.speak("yehuan, o, yehuantin", TextToSpeech.QUEUE_FLUSH, null);
        });

        findViewById(R.id.button3).setOnClickListener(v ->{
            Intent intent = new Intent(Pronombres.this, Glosario.class);
            startActivity(intent);
        });
    }
}