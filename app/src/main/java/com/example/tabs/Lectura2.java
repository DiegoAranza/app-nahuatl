package com.example.tabs;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Lectura2 extends AppCompatActivity {

    ImageView imagen1, imagen2;
    private FirebaseStorage storage;
    private StorageReference storageRef;
    Button btnvolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lectura2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


        imagen1 = findViewById(R.id.imagelec2);
        imagen2 = findViewById(R.id.imagelec22);
        btnvolver = findViewById(R.id.btnvolver);

        storage = FirebaseStorage.getInstance();

        storageRef = storage.getReference().child("l2.png");
        storageRef.getDownloadUrl().addOnSuccessListener(uri -> {
            Glide.with(Lectura2.this)
                    .load(uri.toString())
                    .into(imagen1);
        });

        storageRef = storage.getReference().child("ln2.png");
        storageRef.getDownloadUrl().addOnSuccessListener(uri -> {
            Glide.with(Lectura2.this)
                    .load(uri.toString())
                    .into(imagen2);
        });

        btnvolver.setOnClickListener(view -> {
            Intent intent = new Intent(Lectura2.this, Lecturas.class);
            startActivity(intent);
        });
    }
}