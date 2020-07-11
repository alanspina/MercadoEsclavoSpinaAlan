package com.example.mercadoesclavospinaalan.Controller;

import android.content.Context;

import com.example.mercadoesclavospinaalan.Dao.UserFirebaseDao;
import com.example.mercadoesclavospinaalan.Util.ResultListener;
import com.example.mercadoesclavospinaalan.View.UserMF;

public class UserFirebaseController {

    private UserFirebaseDao daoFireStoreUser;
    private Context context;

    public UserFirebaseController(Context context) {
        this.context = context;
        this.daoFireStoreUser = new UserFirebaseDao();
    }

    public void registerUser(UserMF userMF, final ResultListener<UserMF> resultListenerFromView){
        daoFireStoreUser.registerUser(userMF, resultListenerFromView);
    }
}
