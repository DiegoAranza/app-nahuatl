package com.example.tabs;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity3 extends AppCompatActivity {

    private EditText editTextnombre;
    private EditText editTextemail;
    private EditText editTextpassword;
    private Button btnRegistro;

    private String nombre="";
    private String email="";
    private String password="";

    FirebaseAuth Auth;
    DatabaseReference reference;
    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


        editTextnombre = findViewById(R.id.editTextnombre);
        editTextemail = findViewById(R.id.editTextemail);
        editTextpassword = findViewById(R.id.editTextpassword);
        btnRegistro = findViewById(R.id.btnRegistro);
        Auth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference();
        firestore = FirebaseFirestore.getInstance();

        btnRegistro.setOnClickListener(v -> {
            nombre= editTextnombre.getText().toString();
            email= editTextemail.getText().toString();
            password= editTextpassword.getText().toString();

            if(!nombre.isEmpty() && !email.isEmpty() && !password.isEmpty()){

                if (password.length()>=6){
                    registrar();
                }else{
                    Toast.makeText(MainActivity3.this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show();
                }

            }else{
                Toast.makeText(MainActivity3.this, "Llene todos los campos", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void registrar(){
        Auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    String id = Auth.getCurrentUser().getUid();

                    Map<String, Object> map = new HashMap<>();
                    map.put("id", id);
                    map.put("nombre", nombre);
                    map.put("email", email);
                    map.put("password", password);

                    firestore.collection("usuario").document(id).set(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                    //reference.child("usuarios").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            if(task2.isSuccessful()){
                                startActivity(new Intent(MainActivity3.this, Glosario.class));
                                Toast.makeText(MainActivity3.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                                finish();
                            }else{
                                Toast.makeText(MainActivity3.this, "Error al crear los datos", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else{
                    Toast.makeText(MainActivity3.this, "Error en el registro del usuario", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}