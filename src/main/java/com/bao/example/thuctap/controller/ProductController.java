package com.bao.example.thuctap.controller;

import com.bao.example.thuctap.dto.ProductDTO;
import com.bao.example.thuctap.model.Product;
import com.bao.example.thuctap.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    @Autowired
    private ProductService productService;


    @GetMapping
    public ResponseEntity<List<Product>> getAll(){
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public  ResponseEntity<Product> getProduct(@PathVariable("id") Integer id){

        Optional<Product> category = productService.findProduct(id);
        return category.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody  ProductDTO productDTO){
      return  productService.save(productDTO);

    }


    @PutMapping
    public  ResponseEntity<Product> update(@RequestBody Product product){
        return  ResponseEntity.ok(productService.update(product));
    }


    @DeleteMapping("/{id}")
    public  void  delete(@RequestBody Product product){
        productService.delete(product);
    }

}
