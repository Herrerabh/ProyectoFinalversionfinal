package com.example.proyectofinal1;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.annotation.Nullable;
import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class ConfigurationActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    FirebaseAuth mAuth;

    ImageView set;
    ProgressDialog pd;
    private static final int CAMERA_REQUEST = 100;
    private static final int STORAGE_REQUEST = 200;
    private static final int IMAGE_PICK_GALLERY_REQUEST = 300;
    private static final int IMAGE_PICK_CAMERA_REQUEST = 400;

    String cameraPermission[];
    String storagePermission[];
    Uri imageUri;
    String profileOrCoverImage;
    AppCompatButton editImage;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.configuration_activity);
        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);
        toolbar=findViewById(R.id.toolbar);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


        //
        editImage = findViewById(R.id.profile_edit);
        set = findViewById(R.id.profile_image);
        pd = new ProgressDialog(this);
        pd.setCanceledOnTouchOutside(false);
        cameraPermission = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermission = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};

        editImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.setMessage("actualizar Imagen");
                profileOrCoverImage = "image";
                showImagePicDialog();
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
                Intent b = new Intent(ConfigurationActivity.this, HomeActivity.class);
                startActivity(b);
                break;
            case R.id.nav_logout:
                mAuth = FirebaseAuth.getInstance();
                mAuth.signOut();
                Intent i = new Intent(ConfigurationActivity.this, InicioActivity.class);
                startActivity(i);
                break;
            case R.id.nav_configuration:
                Intent a = new Intent(ConfigurationActivity.this, ConfigurationActivity.class);
                startActivity(a);
                break;
            case R.id.comentarios_menu:
                Intent c = new Intent(ConfigurationActivity.this, ComentariosActivity.class);
                startActivity(c);
                break;
        }
        return true;
    }

    @Override
    protected  void onPause(){
        super.onPause();
        Glide.with(ConfigurationActivity.this).load(imageUri).into(set);

    }

    @Override
    public  void  onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == Activity.RESULT_OK){
            if(requestCode == IMAGE_PICK_GALLERY_REQUEST) {
                imageUri = data.getData();
                uploadProfileCoverPhoto(imageUri);
            }
            if(requestCode == IMAGE_PICK_CAMERA_REQUEST) {
                uploadProfileCoverPhoto(imageUri);
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public  void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CAMERA_REQUEST: {
                if(grantResults.length > 0) {
                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean writeStorageAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    if(cameraAccepted && writeStorageAccepted){
                        pickFromCamera();
                    } else {
                        Toast.makeText(this, "Permisos de camara", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(this, "Error reintente", Toast.LENGTH_LONG).show();
                }
            }
            break;
            case STORAGE_REQUEST: {
                if(grantResults.length > 0) {
                    boolean writeStorageAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if(writeStorageAccepted){
                        pickFromGallery();
                    } else {
                        Toast.makeText(this, "Permisos de memoria", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(this, "Error reintente", Toast.LENGTH_LONG).show();
                }
            }
            break;
        }
    }


    private void showImagePicDialog() {
        String options[] = {"Camera", "Gallery"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pick image from");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    if(!checkCameraPermission()) {
                        requestCameraPermission();
                    } else {
                        pickFromCamera();
                    }
                }
                else if(which == 1) {
                    if(!checkStoragePermission()) {
                        requestStoragePermission();
                    } else {
                        pickFromGallery();
                    }
                }
            }
        });
        builder.create().show();
    }

    private Boolean checkCameraPermission() {
        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == (PackageManager.PERMISSION_GRANTED);
        boolean result1 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);

        return result && result1;
    }

    private Boolean checkStoragePermission() {
        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        return  result;
    }

    private void requestCameraPermission() {
        requestPermissions(cameraPermission, CAMERA_REQUEST);
    }

    private void requestStoragePermission() {
        requestPermissions(storagePermission, STORAGE_REQUEST);
    }

    private void pickFromCamera() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Images.Media.TITLE, "Temp_pic");
        contentValues.put(MediaStore.Images.Media.DESCRIPTION, "Temp description");
        imageUri = this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(cameraIntent, IMAGE_PICK_CAMERA_REQUEST);
    }

    private void pickFromGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, IMAGE_PICK_GALLERY_REQUEST);
    }

    private void uploadProfileCoverPhoto(final Uri uri) {
        pd.show();
        // function to save in to database {}
        pd.dismiss();
        Toast.makeText(ConfigurationActivity.this, "Updated Photo", Toast.LENGTH_LONG).show();
    }
}
