package com.bao.example.thuctap.dto;

import com.bao.example.thuctap.model.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SaleOderDTO {
    private  Integer id;

    private Integer userid;

    private String code;

    private Boolean status;

    private String customername;

    private String customermobile;

    private  CategoryDTO categoryDTO = new CategoryDTO();

    private List<ProductDTO> productList= new ArrayList<>();



}
