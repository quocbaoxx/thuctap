package com.bao.example.thuctap.services;

import com.bao.example.thuctap.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    public List<Role> findAll();

    public Optional<Role> findRole(Integer id);

    public  Role save(Role role);

    public Integer  delete(Role role);

    public   Role update(Role role);


}
