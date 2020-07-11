package com.example.mercadoesclavospinaalan.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mercadoesclavospinaalan.Controller.UserFirebaseController;
import com.example.mercadoesclavospinaalan.R;
import com.example.mercadoesclavospinaalan.Util.ResultListener;
import com.example.mercadoesclavospinaalan.databinding.FragmentLogInBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentLogIn extends Fragment implements View.OnClickListener {


    private static final int RC_SIGN_IN = 1;
    private FragmentLogInListener fragmentLogInListener;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private UserFirebaseController userFirebaseController;
    private FragmentLogInBinding binding;

    public FragmentLogIn() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.fragmentLogInListener = (FragmentLogInListener) context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLogInBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        mAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(getContext(), gso);

        userFirebaseController = new UserFirebaseController(getContext());
        binding.signInButton.setSize(SignInButton.SIZE_WIDE);

        binding.textViewForgotPassword.setOnClickListener(this);
        binding.signUpText.setOnClickListener(this);
        binding.btnLogIn.setOnClickListener(this);
        binding.signInButton.setOnClickListener(this);

        return view;

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.signInButton:
                signIn();
                break;
            case R.id.textViewForgotPassword:
                fragmentLogInListener.onClickForgotPassword();
                break;
            case R.id.signUpText:
                fragmentLogInListener.onClickSignUp();
                break;
            case R.id.btnLogIn:
                confirmInput();
                break;
        }
    }

    /**
     * Click en botón de SignUp para ir al FragmentRegister
     */
    public interface FragmentLogInListener {
        void onClickSignUp();

        void onClickForgotPassword();
    }

    /**
     * Inicia sesión chequeando en Firebase con mail y password
     *
     * @param email
     * @param password
     */
    private void iniciarSesion(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    /**
     * Google SignIn
     */
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    /**
     * More Google stuff
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {

            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);

                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                Log.w("GOOGLE", "signInResult:failed code=" + e.getStatusCode());
                updateUI(null);
            }
        }
    }

    /**
     * Si el usuario está logueado con Firebase, va a la MainActivity.
     *
     * @param currentUser
     */
    private void updateUI(FirebaseUser currentUser) {
        if (currentUser != null) {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
        }
    }

    /**
     * El mail tiene que tener un arroba y más de 10 caracteres
     * @return
     */
    private boolean validateEmail(){
        String emailInput = binding.editTextUsernameEmail.getText().toString();
        if (!emailInput.contains("@") || emailInput.length() < 10){
            binding.editTextUsernameEmail.setError("Email address is incorrect");
            return false;
        } else {
            binding.editTextUsernameEmail.setError(null);
            return true;
        }
    }

    /**
     * La password no puede estar vacía
     * @return
     */
    private boolean validatePassword(){
        String passwordInput = binding.editTextPassword.getText().toString();
        if (passwordInput.isEmpty()){
            binding.editTextPassword.setError("Password needed");
            return false;
        } else {
            binding.editTextPassword.setError(null);
            return true;
        }
    }

    /**
     * Chequea las dos validaciones de inputs
     */
    private void confirmInput(){
        if (!validateEmail() | !validatePassword()){
            return;
        }
        iniciarSesion(binding.editTextUsernameEmail.getText().toString(), binding.editTextPassword.getText().toString());
    }

    /**
     * Intercambia el tokenId de account con el credential de Firebase para hacer Login.
     * @param idToken
     */
    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(getActivity(), task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("TAG", "signInWithCredential:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getActivity());
                        UserMF usuario = new UserMF(acct.getGivenName(),
                                acct.getFamilyName(),
                                acct.getDisplayName(),
                                acct.getEmail());
                        userFirebaseController.registerUser(usuario, new ResultListener<UserMF>() {
                            @Override
                            public void onFinish(UserMF result) {
                                Toast.makeText(FragmentLogIn.this.getContext(), "Welcome to MEAS!", Toast.LENGTH_SHORT).show();
                            }
                        });
                        updateUI(user);
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("TAG", "signInWithCredential:failure", task.getException());
                        //Snackbar.make(binding.getRoot(), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                        updateUI(null);
                    }
                });

    }
}