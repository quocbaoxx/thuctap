package com.bao.example.thuctap.impl;

import com.bao.example.thuctap.dto.CategoryDTO;
import com.bao.example.thuctap.dto.ProductDTO;
import com.bao.example.thuctap.dto.SaleOderDTO;
import com.bao.example.thuctap.model.*;
import com.bao.example.thuctap.repositories.CategoryRepository;
import com.bao.example.thuctap.repositories.ProductRepository;
import com.bao.example.thuctap.repositories.SaleOderRepository;
import com.bao.example.thuctap.repositories.SaleOrderProductRepositoty;
import com.bao.example.thuctap.services.SaleOderService;
import com.bao.example.thuctap.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SaleOderServiceImpl implements SaleOderService {

    @Autowired
    private SaleOderRepository saleOderRepository;

    @Autowired
    private UserService userService;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SaleOrderProductRepositoty saleOrderProductRepositoty;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ResponseEntity<String> savefor(SaleOderDTO saleOderDTO){

        SaleOder saleOder = new SaleOder();
        saleOder.setUserid(saleOderDTO.getUserid());
        saleOder.setCode(saleOderDTO.getCode());
        saleOder.setCustomername(saleOderDTO.getCustomername());
        saleOder.setStatus(true);
        saleOder.setCustomermobile(saleOderDTO.getCustomermobile());

        Optional<User> user = userService.findUser(saleOderDTO.getUserid());
        if (user.isEmpty()){
            throw  new RuntimeException("Không tìm thấy  id user: "+ saleOderDTO.getUserid());
        }
        saleOderRepository.save(saleOder);

        Integer  saleOderId = saleOder.getId();


        CategoryDTO categoryDTO = saleOderDTO.getCategoryDTO();


//        Optional<Category> categoryOptional = categoryRepository.findById(categoryDTO.getId());
//        if (categoryOptional.isPresent()){
//            categoryOptional.get();
//        }
            Category category = new Category();

            category.setName(categoryDTO.getName());
            category.setDescription(categoryDTO.getDescription());
            category.setStatus(true);
            categoryRepository.save(category);

        Integer categoryId = category.getId();


        List<Product> products = new ArrayList<>();
        for (ProductDTO productDTO : saleOderDTO.getProductList()){

            Product product = new Product();
            product.setCategoryid(categoryId);
            product.setName(productDTO.getName());
            product.setPrice(productDTO.getPrice());
            product.setStatus(true);
            product.setDescription(productDTO.getDescription());

            products.add(product);

        }

        if (!products.isEmpty()){
            productRepository.saveAll(products);
            List<SaleOderProduct> saleOderProducts = new ArrayList<>();
            for(Product product : products){
                SaleOderProduct saleOderProduct = new SaleOderProduct();
                saleOderProduct.setProductid(product.getId());
                saleOderProduct.setSaleoderid(saleOderId);
            }
            saleOrderProductRepositoty.saveAll(saleOderProducts);

        }
        return ResponseEntity.ok("ok");
    }


    @Override
    public List<SaleOder> findAll(){
        return saleOderRepository.findAll();
    }

    @Override
    public Optional<SaleOder> findSaleOder(Integer id){
        return saleOderRepository.findById(id);
    }
    @Override
    public ResponseEntity<String> save(SaleOderDTO saleOderDTO){
        SaleOder saleOder = new SaleOder();
        saleOder.setUserid(saleOderDTO.getUserid());
        saleOder.setCode(saleOderDTO.getCode());
        saleOder.setCustomername(saleOderDTO.getCustomername());
        saleOder.setStatus(true);
        saleOder.setCustomermobile(saleOderDTO.getCustomermobile());

        Optional<User> user = userService.findUser(saleOderDTO.getUserid());
        if (user.isEmpty()){
            throw  new RuntimeException("Không tìm thấy  id: "+ saleOderDTO.getUserid());
        }

        saleOderRepository.save(saleOder);
        return ResponseEntity.ok("Lưu thành công");

    }


    @Override
    public  SaleOder update(SaleOder saleOder){
        return  saleOderRepository.save(saleOder);
    }

    @Override
    public  void  delete(SaleOder saleOder){
        saleOderRepository.delete(saleOder);
    }


}
