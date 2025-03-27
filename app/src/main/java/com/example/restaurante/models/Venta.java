package com.example.restaurante.models;

import java.util.ArrayList;
import java.util.List;

public class Venta {
    private String tipo;
    private int numeroMesa;
    private List<Plato> platos;
    private List<Bebida> bebidas;

    public Venta(String tipo, int numeroMesa) {
        this.tipo = tipo;
        this.numeroMesa = numeroMesa;
        this.platos = new ArrayList<>();
        this.bebidas = new ArrayList<>();
    }

    public void agregarPlato(Plato plato) {
        platos.add(plato);
    }

    public void agregarBebida(Bebida bebida) {
        bebidas.add(bebida);
    }

    public double calcularTotal() {
        double total = 0;
        for (Plato p : platos) {
            total += p.getPrecio();
        }
        for (Bebida b : bebidas) {
            total += b.getPrecio();
        }
        return total;
    }

    public String getResumenVenta() {
        StringBuilder resumen = new StringBuilder();
        resumen.append("Tipo: ").append(tipo);
        if (tipo.equals("Mesa")) {
            resumen.append(" - Mesa: ").append(numeroMesa);
        }
        resumen.append("\nPlatos:\n");
        for (Plato p : platos) {
            resumen.append("- ").append(p.getNombre()).append(" - $").append(p.getPrecio()).append("\n");
        }
        resumen.append("Bebidas:\n");
        for (Bebida b : bebidas) {
            resumen.append("- ").append(b.getNombre()).append(" - $").append(b.getPrecio()).append("\n");
        }
        resumen.append("Total: $").append(calcularTotal());
        return resumen.toString();
    }
}

