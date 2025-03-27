package com.example.restaurante.models;

public class Bebida extends Comida {
    int litros = 0;
    public Bebida(String nombre, double precio,int litros, int stock) {
        super(nombre,precio,stock);
        this.litros = litros;
    }

}
