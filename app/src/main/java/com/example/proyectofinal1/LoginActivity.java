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

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity {
    private Button btnRetroceder;
    private Button btnHome;
    private TextInputEditText usernameText;
    private TextInputEditText passwordText;
    private Button btngoogle;
    private ProgressBar loadingBl;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private final static int RC_SIGN_IN = 123;
    private GoogleApiClient googleApiClient;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        btnRetroceder = findViewById(R.id.retroceder);
        btnHome=findViewById(R.id.login_button);
        usernameText = findViewById(R.id.username_edit_text);
        passwordText = findViewById(R.id.password_edit_text);
        loadingBl = findViewById(R.id.progress_bar_loading);
        mAuth = FirebaseAuth.getInstance();

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingBl.setVisibility(View.VISIBLE);
                String username = usernameText.getText().toString();
                String password = passwordText.getText().toString();
                if(TextUtils.isEmpty(username) && TextUtils.isEmpty(password)){
                    Toast.makeText(LoginActivity.this, "Ingrese todos los datos", Toast.LENGTH_LONG).show();
                    return;
                }if(TextUtils.isEmpty(username)){
                    Toast.makeText(LoginActivity.this, "Ingrese usuario", Toast.LENGTH_LONG).show();
                    return;
                }if(TextUtils.isEmpty(password)){
                    Toast.makeText(LoginActivity.this, "Ingrese contraseña", Toast.LENGTH_LONG).show();
                    return;
                }

                else {
                    mAuth.signInWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                loadingBl.setVisibility(View.GONE);
                                Toast.makeText(LoginActivity.this, "Inicio de sesión correcto", Toast.LENGTH_LONG).show();
                                Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(i);
                                finish();
                            } else {
                                loadingBl.setVisibility(View.GONE);
                                Toast.makeText(LoginActivity.this, "Inicio de sesión incorrecto", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }

        });









        btnRetroceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRetrocederActivity();
            }
        });

    }
    public void openRetrocederActivity() {
        Intent intent = new Intent(this, InicioActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user != null) {
            Intent i = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(i);
            this.finish();
        }
    }



}
