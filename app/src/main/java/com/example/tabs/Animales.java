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

public class Animales extends AppCompatActivity {

    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_animales);
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

        findViewById(R.id.imageButton).setOnClickListener(v -> {
            tts.speak("cuauhtli", TextToSpeech.QUEUE_FLUSH, null);
        });

        findViewById(R.id.imageButton2).setOnClickListener(v -> {
            tts.speak("axolotl", TextToSpeech.QUEUE_FLUSH, null);
        });

        findViewById(R.id.imageButton5).setOnClickListener(v -> {
            tts.speak("ichcatl", TextToSpeech.QUEUE_FLUSH, null);
        });

        findViewById(R.id.imageButton6).setOnClickListener(v -> {
            tts.speak("cahuayo", TextToSpeech.QUEUE_FLUSH, null);
        });

        findViewById(R.id.imageButton7).setOnClickListener(v -> {
            tts.speak("chapolin", TextToSpeech.QUEUE_FLUSH, null);
        });

        findViewById(R.id.imageButton8).setOnClickListener(v -> {
            tts.speak("huitzlin", TextToSpeech.QUEUE_FLUSH, null);
        });

        findViewById(R.id.imageButton9).setOnClickListener(v -> {
            tts.speak("tochtli", TextToSpeech.QUEUE_FLUSH, null);
        });

        findViewById(R.id.imageButton10).setOnClickListener(v -> {
            tts.speak("coyotl", TextToSpeech.QUEUE_FLUSH, null);
        });

        findViewById(R.id.imageButton11).setOnClickListener(v -> {
            tts.speak("pinacatl", TextToSpeech.QUEUE_FLUSH, null);
        });

        findViewById(R.id.imageButton12).setOnClickListener(v -> {
            tts.speak("cuanaca", TextToSpeech.QUEUE_FLUSH, null);
        });

        findViewById(R.id.imageButton13).setOnClickListener(v -> {
            tts.speak("mizton", TextToSpeech.QUEUE_FLUSH, null);
        });

        findViewById(R.id.imageButton14).setOnClickListener(v -> {
            tts.speak("alo", TextToSpeech.QUEUE_FLUSH, null);
        });

        findViewById(R.id.imageButton15).setOnClickListener(v -> {
            tts.speak("huehxolotl", TextToSpeech.QUEUE_FLUSH, null);
        });

        findViewById(R.id.imageButton16).setOnClickListener(v -> {
            tts.speak("totolin", TextToSpeech.QUEUE_FLUSH, null);
        });

        findViewById(R.id.imageButton17).setOnClickListener(v -> {
            tts.speak("ocuilin", TextToSpeech.QUEUE_FLUSH, null);
        });

        findViewById(R.id.imageButton18).setOnClickListener(v -> {
            tts.speak("azcatl", TextToSpeech.QUEUE_FLUSH, null);
        });

        findViewById(R.id.imageButton19).setOnClickListener(v -> {
            tts.speak("mapach", TextToSpeech.QUEUE_FLUSH, null);
        });

        findViewById(R.id.imageButton20).setOnClickListener(v -> {
            tts.speak("ocelotl", TextToSpeech.QUEUE_FLUSH, null);
        });

        findViewById(R.id.imageButton21).setOnClickListener(v -> {
            tts.speak("tototl", TextToSpeech.QUEUE_FLUSH, null);
        });

        findViewById(R.id.imageButton22).setOnClickListener(v -> {
            tts.speak("canauhtli", TextToSpeech.QUEUE_FLUSH, null);
        });

        findViewById(R.id.imageButton23).setOnClickListener(v -> {
            tts.speak("chichi", TextToSpeech.QUEUE_FLUSH, null);
        });

        findViewById(R.id.imageButton24).setOnClickListener(v -> {
            tts.speak("michin", TextToSpeech.QUEUE_FLUSH, null);
        });

        findViewById(R.id.imageButton25).setOnClickListener(v -> {
            tts.speak("pitzotl", TextToSpeech.QUEUE_FLUSH, null);
        });

        findViewById(R.id.imageButton26).setOnClickListener(v -> {
            tts.speak("tecpiin", TextToSpeech.QUEUE_FLUSH, null);
        });

        findViewById(R.id.imageButton27).setOnClickListener(v -> {
            tts.speak("miztli", TextToSpeech.QUEUE_FLUSH, null);
        });

        findViewById(R.id.imageButton28).setOnClickListener(v -> {
            tts.speak("cueyatl", TextToSpeech.QUEUE_FLUSH, null);
        });

        findViewById(R.id.imageButton29).setOnClickListener(v -> {
            tts.speak("quimichin", TextToSpeech.QUEUE_FLUSH, null);
        });

        findViewById(R.id.imageButton30).setOnClickListener(v -> {
            tts.speak("cohuatl", TextToSpeech.QUEUE_FLUSH, null);
        });

        findViewById(R.id.imageButton31).setOnClickListener(v -> {
            tts.speak("tecolotl", TextToSpeech.QUEUE_FLUSH, null);
        });

        findViewById(R.id.imageButton32).setOnClickListener(v -> {
            tts.speak("tlacuatl", TextToSpeech.QUEUE_FLUSH, null);
        });

        findViewById(R.id.imageButton33).setOnClickListener(v -> {
            tts.speak("ayotl", TextToSpeech.QUEUE_FLUSH, null);
        });

        findViewById(R.id.imageButton34).setOnClickListener(v -> {
            tts.speak("cuacuaheh", TextToSpeech.QUEUE_FLUSH, null);
        });

        findViewById(R.id.imageButton35).setOnClickListener(v -> {
            tts.speak("mazatl", TextToSpeech.QUEUE_FLUSH, null);
        });

        findViewById(R.id.imageButton36).setOnClickListener(v -> {
            tts.speak("tzopilotl", TextToSpeech.QUEUE_FLUSH, null);
        });

        Button regresar = findViewById(R.id.button6);
        regresar.setOnClickListener(view -> {
            Intent intent = new Intent(Animales.this, Glosario.class);
            startActivity(intent);
        });
    }
}