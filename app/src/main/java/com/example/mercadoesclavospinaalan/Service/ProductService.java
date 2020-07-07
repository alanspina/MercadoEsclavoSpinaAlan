package com.example.mercadoesclavospinaalan.Service;

import com.example.mercadoesclavospinaalan.Model.Category;
import com.example.mercadoesclavospinaalan.Model.ProductContainer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ProductService {

    @GET("search?")
    Call<ProductContainer> productApiRequest(@Query("q") String requestedSearch);

    @GET("categories")
    Call<List<Category>> categoryApiRequest();

    @GET("search?")
    Call<ProductContainer> buscarPorCategoria(@Query("category") String category);
}
