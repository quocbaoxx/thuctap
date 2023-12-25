package com.bao.example.thuctap.impl;

import com.bao.example.thuctap.dto.ProductDTO;
import com.bao.example.thuctap.model.Category;
import com.bao.example.thuctap.model.Product;
import com.bao.example.thuctap.repositories.ProductRepository;
import com.bao.example.thuctap.services.CategoryService;
import com.bao.example.thuctap.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl  implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;


    @Override
    public List<Product> findAll(){
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findProduct(Integer id){
        return productRepository.findById(id);
    }
    @Override
    public ResponseEntity<String> save(ProductDTO productDTO){
        Product product = new Product();
        product.setCategoryid(productDTO.getCategoryid());
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setStatus(true);
        product.setDescription(productDTO.getDescription());

        Optional<Category> category = categoryService.findCategory(productDTO.getCategoryid());
        if (category.isEmpty()){
            throw  new RuntimeException("Không tìm thấy category id: "+ productDTO.getCategoryid());
        }

       productRepository.save(product);
        return ResponseEntity.ok("Lưu thành công");

    }


    @Override
    public  Product update(Product product){
        return  productRepository.save(product);
    }

    @Override
    public  void  delete(Product product){
        productRepository.delete(product);
    }

}
