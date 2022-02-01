package com.example.proyectofinal1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.proyectofinal1.VO.ProductoVO;

import java.util.ArrayList;
import java.util.List;


public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE producto(id INTEGER NOT NULL primary key AUTOINCREMENT, nombre TEXT NOT NULL, description TEXT, precio DOUBLE NOT NULL, categoria TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void abrir(){
        this.getWritableDatabase();
    }
    public void cerrar(){
        this.close();
    }

       public void insertProducto(String nom,String des, Double prec, String cat){
        ContentValues valores = new ContentValues();
        valores.put("nombre",nom);
        valores.put("description",des);
        valores.put("precio",prec);
        valores.put("categoria",cat);
        this.getWritableDatabase().insert("producto",null,valores);
    }



    public List<ProductoVO> getAllProducto() {
        List<ProductoVO> productoList = new ArrayList<ProductoVO>();
        String selectQuery = "SELECT * FROM producto";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {

            ProductoVO producto = new ProductoVO();

            producto.setId(Integer.parseInt(cursor.getString(0)));
            producto.setNombre(cursor.getString(1));
            producto.setDescription(cursor.getString(2));
            producto.setPrecio(cursor.getDouble(3));
            producto.setCategoria(cursor.getString(4));

            productoList.add(producto);
        }

        return productoList;
    }
}


