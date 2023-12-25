package com.bao.example.thuctap.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="tblsaleoder")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaleOder extends  BaseModel{

    @Column(name ="user_id")
    private Integer userid;


    @Column(name = "code", length = 60, nullable = false)
    private String code;

    @Column(name = "customer_name", length = 300, nullable = false)
    private String customername;


    @Column(name = "customer_mobile", length = 120, nullable = true)
    private String customermobile;
}
