package com.bao.example.thuctap.services;

import com.bao.example.thuctap.dto.UserDTO;
import com.bao.example.thuctap.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public List<User> findAll();

    List<User> findUserWithRoles(String username);

    List<UserDTO> findUserWithRoleDTO(String username);

    List<User> findUserWithRoleDTOjoin(String username);


    List<UserDTO> findUserWithRoleDTO1(String username);
    List<UserDTO> findUserWithRoleDTOSubQuery(String username);
    public Optional<User> findUser(Integer id);

    public ResponseEntity<String> save(UserDTO userDTO);

    public void  delete(User user);

    public   User update(User user);

}
