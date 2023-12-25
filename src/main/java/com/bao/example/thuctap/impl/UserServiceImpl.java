package com.bao.example.thuctap.impl;

import com.bao.example.thuctap.dto.RoleDTO;
import com.bao.example.thuctap.dto.UserDTO;
import com.bao.example.thuctap.model.Role;
import com.bao.example.thuctap.model.User;
import com.bao.example.thuctap.model.UserRole;
import com.bao.example.thuctap.repositories.RoleRepository;
import com.bao.example.thuctap.repositories.UserRepository;
import com.bao.example.thuctap.repositories.UserRoleRepository;
import com.bao.example.thuctap.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @Override
    public List<User> findUserWithRoles(String username) {

        List<User> users = userRepository.findByUsername(username);

        if(CollectionUtils.isEmpty(users)) {
            return null;
        }
        for (User user : users) {
            List<Role> roles = roleRepository.findByUserId(user.getId());
            user.setRoles(roles);
        }

        return users;

    }

    @Override
    public List<User> findUserWithRoleDTOjoin(String username) {
        List<User> users = userRepository.findByUserAndRoles(username);
        return  users;
    }

    @Override
    public  List<UserDTO> findUserWithRoleDTO1(String username) {

        List<UserDTO> userDTOList = new ArrayList<>();
        List<User> allUsers ;
        if (!username.isEmpty()){
            allUsers = userRepository.findByUsername(username);
        } else {
            allUsers = userRepository.findAll();
        }

        for (User user : allUsers){
            List<RoleDTO> roles = roleRepository.findRolesByUserId(user.getId());

            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setUsername(user.getUsername());
            userDTO.setPassword(user.getPassword());
            userDTO.setName(user.getName());
            userDTO.setStatus(user.getStatus());

            List<RoleDTO> roleDTOList = new ArrayList<>();
            roles.forEach(role -> {
                RoleDTO roleDTO = new RoleDTO(role.getName(), role.getDescription());
                roleDTOList.add(roleDTO);

            });

            userDTO.setListRole(roleDTOList);
            userDTOList.add(userDTO);

        }

        return  userDTOList;

    }



    @Override
    public  List<UserDTO> findUserWithRoleDTOSubQuery(String username) {

        List<UserDTO> userDTOList = new ArrayList<>();
        List<User> allUsers ;
        if (!username.isEmpty()){
            allUsers = userRepository.findByUsername(username);
        } else {
            allUsers = userRepository.findAll();
        }

        for (User user : allUsers){
            List<RoleDTO> roles = roleRepository.findByIdSubQueryDTO(user.getId());

            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setUsername(user.getUsername());
            userDTO.setPassword(user.getPassword());
            userDTO.setName(user.getName());
            userDTO.setStatus(user.getStatus());

            List<RoleDTO> roleDTOList = new ArrayList<>();
            roles.forEach(role -> {
                RoleDTO roleDTO = new RoleDTO(role.getId(),role.getName(),role.getStatus(),role.getDescription());
                roleDTOList.add(roleDTO);
            });

            userDTO.setListRole(roleDTOList);
            userDTOList.add(userDTO);

        }

        return  userDTOList;

    }


    @Override
    public List<UserDTO> findUserWithRoleDTO(String username) {//không nên làm như này

        /**TODO:
         *  1: Return DTO 2 truong va dung join
         *  2: Return DTO 4 truong va dung " in , subqurey"
         */

        List<UserDTO> userDTOList = new ArrayList<>();
        List<User> allUsers ;
        if (!username.isEmpty()){
            allUsers = userRepository.findByUsername(username);
        } else {
            allUsers = userRepository.findAll();
        }


        for (User user : allUsers) {

            List<UserRole> userRoles = userRoleRepository.findUserRoleByUserid(user.getId());
            List<RoleDTO> roleDTOList = new ArrayList<>();

            for (UserRole userRole : userRoles) {
                Optional<Role> optionalRole = roleRepository.findById(userRole.getRoleid());

                if (optionalRole.isPresent()) {
                    Role role = optionalRole.get();
                    RoleDTO roleDTO = new RoleDTO();
                    roleDTO.setId(role.getId());
                    roleDTO.setName(role.getName());
                    roleDTO.setStatus(role.getStatus());
                    roleDTO.setDescription(role.getDescription());
                    roleDTOList.add(roleDTO);
                }
            }

            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setUsername(user.getUsername());
            userDTO.setName(user.getName());
            userDTO.setPassword(user.getPassword());
            userDTO.setStatus(user.getStatus());
            userDTO.setListRole(roleDTOList);
            userDTOList.add(userDTO);
        }

        return userDTOList;
    }


    @Override
    public Optional<User> findUser(Integer id){
        return userRepository.findById(id);
    }

    @Override
    public  ResponseEntity<String> save(UserDTO userDTO){

        User user = new User();
        user.setName(userDTO.getName());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setStatus(true);

        userRepository.save(user);

        //Lấy id vừa được thêm
        Integer userid  = user.getId();

        List<Role> roles = new ArrayList<>();

        for(RoleDTO roleDTO : userDTO.getListRole()) {
            Role role = new Role();
            role.setName(roleDTO.getName());
            role.setDescription(roleDTO.getDescription());
            role.setStatus(true);
            roles.add(role);
        }

        if(!roles.isEmpty()){
            roleRepository.saveAll(roles);
            List<UserRole> userRoles = new ArrayList<>();
            for(Role e : roles){
                UserRole userRole = new UserRole();
                userRole.setRoleid(e.getId());
                userRole.setUserid(userid);

                userRoles.add(userRole);
            }
            userRoleRepository.saveAll(userRoles);
        }


        return ResponseEntity.ok("ok");


    }

    @Override
    public  User update(User user){
        return  userRepository.save(user);
    }



    @Override
    public  void  delete(User user){
        userRepository.delete(user);
    }




}
