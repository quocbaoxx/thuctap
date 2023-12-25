package com.bao.example.thuctap.dto;

import com.bao.example.thuctap.model.Category;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class CategoryDTO {

    private  Integer id;

    private String name;

    private Boolean status;

    private String description;

    private int parentId;

    private List<CategoryDTO> categoryDTOS = new ArrayList<>();

}
