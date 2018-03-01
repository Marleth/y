package com.gtm.itinerario;

/**
 * Created by PRACTICAS on 23/05/2016.
 */
public class Menuvia {
    private String nombrevia;
    private int idDrawablevia;

    public Menuvia(String nombre, int idDrawable) {
        this.nombrevia = nombre;
        this.idDrawablevia = idDrawable;
    }

    public String getNombre() {
        return nombrevia;
    }

    public int getIdDrawable() {
        return idDrawablevia;
    }

    public int getId() {
        return nombrevia.hashCode();
    }

    public static Menuvia[] ITEMS = {
            new Menuvia("Gastos", R.drawable.casa),
            new Menuvia("Factura",R.drawable.casa),
            new Menuvia("Historial",R.drawable.camara)
    };
    public static Menuvia getItem(int id) {
        for (Menuvia item1 : ITEMS) {
            if (item1.getId() == id) {
                return item1;
            }
        }
        return null;
    }
}
