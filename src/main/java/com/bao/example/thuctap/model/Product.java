package com.bao.example.thuctap.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name="tblproduct")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product extends  BaseModel {

    @Column(name ="categoryid")
    private Integer categoryid;

    @Column(name = "name", length = 300, nullable = false)
    private String name;

    @Column(name = "price", nullable = true)
    private BigDecimal price;


    @Column(name = "description", length = 500, nullable = true)
    private String description;

}
