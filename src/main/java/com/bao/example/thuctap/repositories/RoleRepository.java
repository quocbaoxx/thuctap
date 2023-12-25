package com.bao.example.thuctap.repositories;

import com.bao.example.thuctap.dto.RoleDTO;
import com.bao.example.thuctap.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {



    @Query("select new com.bao.example.thuctap.dto.RoleDTO(r.id, r.name, r.status)  from Role r")
    List<RoleDTO> findById();// retun về dto


    @Query("select r from UserRole  ur join Role r  on ur.roleid = r.id where ur.userid = :userId ")
    List<Role> findByUserId(@Param("userId") Integer userId);

    @Query("SELECT new com.bao.example.thuctap.dto.RoleDTO(r.name, r.description) " +
            "FROM UserRole ur " +
            "JOIN Role r ON ur.roleid = r.id " +
            "WHERE ur.userid = :userId")
    List<RoleDTO> findRolesByUserId(@Param("userId") Integer userId);
    @Query("SELECT  new com.bao.example.thuctap.dto.RoleDTO(r.id, r.name,r.status, r.description) " +
            "FROM Role r " +
            "WHERE r.id IN (SELECT ur.roleid FROM UserRole ur " +
            "WHERE ur.userid = :userid)")
    List<RoleDTO> findByIdSubQueryDTO(@Param("userid") Integer userid);

    @Query("select  r.id, r.name, r.status from Role r")
    List<RoleDTO> findById1();

   @Query("SELECT r " +
           "FROM Role r " +
           "WHERE r.id IN (SELECT ur.roleid FROM UserRole ur " +
           "WHERE ur.userid = :userid)")
    List<Role> findByIdSubQuery(@Param("userid") Integer userid);




}
