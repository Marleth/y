package com.gtm.itinerario;

public class Menu {
    private String nombre;
    private int idDrawable;

    public Menu(String nombre, int idDrawable) {
        this.nombre = nombre;
        this.idDrawable = idDrawable;
    }

    public String getNombre() {
        return nombre;
    }

    public int getIdDrawable() {
        return idDrawable;
    }

    public int getId() {
        return nombre.hashCode();
    }

    public static Menu[] ITEMS = {
            new Menu("Documentacion",R.drawable.documentacio),
            new Menu("Vehicular", R.drawable.vehiculars),
            new Menu ("Carga",R.drawable.vehi),
            new Menu ("Viaticos",R.drawable.ic_dinero),
    };

    public static Menu getItem(int id) {
        for (Menu item1 : ITEMS) {
            if (item1.getId() == id) {
                return item1;
            }
        }
        return null;
    }
}
