package com.example.mercadoesclavospinaalan.Dao;

import com.example.mercadoesclavospinaalan.Model.ProductContainer;
import com.example.mercadoesclavospinaalan.Service.ProductService;
import com.example.mercadoesclavospinaalan.Util.ResultListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDao extends RetrofitDao {

    private ProductService productService;

    public ProductDao() {
        productService =  super.retrofit.create(ProductService.class);
    }

/*    public void getProduct(ResultListener){
    }*/
    public void productRequest(String requestedSearch, final ResultListener<ProductContainer> controllerListener) {
        Call<ProductContainer> productContainerCall = productService.productApiRequest(requestedSearch);
        productContainerCall.enqueue(new Callback<ProductContainer>() {
            @Override
            public void onResponse(Call<ProductContainer> call, Response<ProductContainer> response) {
                ProductContainer body = response.body();
                controllerListener.onFinish(body);
            }

            @Override
            public void onFailure(Call<ProductContainer> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void buscarProductosPorCategoria(String categoria, final ResultListener<ProductContainer> resultListenerFromController){
        productService.buscarPorCategoria(categoria);
        Call<ProductContainer> productContainerCall = productService.buscarPorCategoria(categoria);
        productContainerCall.enqueue(new Callback<ProductContainer>() {
            @Override
            public void onResponse(Call<ProductContainer> call, Response<ProductContainer> response) {
                ProductContainer body = response.body();
                resultListenerFromController.onFinish(body);
            }

            @Override
            public void onFailure(Call<ProductContainer> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
    }

/*    public void categorytRequest(final ResultListener<List<Category>> controllerListener){
        Call<List<Category>> categoryListCall = productService.categoryApiRequest();
        productService.categoryApiRequest();
        categoryListCall.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                List<Category> body = response.body();
                controllerListener.onFinish(body);
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }*/





