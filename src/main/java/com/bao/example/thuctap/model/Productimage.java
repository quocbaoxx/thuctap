package com.bao.example.thuctap.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="tblproductimage")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Productimage extends  BaseModel{

    @Column(name ="product_id")
    private Integer productid;

    @Column(name = "title", length = 500, nullable = false)
    private String title;

}
