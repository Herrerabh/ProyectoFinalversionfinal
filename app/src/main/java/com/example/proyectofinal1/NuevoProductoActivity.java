package com.example.proyectofinal1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NuevoProductoActivity extends Activity {

    Button btnReturn,btnregistro;
    EditText nombre,description,precio,category;

    DBHelper db =new DBHelper(this,"DB1",null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_producto);

        btnReturn = findViewById(R.id.return_new_produc);
        btnregistro = findViewById(R.id.save_btn);

        nombre= findViewById(R.id.nombre_et_text_edit);
        description= findViewById(R.id.description_et_text_edit);
        precio= findViewById(R.id.precio_et_text_edit);
        category= findViewById(R.id.descripcion_et_text_edit);

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(NuevoProductoActivity.this,HomeActivity.class);

                startActivity(i);
                finish();
            }
        });

        btnregistro.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nom = nombre.getText().toString();
                String des = description.getText().toString();
                Double pre = Double.valueOf(precio.getText().toString());
                String cat = category.getText().toString();
                if (nom.length()>0) {

                    db.abrir();
                    db.insertProducto(nom, des, pre, cat);
                    db.cerrar();
                    Toast.makeText(NuevoProductoActivity.this, "Se registro con exito", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(NuevoProductoActivity.this, HomeActivity.class);
                    startActivity(i);

                }else {
                    Toast.makeText(NuevoProductoActivity.this,"Algo a fallado :'(, no deben aver casillas en blanco", Toast.LENGTH_SHORT).show();
                }
            }
        }));

    }

}
