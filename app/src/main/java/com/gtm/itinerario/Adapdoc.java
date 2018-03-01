package com.gtm.itinerario;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by PRACTICAS on 01/03/2016.
 */
public class Adapdoc extends BaseAdapter {
    private Context context;

    public Adapdoc(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return Menudoc.ITEMS.length;
    }

    @Override
    public Menu getItem(int position) {
        return Menu.ITEMS[position];
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.grid_item_doc, viewGroup, false);
        }

        ImageView imagenCoche = (ImageView) view.findViewById(R.id.imagen_coche1);
        TextView nombreCoche = (TextView) view.findViewById(R.id.nombre_coche1);

        final Menu item = getItem(position);
        imagenCoche.setImageResource(item.getIdDrawable());
        nombreCoche.setText(item.getNombre());

        return view;
    }


}
