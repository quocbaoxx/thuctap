package com.bao.example.thuctap.dto;

import com.bao.example.thuctap.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO{

    private  Integer id;

    private String name;

    private Boolean status;

    private String description;

    private int parentId;

    private List<CategoryDTO> categoryDTOS = new ArrayList<>();

    public CategoryDTO(Integer id, String name, Boolean status, String description, int parentId) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.description = description;
        this.parentId = parentId;
    }

    public CategoryDTO(Integer id, String name, Boolean status, String description) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.description = description;
    }



}
