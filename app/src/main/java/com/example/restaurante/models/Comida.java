package com.example.restaurante.models;

public class Comida {
    private String nombre;
    private double precio;


    private int stock;

    public Comida(String nombre, double precio, int stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }
    public int getStock() {return stock;}

}
