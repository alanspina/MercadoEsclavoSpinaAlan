package com.example.mercadoesclavospinaalan.Dao;

import com.example.mercadoesclavospinaalan.Service.ProductService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class RetrofitDao {


    //lo que se necesita para retrofit:
    protected Retrofit retrofit;
        public static final String BASE_URL = "https://api.mercadolibre.com/sites/MLA/";
        private ProductService productService;
        private String searchedProductQuery;

    public RetrofitDao() {
            this.retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();


    }

}
