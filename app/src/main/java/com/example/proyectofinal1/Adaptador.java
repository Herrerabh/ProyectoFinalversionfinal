package com.example.proyectofinal1;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder> {

    private List<CardView> mData;
    private LayoutInflater mInflater;
    private Context context;

    public Adaptador(List<CardView> itemLista, Context context){
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemLista;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.card_view, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.bindData(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iconoUsuario;
        TextView nombreUsuario,comentarioUsuario,fecha;

        ViewHolder(View itemView){
            super(itemView);
            iconoUsuario = itemView.findViewById(R.id.iconoUsuario);
            nombreUsuario = itemView.findViewById(R.id.nombreUsuario);
            comentarioUsuario = itemView.findViewById(R.id.comentarioUsuario);
            fecha = itemView.findViewById(R.id.fechaPublicacion);

        }

        void bindData(final CardView cardView){
            iconoUsuario.setColorFilter(Color.parseColor(cardView.getColor()), PorterDuff.Mode.SRC_IN);
            nombreUsuario.setText(cardView.getNombreUsuario());
            comentarioUsuario.setText(cardView.getComentarioUsuario());
            fecha.setText(cardView.getFechaPublicacion());
        }
    }

}
