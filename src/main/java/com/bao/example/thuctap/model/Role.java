package com.bao.example.thuctap.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="tblrole")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role  extends  BaseModel{


    @Column(name = "name", length = 120, nullable = false)
    private String name;


    @Column(name = "description", length = 500, nullable = true)
    private String description;

//    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
//    private List<UserRole> users;


}
