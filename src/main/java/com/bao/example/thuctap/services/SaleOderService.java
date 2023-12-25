package com.bao.example.thuctap.services;

import com.bao.example.thuctap.dto.ProductDTO;
import com.bao.example.thuctap.dto.SaleOderDTO;
import com.bao.example.thuctap.model.Product;
import com.bao.example.thuctap.model.SaleOder;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface SaleOderService {

    public List<SaleOder> findAll();

    public ResponseEntity<String> savefor(SaleOderDTO saleOderDTO);

    public Optional<SaleOder> findSaleOder(Integer id);

    public void  delete(SaleOder saleOder);

    public   SaleOder update(SaleOder saleOder);


    public ResponseEntity<String> save(SaleOderDTO saleOderDTO);


}
