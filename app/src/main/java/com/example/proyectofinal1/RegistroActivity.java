package com.example.proyectofinal1;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistroActivity extends AppCompatActivity {

    private Button btnRetroceder;
    private TextInputEditText usernameText;
    private TextInputEditText passwordText;
    private TextInputEditText confirmPasswordText;
    private ProgressBar loadingBl;
    private Button registerButton;
    private FirebaseAuth mAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_activity);
        btnRetroceder = findViewById(R.id.retroceder);
        btnRetroceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRetrocederActivity();
            }
        });
        usernameText = findViewById(R.id.username_edit_text);
        passwordText = findViewById(R.id.password_edit_text);
        confirmPasswordText = findViewById(R.id.password_confirmacion_edit_input);
        registerButton = findViewById(R.id.registrarse_button);
        loadingBl = findViewById(R.id.progress_bar_loading);
        mAuth = FirebaseAuth.getInstance();

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingBl.setVisibility(View.VISIBLE);
                String username = usernameText.getText().toString();
                String password = passwordText.getText().toString();
                String confirmPassword = confirmPasswordText.getText().toString();
                if(!password.equals(confirmPassword)){
                    Toast.makeText(RegistroActivity.this, "Ingrese datos iguales", Toast.LENGTH_LONG).show();
                } if(TextUtils.isEmpty(password)){
                    Toast.makeText(RegistroActivity.this, "Ingresar contraseña", Toast.LENGTH_LONG).show();
                }if(TextUtils.isEmpty(username)){
                    Toast.makeText(RegistroActivity.this, "Ingresar Usuario", Toast.LENGTH_LONG).show();
                }
                if (TextUtils.isEmpty(username) && TextUtils.isEmpty(password) && TextUtils.isEmpty(confirmPassword)) {
                    Toast.makeText(RegistroActivity.this, "Ingresar todos los datos", Toast.LENGTH_LONG).show();
                } else {
                    mAuth.createUserWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                loadingBl.setVisibility(View.GONE);
                                Toast.makeText(RegistroActivity.this, "Registro Correcto", Toast.LENGTH_LONG).show();
                                Intent i = new Intent(RegistroActivity.this, LoginActivity.class);
                                startActivity(i);
                                finish();
                            } else {
                                loadingBl.setVisibility(View.GONE);
                                Toast.makeText(RegistroActivity.this, "Registro Fallido!", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });
    }
        public void openRetrocederActivity() {
        Intent intent = new Intent(this, InicioActivity.class);
        startActivity(intent);
    }
}
