package com.example.autenticator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registrar extends AppCompatActivity {

    EditText autnombre, autemail,autpass, auttelef;
    Button btn1;
    FirebaseAuth fAuth;
    TextView logintxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);


        autemail = findViewById (R.id.autEmail);
        autpass = findViewById (R.id.autPass);
        auttelef = findViewById (R.id.autTelef);
        btn1 = findViewById (R.id.btn1);
        logintxt = findViewById (R.id.autLogin);


        fAuth =FirebaseAuth.getInstance();
        if(fAuth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email =autemail.getText().toString().trim();
                String password =autpass.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    autemail.setError("Ingrese el nombre de su mascota");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    autpass.setError("Ingrese la raza de su mascota");
                    return;
                }
                if(password.length()<8){
                    autpass.setError("La clave debe ser mas larga");
                    return;
                }

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Registrar.this,"Mascota creado exitosamente", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }else{
                            Toast.makeText(Registrar.this,"No sempudo crear a la mascota", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


    }
}