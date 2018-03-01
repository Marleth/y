package com.gtm.itinerario;


public class Menudoc {
    private String nombredoc;
    private int idDrawabledoc;

    public Menudoc(String nombre, int idDrawable) {
        this.nombredoc = nombre;
        this.idDrawabledoc = idDrawable;
    }

    public String getNombre() {
        return nombredoc;
    }

    public int getIdDrawable() {
        return idDrawabledoc;
    }

    public int getId() {
        return nombredoc.hashCode();
    }

    public static Menudoc[] ITEMS = {
            new Menudoc("Conductor", R.drawable.conductor),
            new Menudoc("Vehiculo",R.drawable.doc_vehi),
              };
    public static Menudoc getItem(int id) {
        for (Menudoc item1 : ITEMS) {
            if (item1.getId() == id) {
                return item1;
            }
        }
        return null;
    }
}
