package com.example.restaurante.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class MenuAdapter extends ArrayAdapter<Object> {

    public MenuAdapter(Context context, List<Object> items) {
        super(context, 0, items);
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
            textView.setText("🍽 " + plato.getNombre() + " - BS" + plato.getPrecio());
        } else if (item instanceof Bebida) {
            Bebida bebida = (Bebida) item;
            textView.setText("🥤 " + bebida.getNombre() + " - BS" + bebida.getPrecio());
        }

        return convertView;
    }
}
