package com.example.mercadoesclavospinaalan.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.mercadoesclavospinaalan.Model.ProductContainer;
import com.example.mercadoesclavospinaalan.POJO.Product;
import com.example.mercadoesclavospinaalan.R;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecyclerViewProductos extends Fragment {

    private ProductosAdapter productosAdapter;

    public RecyclerViewProductos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);
        //declaro las variables que necesito
        RecyclerView recyclerViewProductos = view.findViewById(R.id.fragmentRecyclerViewRecyclerView);
        //ACA LE TENGO QUE DAR LOS DATOS QUE VIENEN DEL CONTROLLER.

//magia del juli
        Bundle bundle = getArguments();
        ProductContainer productContainer = (ProductContainer) bundle.getSerializable("resultado");
        List<Product> productList = productContainer.getResults();
        ProductosAdapter productosAdapter = new ProductosAdapter(productList);
        //magia del juli


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);

        //activo
        recyclerViewProductos.setLayoutManager(linearLayoutManager);
        recyclerViewProductos.setAdapter(productosAdapter);
        return view;
    }
}
