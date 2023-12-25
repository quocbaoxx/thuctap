package com.bao.example.thuctap.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="tblsaleoderproduct")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaleOderProduct extends  BaseModel {

    @Column(name ="sale_oder_id")
    private Integer saleoderid;

    @Column(name ="product_id")
    private Integer productid;

    @Column(name = "description", length = 500, nullable = false)
    private String description;

}
