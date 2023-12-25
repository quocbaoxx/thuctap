package com.bao.example.thuctap.controller;


import com.bao.example.thuctap.model.Role;
import com.bao.example.thuctap.repositories.RoleRepository;
import com.bao.example.thuctap.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping
    public ResponseEntity<List<Role>> getAll(){
        return ResponseEntity.ok(roleService.findAll());
    }

    @GetMapping("/{id}")
    public  ResponseEntity<Role> getRole(@PathVariable("id") Integer id){

        Optional<Role> category = roleService.findRole(id);
        return category.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PostMapping
    public ResponseEntity<Role> save(@RequestBody  Role role){
        return  ResponseEntity.ok(roleService.save(role));
    }

    @PutMapping
    public  ResponseEntity<Role> update(@RequestBody Role role){
        return  ResponseEntity.ok(roleService.update(role));
    }


    @DeleteMapping("/{id}")
    public  ResponseEntity<String>  delete(@PathVariable("id") Integer id){
        Role roleToDelete = roleRepository.findById(id).orElseThrow();
        roleService.delete(roleToDelete);
        return  ResponseEntity.ok("ok");

    }
}
