package com.example.tabs;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Locale;

public class AdjetivosON extends AppCompatActivity {

    private TextToSpeech tts;
    FirebaseStorage storage;
    StorageReference storageRef;
    private HashMap<String, String> adjetivosMap;
    Button button6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adjetivos_on);
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

        storage = FirebaseStorage.getInstance();

        adjetivosMap = new HashMap<>();
        adjetivosMap.put("mio", "no");
        adjetivosMap.put("tuyo", "mo");
        adjetivosMap.put("suyo", "i");
        adjetivosMap.put("nuestro", "too");
        adjetivosMap.put("suyoellos", "in");
        adjetivosMap.put("suyoustedes", "nanmo, o, anmo");

        for (String adjetivo : adjetivosMap.keySet()) {
            int imageButtonId = getResources().getIdentifier("image" + adjetivo, "id", getPackageName());

            if (imageButtonId != 0) {
                ImageButton imageButton = findViewById(imageButtonId);

                if (imageButton != null) {

                    StorageReference storageRef = storage.getReference().child(adjetivo + ".png");
                    storageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                        Glide.with(AdjetivosON.this)
                                .load(uri.toString())
                                .into(imageButton);
                    });


                    imageButton.setOnClickListener(v -> {
                        tts.speak(adjetivosMap.get(adjetivo), TextToSpeech.QUEUE_FLUSH, null, null);
                    });
                }
            }
        }

        button6 = findViewById(R.id.button3);
        button6.setOnClickListener(view -> {
            Intent intent = new Intent(AdjetivosON.this, Diccionario.class);
            startActivity(intent);
        });

    }
}