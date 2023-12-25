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
@Table(name="tblcontact")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Contact extends  BaseModel{

    @Column(name = "name", length = 120, nullable = true)
    private String name;

    @Column(name = "total", nullable = true)
    private BigDecimal total;
}
