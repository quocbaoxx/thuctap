package com.bao.example.thuctap.repositories;

import com.bao.example.thuctap.model.User;
import com.bao.example.thuctap.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {


    List<UserRole> findUserRoleByUserid(Integer userid); //HQL


//   UserRole findByUserId(Integer userid);
}
