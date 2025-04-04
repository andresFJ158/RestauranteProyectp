package com.example.restaurante.models;

import java.util.ArrayList;
import java.util.List;

public class Restaurante {

    private double totalVendido;
    private Menu menu;
    private List<Venta> ventas;

    public Restaurante() {
        this.menu = new Menu();
        this.ventas = new ArrayList<>();
        menu.agregarPlato("Hamburguesa", 10.0,"Tomate,Lechuga,Pan,Carne",12);
        menu.agregarPlato("Pizza", 48.0,"Peperoni,Queso,SalsaTomate",4);
        menu.agregarBebida("Coca Cola", 14.0,2,2);
        menu.agregarBebida("Jugo de Naranja", 5.0,1,10);
        menu.agregarPlato("Kepery", 10.0,"Tomate,Lechuga,Pan,Carne",12);

    }

    public Menu getMenu() {
        return menu;
    }

    public double registrarVenta(String tipoVenta, int numeroMesa, List<Plato> platos, List<Bebida> bebidas) {
        double totalVenta = 0.0;

        for (Plato plato : platos) {
            totalVenta += plato.getPrecio();
        }
        for (Bebida bebida : bebidas) {
            totalVenta += bebida.getPrecio();
        }

        totalVendido += totalVenta;

        return totalVenta;
    }

    public double getTotalVendido() {
        return totalVendido;
    }
}

