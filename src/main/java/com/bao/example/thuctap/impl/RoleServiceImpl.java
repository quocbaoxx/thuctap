package com.bao.example.thuctap.impl;

import com.bao.example.thuctap.model.Role;
import com.bao.example.thuctap.repositories.RoleRepository;
import com.bao.example.thuctap.repositories.UserRepository;
import com.bao.example.thuctap.repositories.UserRoleRepository;
import com.bao.example.thuctap.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl  implements RoleService {


    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;


    @Override
    public List<Role> findAll(){
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> findRole(Integer id){
        return roleRepository.findById(id);
    }

    @Override
    public  Role save(Role category){
        return  roleRepository.save(category);
    }


    @Override
    public Role update(Role role){
        return  roleRepository.save(role);
    }



    public Integer delete(Role role) {

//        if(isRoleLinkedToUser(role.getId())){
//            throw  new IllegalStateException("Không xoá đc");
//        }
        role.setStatus(Boolean.FALSE);
        Role deletedRole = roleRepository.save(role);
        return  deletedRole.getId();

    }

//    public boolean isRoleLinkedToUser(Integer roleid) {
//        return userRepository.existsByRoles_Id(roleid);
//    }



}
