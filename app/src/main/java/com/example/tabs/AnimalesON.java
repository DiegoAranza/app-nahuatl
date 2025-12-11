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

public class AnimalesON extends AppCompatActivity {

    private TextToSpeech tts;
    FirebaseStorage storage;
    StorageReference storageRef;
    private HashMap<String, String> animalesMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_animales_on);
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


        animalesMap = new HashMap<>();
        animalesMap.put("aguila", "cuauhtli");
        animalesMap.put("ajolote", "axolotl");
        animalesMap.put("borrego", "ichcatl");
        animalesMap.put("caballo", "cahuayo");
        animalesMap.put("chapulin", "chapolin");
        animalesMap.put("colibri", "huitzlin");
        animalesMap.put("conejo", "tochtli");
        animalesMap.put("coyote", "coyotl");
        animalesMap.put("escarabajo", "pinacatl");
        animalesMap.put("galloygallina", "cuanaca");
        animalesMap.put("gato", "mizton");
        animalesMap.put("guacamaya", "alo");
        animalesMap.put("guajolote", "huehxolotl");
        animalesMap.put("guajolota", "totolin");
        animalesMap.put("gusano", "ocuilin");
        animalesMap.put("hormiga", "azcatl");
        animalesMap.put("mapache", "mapach");
        animalesMap.put("ocelote", "ocelotl");
        animalesMap.put("pajaro", "tototl");
        animalesMap.put("pato", "canauhtli");
        animalesMap.put("perro", "chichi");
        animalesMap.put("pescado", "michin");
        animalesMap.put("puerco", "pitzotl");
        animalesMap.put("pulga", "tecpiin");
        animalesMap.put("puma", "miztli");
        animalesMap.put("rana", "cueyatl");
        animalesMap.put("raton", "quimichin");
        animalesMap.put("serpiente", "cohuatl");
        animalesMap.put("tecolote", "tecolotl");
        animalesMap.put("tlacuache", "tlacuatl");
        animalesMap.put("tortuga", "ayotl");
        animalesMap.put("vacaybuey", "cuacuaheh");
        animalesMap.put("venado", "mazatl");
        animalesMap.put("zopilote", "tzopilotl");


        for (String animal : animalesMap.keySet()) {
            int imageButtonId = getResources().getIdentifier("image" + animal, "id", getPackageName());
            if (imageButtonId != 0) {
                ImageButton imageButton = findViewById(imageButtonId);
                if (imageButton != null) {
                    StorageReference storageRef = storage.getReference().child(animal + ".png");
                    storageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                        Glide.with(AnimalesON.this)
                                .load(uri.toString())
                                .into(imageButton);
                    });
                    imageButton.setOnClickListener(v -> {
                            tts.speak(animalesMap.get(animal), TextToSpeech.QUEUE_FLUSH, null, null);
                    });
                }
            }
        }

        Button regresar = findViewById(R.id.button6);
        regresar.setOnClickListener(view -> {
            Intent intent = new Intent(AnimalesON.this, Diccionario.class);
            startActivity(intent);
        });
    }
}