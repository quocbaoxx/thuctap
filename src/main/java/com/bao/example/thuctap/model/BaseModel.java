package com.bao.example.thuctap.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Integer id;


    @Column(name = "status", nullable = true)
    private Boolean status = Boolean.TRUE;


}