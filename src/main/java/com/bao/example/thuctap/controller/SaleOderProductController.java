package com.bao.example.thuctap.controller;

import com.bao.example.thuctap.dto.SaleOderProductDTO;
import com.bao.example.thuctap.model.SaleOderProduct;
import com.bao.example.thuctap.services.SaleOderProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/saleoderproduct")
public class SaleOderProductController {


    @Autowired
    private SaleOderProductService oderProductService;


    @GetMapping
    public ResponseEntity<List<SaleOderProduct>> getAll(){
        return ResponseEntity.ok(oderProductService.findAll());
    }

    @GetMapping("/{id}")
    public  ResponseEntity<SaleOderProduct> getProduct(@PathVariable("id") Integer id){

        Optional<SaleOderProduct> category = oderProductService.findSaleOderProduct(id);
        return category.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody SaleOderProductDTO saleOderProductDTO){
        return  oderProductService.save(saleOderProductDTO);

    }


    @PutMapping
    public  ResponseEntity<SaleOderProduct> update(@RequestBody SaleOderProduct saleOderProduct){
        return  ResponseEntity.ok(oderProductService.update(saleOderProduct));
    }


    @DeleteMapping("/{id}")
    public  void  delete(@RequestBody SaleOderProduct saleOderProduct){
        oderProductService.delete(saleOderProduct);
    }

}
