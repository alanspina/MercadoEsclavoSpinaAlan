package com.example.mercadoesclavospinaalan.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mercadoesclavospinaalan.R;
import com.example.mercadoesclavospinaalan.databinding.ActivityLogInBinding;
import com.example.mercadoesclavospinaalan.databinding.FragmentLogInBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ActivityLogIn extends AppCompatActivity implements FragmentLogIn.FragmentLogInListener, FragmentRegister.FragmentRegisterListener{

    private FragmentLogIn fragmentLogIn;
    private FragmentRegister fragmentRegister;
    private FirebaseAuth mAuth;
    private ActivityLogInBinding binding;

    public ActivityLogIn() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        binding = ActivityLogInBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        fragmentLogIn = new FragmentLogIn();
        fragmentRegister = new FragmentRegister();
        pegarFragment(fragmentLogIn);
    }


    /**
     * Chequea si ya está logueado el usuario.
     */
    @Override
    public void onStart() {
        super.onStart();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            updateUI(currentUser);
        }
    }

    /**
     * Si el usuario no es null, lo manda a la mainActivity
     * @param account
     */
    private void updateUI(FirebaseUser account) {
        if (account != null) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    protected void pegarFragment(Fragment fragment) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.logInContenedor, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
    @Override
    public void onClickSignUp() {
        pegarFragment(fragmentRegister);
    }

    @Override
    public void onClickForgotPassword() {
        Toast.makeText(this, "Mail para recuperar contraseña", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClickSignIn() {
        pegarFragment(fragmentLogIn);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finish();
        } else {
            super.onBackPressed();
        }
    }
}
