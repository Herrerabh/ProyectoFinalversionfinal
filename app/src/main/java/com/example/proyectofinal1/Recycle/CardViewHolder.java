package com.example.proyectofinal1.Recycle;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofinal1.R;


public class CardViewHolder extends RecyclerView.ViewHolder{

    public TextView id;
    public TextView prodName;
    public TextView prodDes;
    public TextView prodPreci;
    public TextView prodCategory;

    public CardViewHolder(@NonNull View view) {
        super(view);
        prodName = view.findViewById(R.id.nombre);
        prodPreci = view.findViewById(R.id.precio);
        prodDes = view.findViewById(R.id.descripcion);
        prodCategory = view.findViewById(R.id.categoria);

    }

}
