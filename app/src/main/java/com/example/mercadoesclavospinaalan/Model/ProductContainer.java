package com.example.mercadoesclavospinaalan.Model;

import com.example.mercadoesclavospinaalan.POJO.Product;

import java.io.Serializable;
import java.util.List;

public class ProductContainer implements Serializable {
    private List<Product> results;

    public ProductContainer(List<Product> results) {
        this.results = results;
    }

    public ProductContainer() {
    }

    public List<Product> getResults() {
        return results;
    }

    public void setResults(List<Product> results) {
        this.results = results;
    }
}

