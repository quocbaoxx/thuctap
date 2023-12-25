package com.bao.example.thuctap.controller;

import com.bao.example.thuctap.dto.ProductDTO;
import com.bao.example.thuctap.dto.SaleOderDTO;
import com.bao.example.thuctap.model.Product;
import com.bao.example.thuctap.model.SaleOder;
import com.bao.example.thuctap.services.ProductService;
import com.bao.example.thuctap.services.SaleOderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/saleoder")
public class SaleOderController {

    @Autowired
    private SaleOderService saleOderService;


    @GetMapping
    public ResponseEntity<List<SaleOder>> getAll(){
        return ResponseEntity.ok(saleOderService.findAll());
    }

    @GetMapping("/{id}")
    public  ResponseEntity<SaleOder> getProduct(@PathVariable("id") Integer id){

        Optional<SaleOder> category = saleOderService.findSaleOder(id);
        return category.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PostMapping("/savefor")
    public ResponseEntity<String> savefor(@RequestBody SaleOderDTO saleOderDTO){
        return  saleOderService.savefor(saleOderDTO);
    }


    @PostMapping
    public ResponseEntity<String> save(@RequestBody SaleOderDTO saleOderDTO){
        return  saleOderService.save(saleOderDTO);
    }


    @PutMapping
    public  ResponseEntity<SaleOder> update(@RequestBody SaleOder saleOder){
        return  ResponseEntity.ok(saleOderService.update(saleOder));
    }


    @DeleteMapping("/{id}")
    public  void  delete(@RequestBody SaleOder saleOder){
        saleOderService.delete(saleOder);
    }

}
