package com.bao.example.thuctap.services;

import com.bao.example.thuctap.dto.ProductDTO;
import com.bao.example.thuctap.model.Category;
import com.bao.example.thuctap.model.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    public List<Product> findAll();

    public Optional<Product> findProduct(Integer id);

    public void  delete(Product product);

    public   Product update(Product product);


    public ResponseEntity<String> save(ProductDTO productDTO);


}
