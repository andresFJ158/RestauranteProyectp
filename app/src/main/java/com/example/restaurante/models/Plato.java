package com.example.restaurante.models;

public class Plato extends Comida {
    String ingredientes = "";
    public Plato(String nombre, double precio, String ingredientes, int stock) {
        super(nombre,precio,stock);
        this.ingredientes = ingredientes;
    }

}

