package com.bao.example.thuctap.dto;

import com.bao.example.thuctap.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {


    private Integer id;
    private String name;
    private Boolean status ;
    private String description;

    public RoleDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }




    public RoleDTO(String name, Boolean status, String description) {
        this.name = name;
        this.status = status;
        this.description = description;
    }

    public RoleDTO(Integer id, String name, Boolean status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }
}
