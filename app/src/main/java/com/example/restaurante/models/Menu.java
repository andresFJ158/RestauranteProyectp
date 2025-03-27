package com.example.restaurante.models;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private List<Plato> platos;
    private List<Bebida> bebidas;

    public Menu() {
        this.platos = new ArrayList<>();
        this.bebidas = new ArrayList<>();
    }

    public void agregarPlato(String nombre, double precio) {
        platos.add(new Plato(nombre, precio));
    }

    public void agregarBebida(String nombre, double precio) {
        bebidas.add(new Bebida(nombre, precio));
    }

    public List<Plato> getPlatos() {
        return platos;
    }

    public List<Bebida> getBebidas() {
        return bebidas;
    }
}
