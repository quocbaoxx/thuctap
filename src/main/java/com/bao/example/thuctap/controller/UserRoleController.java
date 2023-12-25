package com.bao.example.thuctap.controller;
import com.bao.example.thuctap.model.UserRole;
import com.bao.example.thuctap.services.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/userrole")
public class UserRoleController {



    @Autowired
    private UserRoleService userRoleService;

    @PostMapping
    public UserRole save(@RequestBody UserRole userRole){
        return  userRoleService.save(userRole);
    }
    @GetMapping
    public ResponseEntity<List<UserRole>> getAll(){
        return ResponseEntity.ok(userRoleService.findAll());
    }


}
