package com.bao.example.thuctap.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="tbluser")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User  extends  BaseModel{


    @Column(name = "username", length = 120, nullable = false)
    private String username;

    @Column(name = "password", length = 120, nullable = false)
    private String password;

    @Column(name = "name", length = 120, nullable = false)
    private String name;

//    @Column(name = "description", length = 120, nullable = false)
//    private String description;

//    @ManyToMany
//    @JoinTable(
//            name = "tbluserrole",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id")
//    )

    @Transient// ko dung de map voi truong trong database
    private List<Role> roles =new ArrayList<>();




//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<UserRole> roles;


}
