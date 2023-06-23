package com.coca.dao;

import com.coca.model.Product;

import java.util.List;

public interface ProductDAO {
    List<Product> getAll();

    boolean add(Product product);
}
