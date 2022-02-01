package com.example.proyectofinal1;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class ComentariosActivity extends AppCompatActivity{
    List<CardView> cardViews;
    private Button btnRetroceder;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comentarios_activity);
        btnRetroceder = findViewById(R.id.btnButonnConfiguration);
        init();
        btnRetroceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRetrocederActivity();
            }
        });
    }


    public void init(){
        cardViews = new ArrayList<>();
        cardViews.add(new CardView("#775447","LuisG","La pizza americana es muy sencilla de hacer","09/11/21"));
        cardViews.add(new CardView("#607D8B","Alejandra Parra","Recomiendo la receta de rocoto relleno del chef: Luis Masta","21/12/21"));
        cardViews.add(new CardView("#775447","Luisa Montalvo","Yo soy de Coruña, y es verdad que la gente confunde mucho el pulpo a la gallega \n" +
                "con el pulpo a feira. Según a quien preguntas te puede contestar que es lo mismo, o son distintos.\n","09/11/21"));
        cardViews.add(new CardView("#607D8B","Carlos Bances","Buenas! \n" +
                "Tengo un horno 100% vapor y no sé desenvolverme con el \uD83D\uDE14\n" +
                "Alguien me puede recomendar recetas ?","21/12/21"));
        cardViews.add(new CardView("#775447","Karla Ruiz","Hola, al hacer la receta de tiramisu, haciendo los mismos pasos, \n" +
                "cantidades iguales y los mismos productos, hay en ocasiones que la\n" +
                "crema del tiramisú ","09/11/21"));


        Adaptador adaptador = new Adaptador(cardViews, this);
        RecyclerView recyclerView = findViewById(R.id.lista_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adaptador);

    }
    public void openRetrocederActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
