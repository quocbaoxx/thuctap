package com.bao.example.thuctap.impl;

import com.bao.example.thuctap.model.Category;
import com.bao.example.thuctap.model.Role;
import com.bao.example.thuctap.model.User;
import com.bao.example.thuctap.model.UserRole;
import com.bao.example.thuctap.repositories.UserRoleRepository;
import com.bao.example.thuctap.services.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl  implements UserRoleService {


    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public UserRole save(UserRole userRole){
        return userRoleRepository.save(userRole);

    }

    @Override
    public List<UserRole> findAll(){
        return userRoleRepository.findAll();
    }



}
