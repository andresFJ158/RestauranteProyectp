package com.example.restaurante.models;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private List<Plato> platos;
    private List<Bebida> bebidas;
    private List<Comida> comidas;

    public Menu() {
        this.platos = new ArrayList<>();
        this.bebidas = new ArrayList<>();
        this.comidas = new ArrayList<>();
    }

    public void agregarPlato(String nombre, double precio, String ingredientes, int stock) {
        platos.add(new Plato(nombre, precio,ingredientes, stock));
    }

    public void agregarBebida(String nombre, double precio, int litros, int stock) {
        bebidas.add(new Bebida(nombre, precio,litros,stock));
    }

    public List<Plato> getPlatos() {
        return platos;
    }

    public List<Bebida> getBebidas() {
        return bebidas;
    }
    public int ContPlatos()
    {
        return platos.size();
    }
    public int ContBebidas()
    {
        return bebidas.size();
    }
}
