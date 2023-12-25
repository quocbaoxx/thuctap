package com.bao.example.thuctap.controller;


import com.bao.example.thuctap.dto.UserDTO;
import com.bao.example.thuctap.dto.UserRoleDTO;
import com.bao.example.thuctap.model.User;
import com.bao.example.thuctap.services.UserRoleService;
import com.bao.example.thuctap.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {


    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;

    @GetMapping
    public ResponseEntity<List<User>> getAll(){
        return ResponseEntity.ok(userService.findAll());
    }


    @GetMapping("/userwithrole/{username}")
    public ResponseEntity<List<User>>getUserWithRoles(@PathVariable String username) {

        List<User> users = userService.findUserWithRoles(username);
        return ResponseEntity.ok(users);
    }
    @GetMapping("/userwithrolejoin/{username}")
    public ResponseEntity<List<User>>getUserWithRolesjoin(@PathVariable String username) {

        List<User> users = userService.findUserWithRoleDTOjoin(username);
        return ResponseEntity.ok(users);
    }


    @GetMapping("/userwithroledto/{username}")
    public ResponseEntity<List<UserDTO>>getUserWithRoleDTO1(@PathVariable String username) {

        List<UserDTO> users = userService.findUserWithRoleDTO1(username);
        return ResponseEntity.ok(users);
    } @GetMapping("/userwithroledtosubquery/{username}")
    public ResponseEntity<List<UserDTO>>getUserWithRoleDTOSubQuery(@PathVariable String username) {

        List<UserDTO> users = userService.findUserWithRoleDTOSubQuery(username);
        return ResponseEntity.ok(users);
    }


    @GetMapping("/{id}")
    public  ResponseEntity<User> getUser(@PathVariable("id") Integer id){

        Optional<User> category = userService.findUser(id);
        return category.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody UserDTO userDTO){
        return  userService.save(userDTO);
    }

    @PutMapping
    public  ResponseEntity<User> update(@RequestBody User user){
        return  ResponseEntity.ok(userService.update(user));
    }


    @DeleteMapping("/{id}")
    public  void  delete(@RequestBody User user){
        userService.delete(user);
    }

}













