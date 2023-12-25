package com.bao.example.thuctap.impl;

import com.bao.example.thuctap.dto.ProductImageDTO;
import com.bao.example.thuctap.model.Product;
import com.bao.example.thuctap.model.Productimage;
import com.bao.example.thuctap.repositories.ProductimageRepository;
import com.bao.example.thuctap.services.ProductService;
import com.bao.example.thuctap.services.ProductimgaeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductimgaeServiceImpl implements ProductimgaeService {

    @Autowired
    private ProductimageRepository productimageRepository;

    @Autowired
    private ProductService productService;
    @Override
    public List<Productimage> findAll(){
        return productimageRepository.findAll();
    }

    @Override
    public Optional<Productimage> findProductimgae(Integer id){
        return productimageRepository.findById(id);
    }
    @Override
    public ResponseEntity<String> save(ProductImageDTO productImageDTO){
        Productimage productimgae = new Productimage();
        productimgae.setProductid(productImageDTO.getProductid());
        productimgae.setTitle(productImageDTO.getTitle());
        productimgae.setStatus(true);

        Optional<Product> product = productService.findProduct(productImageDTO.getProductid());
        if (product.isEmpty()){
            throw  new RuntimeException("Không tìm thấy category id: "+ productImageDTO.getProductid());
        }

        productimageRepository.save(productimgae);
        return ResponseEntity.ok("Lưu thành công");

    }


    @Override
    public Productimage update(Productimage productimgae){
        return  productimageRepository.save(productimgae);
    }

    @Override
    public  void  delete(Productimage productimgae){
        productimageRepository.delete(productimgae);
    }


}
