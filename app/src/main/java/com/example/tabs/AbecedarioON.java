package com.example.tabs;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class AbecedarioON extends AppCompatActivity {
    ImageView imagen;
    Button btnvolver;
    private FirebaseStorage storage;
    private StorageReference storageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_abecedario_on);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                    Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                    v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                    return insets;
                });
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


        ImageView imagen = findViewById(R.id.imageView2);
            Button btnvolver = findViewById(R.id.btnvolver);

            storage = FirebaseStorage.getInstance();
           storageRef = storage.getReference().child("abecedario.png");
           storageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                Glide.with(AbecedarioON.this)
                        .load(uri.toString())
                       .into(imagen);
            });

            btnvolver.setOnClickListener(view -> {
                Intent intent = new Intent(AbecedarioON.this, Diccionario.class);
                finish();
            });


        }
}