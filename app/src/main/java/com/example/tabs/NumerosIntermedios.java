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

public class NumerosIntermedios extends AppCompatActivity {

    private TextToSpeech tts;
    FirebaseStorage storage;
    StorageReference storageRef;
    private HashMap<String, String> intermediosMap;
    Button button6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_numeros_intermedios);
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

        intermediosMap = new HashMap<>();
        intermediosMap.put("veinte", "ce poali, o, cempoali");
        intermediosMap.put("veintiuno", "ce poali ihuan ce");
        intermediosMap.put("veintidos", "ce poali ihuan ome");
        intermediosMap.put("veintitres", "ce poali ihuan yeyi");
        intermediosMap.put("veinticuatro", "ce poali ihuan nahui");
        intermediosMap.put("veinticinco", "ce poali ihuan macuili");
        intermediosMap.put("veintiseis", "ce poali ihuan chicuace");
        intermediosMap.put("veintisiete", "ce poali ihuan chicome");
        intermediosMap.put("veintiocho", "ce poali ihuan chicueyi");
        intermediosMap.put("veintinueve", "ce poali ihuan chicnahui");
        intermediosMap.put("treinta", "ce poali ihuan mahtlactli ");
        intermediosMap.put("treintayuno", "ce poali ihuan mahtlactli ce");
        intermediosMap.put("treintaydos", "ce poali ihuan mahtlactli ome");
        intermediosMap.put("treintaytres", "ce poali ihuan mahtlactli yeyi");
        intermediosMap.put("treintaycuatro", "ce poali ihuan mahtlactli nahui");
        intermediosMap.put("treintaycinco", "ce poali ihuan caxtoli");
        intermediosMap.put("treintayseis", "ce poali ihuan caxtoli ce");
        intermediosMap.put("treintaysiete", "ce poali ihuan caxtoli ome");
        intermediosMap.put("treintayocho", "ce poali ihuan caxtoli yeyi");
        intermediosMap.put("treintaynueve", "ce poali ihuan caxtoli nahui");
        intermediosMap.put("cuarenta", "ome cempoali");


        for (String intermedio : intermediosMap.keySet()) {
            int imageButtonId = getResources().getIdentifier("image" + intermedio, "id", getPackageName());

            if (imageButtonId != 0) {
                ImageButton imageButton = findViewById(imageButtonId);

                if (imageButton != null) {

                    StorageReference storageRef = storage.getReference().child(intermedio + ".png");
                    storageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                        Glide.with(NumerosIntermedios.this)
                                .load(uri.toString())
                                .into(imageButton);
                    });


                    imageButton.setOnClickListener(v -> {
                        tts.speak(intermediosMap.get(intermedio), TextToSpeech.QUEUE_FLUSH, null, null);
                    });
                }
            }
        }

        button6 = findViewById(R.id.btnvolver);
        button6.setOnClickListener(view -> {
            Intent intent = new Intent(NumerosIntermedios.this, NumerosON.class);
            startActivity(intent);
        });
    }
}