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

public class NumerosBasicos extends AppCompatActivity {
    private TextToSpeech tts;
    FirebaseStorage storage;
    StorageReference storageRef;
    private HashMap<String, String> basicosMap;
    Button button6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_numeros_basicos);
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

        basicosMap = new HashMap<>();
        basicosMap.put("cero", "atle");
        basicosMap.put("uno", "ce");
        basicosMap.put("dos", "ome");
        basicosMap.put("tres", "yeyi");
        basicosMap.put("cuatro", "nahui");
        basicosMap.put("cinco", "macuili");
        basicosMap.put("seis", "chicuace");
        basicosMap.put("siete", "chicome");
        basicosMap.put("ocho", "chicueyi");
        basicosMap.put("nueve", "chicnahui");
        basicosMap.put("diez", "mahtlactli");
        basicosMap.put("once", "mahtlactli ce");
        basicosMap.put("doce", "mahtlactli ome");
        basicosMap.put("trece", "mahtlactli yeyi");
        basicosMap.put("catorce", "mahtlactli nahui");
        basicosMap.put("quince", "caxtoli");
        basicosMap.put("dieciseis", "caxtoli ce");
        basicosMap.put("diecisiete", "caxtoli ome");
        basicosMap.put("dieciocho", "caxtoli yeyi");
        basicosMap.put("diecinueve", "caxtoli nahui");
        basicosMap.put("veinte", "ce poali, o, cempoali");

        for (String basico : basicosMap.keySet()) {
            int imageButtonId = getResources().getIdentifier("image" + basico, "id", getPackageName());

            if (imageButtonId != 0) {
                ImageButton imageButton = findViewById(imageButtonId);

                if (imageButton != null) {

                    StorageReference storageRef = storage.getReference().child(basico + ".png");
                    storageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                        Glide.with(NumerosBasicos.this)
                                .load(uri.toString())
                                .into(imageButton);
                    });


                    imageButton.setOnClickListener(v -> {
                        tts.speak(basicosMap.get(basico), TextToSpeech.QUEUE_FLUSH, null, null);
                    });
                }
            }
        }

        button6 = findViewById(R.id.btnvolver);
        button6.setOnClickListener(view -> {
            Intent intent = new Intent(NumerosBasicos.this, NumerosON.class);
            startActivity(intent);
        });
    }
}