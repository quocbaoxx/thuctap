package com.bao.example.thuctap.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Table(name="tbluserrole")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRole    {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Integer id;


    @Column(name ="user_id")
    private Integer userid;

    @Column(name ="role_id")
    private Integer roleid;




//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
//
//    @ManyToOne
//    @JoinColumn(name = "role_id")
//    private Role role;



}
