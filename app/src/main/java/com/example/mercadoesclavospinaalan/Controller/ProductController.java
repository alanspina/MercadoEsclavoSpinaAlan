package com.example.mercadoesclavospinaalan.Controller;

import com.example.mercadoesclavospinaalan.Dao.ProductDao;
import com.example.mercadoesclavospinaalan.Model.ProductContainer;
import com.example.mercadoesclavospinaalan.Util.ResultListener;

import java.util.List;

public class ProductController {

    private ProductDao dao;

    public ProductController() {
        dao = new ProductDao();
    }

/*    public void getCategoryRequest (final ResultListener<List<Category>> viewListener){
        dao.categorytRequest(new ResultListener<List<Category>>() {
            @Override
            public void onFinish(List<Category> results) {
                viewListener.onFinish(results);
            }
        });
    }*/

/*    public void getProductRequest(String requestedSearch, final ResultListener<ProductContainer> viewListener ){
        dao.productRequest(new ResultListener<ProductContainer>() {
            @Override
            public void onFinish(ProductContainer results) {
                viewListener.onFinish(results);
            }
        },requestedSearch);
    }*/

    public void buscarProductoPorCategoria(final ResultListener<ProductContainer> resultListenerFromView){
        dao.buscarProductosPorCategoria("MLA1051", new ResultListener<ProductContainer>() {
            @Override
            public void onFinish(ProductContainer result) {
                resultListenerFromView.onFinish(result);
            }
        });
    }
}