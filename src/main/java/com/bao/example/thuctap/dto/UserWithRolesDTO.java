package com.bao.example.thuctap.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserWithRolesDTO {

    private Integer userId;
    private String username;
    private String userName;
    private Integer roleId;
    private String roleName;
}
