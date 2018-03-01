package com.gtm.itinerario;

public class Menuvehi {
    private String nombrevehi;
    private int idDrawablevehi;

    public Menuvehi(String nombre, int idDrawable) {
        this.nombrevehi = nombre;
        this.idDrawablevehi = idDrawable;
    }

    public String getNombre() {
        return nombrevehi;
    }

    public int getIdDrawable() {
        return idDrawablevehi;
    }

    public int getId() {
        return nombrevehi.hashCode();
    }

    public static Menuvehi[] ITEMS = {

            new Menuvehi("Neumaticos",R.drawable.neumaticos),
            new Menuvehi ("Niveles",R.drawable.niveles),
            new Menuvehi ("Luces",R.drawable.luces),
    };

    public static Menuvehi getItem(int id) {
        for (Menuvehi item1 : ITEMS) {
            if (item1.getId() == id) {
                return item1;
            }
        }
        return null;
    }
}
