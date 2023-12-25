package com.bao.example.thuctap.services;

import com.bao.example.thuctap.dto.SaleOderDTO;
import com.bao.example.thuctap.dto.SaleOderProductDTO;
import com.bao.example.thuctap.dto.UserDTO;
import com.bao.example.thuctap.model.Category;
import com.bao.example.thuctap.model.SaleOder;
import com.bao.example.thuctap.model.SaleOderProduct;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface SaleOderProductService {

    public List<SaleOderProduct> findAll();


    public Optional<SaleOderProduct> findSaleOderProduct(Integer id);

    public void  delete(SaleOderProduct saleOderProduct);

    public   SaleOderProduct update(SaleOderProduct saleOderProduct);


    public ResponseEntity<String> save(SaleOderProductDTO saleOderProductDTO);
}
