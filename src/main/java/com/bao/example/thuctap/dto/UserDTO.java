package com.bao.example.thuctap.dto;

import com.bao.example.thuctap.model.Role;
import jdk.jshell.Snippet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Integer id;

    private String username;
    private  String password;
    private String name;
    private Boolean status ;

    private List<RoleDTO> listRole = new ArrayList<>();


}
