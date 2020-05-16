package com.example.mercadoesclavospinaalan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerViewFragment recyclerViewFragment = new RecyclerViewFragment();
        pegarFragment(recyclerViewFragment);


    }

    private void pegarFragment(RecyclerViewFragment recyclerViewFragment) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.activityMainContenedorFragment, recyclerViewFragment);
        fragmentTransaction.commit();
    }
}
