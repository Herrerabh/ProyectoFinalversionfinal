package com.example.proyectofinal1;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofinal1.Recycle.ProductosAdapter;
import com.example.proyectofinal1.VO.ProductoVO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    FirebaseAuth mAuth;

    List<ProductoVO> elements;
    FloatingActionButton btnAgregar;
    DBHelper db =new DBHelper(this,"DB1",null,1);

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);
        toolbar=findViewById(R.id.toolbar);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        init();

        Toolbar myToolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(myToolbar);

        ArrayList<String> informacionLIst;
        ArrayList<ProductoVO> listaProduc;

        btnAgregar = findViewById(R.id.floatingActionButton);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this,NuevoProductoActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem){
        switch(menuItem.getItemId()) {
            case R.id.inicio_menu:
                break;
            case R.id.nav_logout:
                mAuth = FirebaseAuth.getInstance();
                mAuth.signOut();
                Intent i = new Intent(HomeActivity.this, InicioActivity.class);
                startActivity(i);
                break;
            case R.id.nav_configuration:
                Intent a = new Intent(HomeActivity.this, ConfigurationActivity.class);
                startActivity(a);
                break;
            case R.id.comentarios_menu:
                Intent b = new Intent(HomeActivity.this, ComentariosActivity.class);
                startActivity(b);
                break;
        }
        return true;
    }

    public void init(){
        elements = new ArrayList<>();
        db.abrir();
        elements = db.getAllProducto();

        if (elements.size()>0){
            ProductosAdapter productosAdapter= new ProductosAdapter(elements);

            RecyclerView recyclerView = findViewById(R.id.recycler_view);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(productosAdapter);
        }else{
            Toast.makeText(HomeActivity.this,"No se encuentra datos", Toast.LENGTH_SHORT).show();
        }

        db.cerrar();

    }
}

