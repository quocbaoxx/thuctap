package com.bao.example.thuctap.controller;

import com.bao.example.thuctap.dto.ProductDTO;
import com.bao.example.thuctap.dto.ProductImageDTO;
import com.bao.example.thuctap.model.Product;
import com.bao.example.thuctap.model.Productimage;
import com.bao.example.thuctap.services.ProductService;
import com.bao.example.thuctap.services.ProductimgaeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/productimage")
public class ProductImageController {
    @Autowired
    private ProductimgaeService productimgaeService;


    @GetMapping
    public ResponseEntity<List<Productimage>> getAll(){
        return ResponseEntity.ok(productimgaeService.findAll());
    }

    @GetMapping("/{id}")
    public  ResponseEntity<Productimage> getProduct(@PathVariable("id") Integer id){

        Optional<Productimage> category = productimgaeService.findProductimgae(id);
        return category.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody ProductImageDTO productImageDTO){
        return  productimgaeService.save(productImageDTO);

    }


    @PutMapping
    public  ResponseEntity<Productimage> update(@RequestBody Productimage productimage){
        return  ResponseEntity.ok(productimgaeService.update(productimage));
    }


    @DeleteMapping("/{id}")
    public  void  delete(@RequestBody Productimage productimage){
        productimgaeService.delete(productimage);
    }

}
