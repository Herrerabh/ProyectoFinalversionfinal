package com.example.proyectofinal1;

public class CardView {
    public String color;
    public String nombreUsuario;
    public String comentarioUsuario;
    public String fechaPublicacion;

    public CardView(String color, String nombreUsuario, String comentarioUsuario, String fechaPublicacion) {
        this.color = color;
        this.nombreUsuario = nombreUsuario;
        this.comentarioUsuario = comentarioUsuario;
        this.fechaPublicacion = fechaPublicacion;
    }


    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getComentarioUsuario() {
        return comentarioUsuario;
    }

    public void setComentarioUsuario(String comentarioUsuario) {
        this.comentarioUsuario = comentarioUsuario;
    }

    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }
}
