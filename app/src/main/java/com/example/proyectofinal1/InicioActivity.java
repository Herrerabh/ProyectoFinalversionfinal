package com.example.proyectofinal1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class InicioActivity extends AppCompatActivity {

    private Button btnLogin;
    private Button btnRegister;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio_activity);
        btnLogin = findViewById(R.id.inicio_iniciarsesión);
        btnRegister=findViewById(R.id.inicio_registro);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLoginActivity();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRegistrarseActivity();
            }
        });
    }
    public void openLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    public void openRegistrarseActivity() {
        Intent intent = new Intent(this, RegistroActivity.class);
        startActivity(intent);
    }

}
