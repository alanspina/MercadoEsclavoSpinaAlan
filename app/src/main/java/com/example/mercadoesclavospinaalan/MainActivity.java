package com.example.mercadoesclavospinaalan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import com.example.mercadoesclavospinaalan.Controller.ProductController;
import com.example.mercadoesclavospinaalan.Model.ProductContainer;
import com.example.mercadoesclavospinaalan.POJO.Product;
import com.example.mercadoesclavospinaalan.Util.ResultListener;
import com.example.mercadoesclavospinaalan.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActivityMainBinding binding;
    private ProductController productController;
    private List<Product> productList;
    private ProductosAdapter adapter;
    private RecyclerViewProductos recyclerViewFragment;
    private FragmentAboutUs fragmentAboutUs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newFragments();

        noUsarFindViewById();
        findViewById();


        binding.navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menuNavigationAboutUs:
                        pegarFragment(fragmentAboutUs);
                        break;
                    case R.id.menuNavigationLogOut:
                        Toast.makeText(MainActivity.this, "Presionaron Logout", LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
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

    private void pegarFragment(Fragment unFragment) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.activityMainContenedorFragment, unFragment);
        fragmentTransaction.commit();
    }

    private void newFragments(){
        fragmentAboutUs = new FragmentAboutUs();
    }
}
