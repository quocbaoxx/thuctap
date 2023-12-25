package com.bao.example.thuctap.services;

import com.bao.example.thuctap.model.Role;
import com.bao.example.thuctap.model.User;
import com.bao.example.thuctap.model.UserRole;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserRoleService {

    UserRole save(UserRole userRole);

    List<UserRole> findAll();

}
