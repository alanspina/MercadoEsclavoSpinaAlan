package com.example.mercadoesclavospinaalan.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mercadoesclavospinaalan.Controller.UserFirebaseController;
import com.example.mercadoesclavospinaalan.R;
import com.example.mercadoesclavospinaalan.databinding.FragmentRegisterBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentRegister extends Fragment implements View.OnClickListener {

    private FragmentRegisterBinding binding;
    private FragmentRegisterListener fragmentRegisterListener;
    private FirebaseAuth mAuth;
    private UserFirebaseController userFirebaseController;

    public FragmentRegister() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        fragmentRegisterListener = (FragmentRegisterListener) context;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        mAuth = FirebaseAuth.getInstance();
        userFirebaseController = new UserFirebaseController(getContext());

        binding.signInText.setOnClickListener(this);
        binding.btnRegister.setOnClickListener(this);

        return view;
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.signInText:
                fragmentRegisterListener.onClickSignIn();
                break;
            case R.id.btnRegister:
                crearUsuario(binding.editTextRegisterEMail.getText().toString(), binding.editTextRegisterPassword1.getText().toString());
                break;

        }
    }

    /**
     * Crear usuario con Email y Password en Firebase.
     * @param email
     * @param password
     */
    private void crearUsuario(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        FirebaseUser user = mAuth.getCurrentUser();
                        UserMF userMF = new UserMF(
                                binding.editTextUsername.getText().toString(),
                                binding.editTextRegisterFirstName.getText().toString(),
                                binding.editTextRegisterLastName.getText().toString(),
                                binding.editTextRegisterEMail.getText().toString()
                        );
                        userFirebaseController.registerUser(userMF, result -> Toast.makeText(getContext(), "Welcome " + binding.editTextUsername.getText().toString() + "!", Toast.LENGTH_SHORT).show());
                        updateUI(user);
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(getContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                        updateUI(null);
                    }
                });

    }

    /**
     * Si el usuario está logueado con Firebase, va a la MainActivity.
     * @param currentUser
     */
    private void updateUI(FirebaseUser currentUser) {
        if (currentUser != null) {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(getContext(), "Registration failed", Toast.LENGTH_SHORT).show();
        }
    }

    public interface FragmentRegisterListener{
        void onClickSignIn();
    }
}
