package com.example.mercadoesclavospinaalan.Dao;

import android.util.Log;

import com.example.mercadoesclavospinaalan.Util.ResultListener;
import com.example.mercadoesclavospinaalan.View.UserMF;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserFirebaseDao {

    private FirebaseFirestore db;
    private CollectionReference reference;
    private static final String COLLECTION_NAME = "users";
    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;

    public UserFirebaseDao() {
        this.db = FirebaseFirestore.getInstance();
        reference = db.collection(COLLECTION_NAME);
        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
    }

    public void registerUser(final UserMF userSM, final ResultListener<UserMF> resultListenerFromController){
        reference.document(mAuth.getCurrentUser().getUid())
                .set(userSM)
                .addOnCompleteListener(task -> resultListenerFromController.onFinish(userSM))
                .addOnFailureListener(e -> Log.w("TAG", "Error adding user", e));
    }
}
