package com.bao.example.thuctap.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class ProductDTO {


    private  Integer id;

    private Integer categoryid;

    private String name;

    private BigDecimal price;

    private Boolean status;

    private String description;


}
