package com.bao.example.thuctap.impl;

import com.bao.example.thuctap.dto.SaleOderProductDTO;
import com.bao.example.thuctap.model.Product;
import com.bao.example.thuctap.model.SaleOder;
import com.bao.example.thuctap.model.SaleOderProduct;
import com.bao.example.thuctap.repositories.SaleOrderProductRepositoty;
import com.bao.example.thuctap.services.ProductService;
import com.bao.example.thuctap.services.SaleOderProductService;
import com.bao.example.thuctap.services.SaleOderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class SaleOderProductServiceImpl implements SaleOderProductService {

    @Autowired
    private SaleOrderProductRepositoty SaleOrderProductRepositoty;

    @Autowired
    private SaleOderService saleOderService;

    @Autowired
    private ProductService productService;


    @Override
    public List<SaleOderProduct> findAll(){
        return SaleOrderProductRepositoty.findAll();
    }

    @Override
    public Optional<SaleOderProduct> findSaleOderProduct(Integer id){
        return SaleOrderProductRepositoty.findById(id);
    }
    @Override
    public ResponseEntity<String> save(SaleOderProductDTO saleOderProductDTO){
        SaleOderProduct saleOderProduct = new SaleOderProduct();
        saleOderProduct.setSaleoderid(saleOderProductDTO.getSaleoderid());
        saleOderProduct.setProductid(saleOderProductDTO.getProductid());
        saleOderProduct.setDescription(saleOderProductDTO.getDescription());
        saleOderProduct.setStatus(true);

        Optional<SaleOder> saleOder = saleOderService.findSaleOder(saleOderProductDTO.getSaleoderid());
        Optional<Product> product = productService.findProduct(saleOderProductDTO.getProductid());
        if (saleOder.isEmpty() && product.isEmpty()){
            throw  new RuntimeException("Kiểm tra lại id saleoderid and productid");
        }

        SaleOrderProductRepositoty.save(saleOderProduct);
        return ResponseEntity.ok("Lưu thành công");

    }


    @Override
    public  SaleOderProduct update(SaleOderProduct saleOderProduct){
        return  SaleOrderProductRepositoty.save(saleOderProduct);
    }

    @Override
    public  void  delete(SaleOderProduct saleOderProduct){
        SaleOrderProductRepositoty.delete(saleOderProduct);
    }


}
