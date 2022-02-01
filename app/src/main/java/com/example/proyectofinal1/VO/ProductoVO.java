package com.example.proyectofinal1.VO;

public class ProductoVO {

    private int id;
    private String nombre;
    private String description;
    private Double precio;
    private String categoria;

    public ProductoVO() {
    }

    public ProductoVO(int id, String nombre, String description, Double precio, String categoria) {
        this.id = id;
        this.nombre = nombre;
        this.description = description;
        this.precio = precio;
        this.categoria = categoria;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "ProductoVO{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", description='" + description + '\'' +
                ", precio=" + precio +
                ", categoria='" + categoria + '\'' +
                '}';
    }
}

