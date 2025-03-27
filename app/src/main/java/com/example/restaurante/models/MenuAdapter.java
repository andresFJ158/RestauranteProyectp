package com.example.restaurante.models;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MenuAdapter extends ArrayAdapter<Object> {
    private List<Object> originalItems;
    private List<Object> filteredItems;
    public MenuAdapter(Context context, List<Object> items) {
        super(context, 0, items);
        this.originalItems = new ArrayList<>(items);
        this.filteredItems = items;
    }
    @Override
    public int getCount() {
        return filteredItems.size();
    }

    @Override
    public Object getItem(int position) {
        return filteredItems.get(position);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Object item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_multiple_choice, parent, false);
        }

        TextView textView = convertView.findViewById(android.R.id.text1);

        if (item instanceof Plato) {
            Plato plato = (Plato) item;
            if(plato.getStock() >= 10){
                textView.setText("🍽 " + plato.getNombre() + " - BS" + plato.getPrecio());
                textView.setTextColor(Color.parseColor("#00796B"));
            }
            else if (plato.getStock() >= 5 && plato.getStock() < 10 )
            {
                textView.setText("🍽 " + plato.getNombre() + " - BS" + plato.getPrecio());
                textView.setTextColor(Color.parseColor("#f3e755"));
            }
            else
            {
                textView.setText("🍽 " + plato.getNombre() + " - BS" + plato.getPrecio());
                textView.setTextColor(Color.parseColor("#FF5733"));
            }

        } else if (item instanceof Bebida) {
            Bebida bebida = (Bebida) item;
            textView.setText("🥤 " + bebida.getNombre() + " - BS" + bebida.getPrecio());
        }

        return convertView;
    }
    public void filter(String query) {
        query = query.toLowerCase().trim();
        filteredItems.clear();

        if (query.isEmpty()) {
            filteredItems.addAll(originalItems);
        } else {
            for (Object item : originalItems) {
                if (item instanceof Plato && ((Plato) item).getNombre().toLowerCase().contains(query)) {
                    filteredItems.add(item);
                } else if (item instanceof Bebida && ((Bebida) item).getNombre().toLowerCase().contains(query)) {
                    filteredItems.add(item);
                }
            }
        }

        notifyDataSetChanged();
    }
}
