package com.bao.example.thuctap.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="tblcategory")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category  extends BaseModel{

    @Column(name = "name", length = 300, nullable = false)
    private String name;

    @Column(name = "description", length = 500, nullable = true)
    private String description;

    @Column(name = "parentID", nullable = true)
    private Integer  parentId;



}
