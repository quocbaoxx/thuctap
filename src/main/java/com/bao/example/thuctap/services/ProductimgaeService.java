package com.bao.example.thuctap.services;

import com.bao.example.thuctap.dto.ProductImageDTO;
import com.bao.example.thuctap.model.Productimage;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ProductimgaeService {

    public List<Productimage> findAll();

    public Optional<Productimage> findProductimgae(Integer id);

    public void  delete(Productimage productimgae);

    public Productimage update(Productimage productimgae);


    public ResponseEntity<String> save(ProductImageDTO ProductImageDTO);


}
