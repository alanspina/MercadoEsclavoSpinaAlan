package com.example.mercadoesclavospinaalan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.mercadoesclavospinaalan.Controller.ProductController;
import com.example.mercadoesclavospinaalan.Model.ProductContainer;
import com.example.mercadoesclavospinaalan.POJO.Product;
import com.example.mercadoesclavospinaalan.Util.ResultListener;
import com.example.mercadoesclavospinaalan.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActivityMainBinding binding;
    private ProductController productController;
    private List<Product> productList;
    private ProductosAdapter adapter;
    private RecyclerViewProductos recyclerViewFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noUsarFindViewById();
        findViewById();

        //el navigationview es la barra del costado que no me sirve para nada
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return false;
            }
        });

        recyclerViewFragment = new RecyclerViewProductos();

        ProductController productController = new ProductController();
        traerCelulares(productController);
    }

    private void traerCelulares(ProductController productController) {
        productController.buscarProductoPorCategoria(new ResultListener<ProductContainer>() {
            @Override
            public void onFinish(ProductContainer result) {
                Bundle unBundle = new Bundle();
                unBundle.putSerializable("resultado", result);
                recyclerViewFragment.setArguments(unBundle);
                pegarFragment(recyclerViewFragment);
            }
        });
    }

    private void noUsarFindViewById() {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    private void findViewById() {
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
    }

    private void pegarFragment(RecyclerViewProductos recyclerViewFragment) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.activityMainContenedorFragment, recyclerViewFragment);
        fragmentTransaction.commit();
    }
}
