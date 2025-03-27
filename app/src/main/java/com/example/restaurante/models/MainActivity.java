package com.example.restaurante.models;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.restaurante.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView textViewTotalVendido;
    private Restaurante restaurante;
    private ListView listViewMenu;
    private Spinner spinnerTipoVenta;
    private EditText editTextNumeroMesa;
    private LinearLayout layoutMesa;
    private TextView textViewResumen;
    private List<Object> menuItems;
    private MenuAdapter menuAdapter;
    private TextView textContPlatos;
    private TextView textContBebidas;
    private SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        restaurante = new Restaurante();

        // Referencias UI
        spinnerTipoVenta = findViewById(R.id.spinnerTipoVenta);
        editTextNumeroMesa = findViewById(R.id.editTextNumeroMesa);
        layoutMesa = findViewById(R.id.layoutMesa);
        listViewMenu = findViewById(R.id.listViewMenu);
        textViewResumen = findViewById(R.id.textViewResumen);
        Button btnRegistrarVenta = findViewById(R.id.btnRegistrarVenta);
        textViewTotalVendido = findViewById(R.id.textViewTotalVendido);
        textContPlatos = findViewById(R.id.ContPlatos);
        textContBebidas = findViewById(R.id.ContBebidas);
        searchView = findViewById(R.id.searchView);

        // Configurar Spinner
        spinnerTipoVenta.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) { // Mesa
                    layoutMesa.setVisibility(View.VISIBLE);
                } else {
                    layoutMesa.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        // Cargar Menú
        cargarMenu();
        VerfStock();

        // Configurar Botón
        btnRegistrarVenta.setOnClickListener(view -> registrarVenta());

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                menuAdapter.filter(newText);
                return true;
            }
        });
    }

    private void cargarMenu() {
        menuItems = new ArrayList<>();
        menuItems.addAll(restaurante.getMenu().getPlatos());
        menuItems.addAll(restaurante.getMenu().getBebidas());

        menuAdapter = new MenuAdapter(this, menuItems);
        listViewMenu.setAdapter(menuAdapter);
    }

    private void registrarVenta() {
        String tipoVenta = spinnerTipoVenta.getSelectedItem().toString();
        int numeroMesa = 0;
        if (tipoVenta.equals("Mesa")) {
            try {
                numeroMesa = Integer.parseInt(editTextNumeroMesa.getText().toString());
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Por favor ingrese un número de mesa válido.", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        List<Plato> platosSeleccionados = new ArrayList<>();
        List<Bebida> bebidasSeleccionadas = new ArrayList<>();

        // Obtener elementos seleccionados
        for (int i = 0; i < listViewMenu.getCount(); i++) {
            if (listViewMenu.isItemChecked(i)) {
                Object item = menuItems.get(i);
                if (item instanceof Plato) {
                    platosSeleccionados.add((Plato) item);
                } else if (item instanceof Bebida) {
                    bebidasSeleccionadas.add((Bebida) item);
                }
            }
        }

        if (platosSeleccionados.isEmpty() && bebidasSeleccionadas.isEmpty()) {
            Toast.makeText(this, "Por favor seleccione al menos un plato o bebida.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Registrar Venta y Calcular Total
        double totalVenta = restaurante.registrarVenta(tipoVenta, numeroMesa, platosSeleccionados, bebidasSeleccionadas);

        // Actualizar Resumen
        textViewResumen.setText("Venta registrada con éxito. Total: BS" + totalVenta);

        // Actualizar Total Vendido
        double totalVendido = restaurante.getTotalVendido();
        textViewTotalVendido.setText("Total Vendido: BS" + totalVendido);

        // Limpiar selección del ListView para nueva venta
        listViewMenu.clearChoices();
        menuAdapter.notifyDataSetChanged();

        // Limpiar número de mesa
        editTextNumeroMesa.setText("");
        VerfStock();
    }


    private void VerfStock()
    {
        textContPlatos.setText("Platos del Menú: "+String.valueOf(restaurante.getMenu().ContPlatos()));
        textContBebidas.setText("Bebidas del Menú: "+String.valueOf(restaurante.getMenu().ContBebidas()));

        int platos = restaurante.getMenu().ContPlatos();
        int bebidas = restaurante.getMenu().ContBebidas();
        if(platos >= 10)
            textContPlatos.setTextColor(Color.parseColor("#00796B"));
        else if (platos >= 5 && platos < 10 )
            textContPlatos.setTextColor(Color.parseColor("#f3e755"));
        else
            textContPlatos.setTextColor(Color.parseColor("#FF5733"));

        if(bebidas >= 10)
            textContBebidas.setTextColor(Color.parseColor("#00796B"));
        else if (bebidas >= 5 && platos < 10 )
            textContBebidas.setTextColor(Color.parseColor("#f3e755"));
        else
            textContBebidas.setTextColor(Color.parseColor("#FF5733"));

    }
}