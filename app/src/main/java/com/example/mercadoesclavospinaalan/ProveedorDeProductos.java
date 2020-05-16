package com.example.mercadoesclavospinaalan;

import java.util.ArrayList;
import java.util.List;

public abstract class ProveedorDeProductos {

    public static List<Producto> getProductos(){
        List<Producto> productosList = new ArrayList<>();

        productosList.add(new Producto("celulariphone10",30, R.drawable.celulariphone10));
        productosList.add(new Producto("celulariphonexr", 30,R.drawable.celulariphonexr));
        productosList.add(new Producto("celularmotog8", 30,R.drawable.celularmotog8));
        productosList.add(new Producto("celularpixel3", 30,R.drawable.celularpixel3));
        productosList.add(new Producto("celularsamsungnote10", 30,R.drawable.celularsamsungnote10));
        productosList.add(new Producto("ceularsamsungs10", 30,R.drawable.ceularsamsungs10));


        return productosList;
    }
}
