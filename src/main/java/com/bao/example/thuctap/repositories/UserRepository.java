package com.bao.example.thuctap.repositories;

import com.bao.example.thuctap.dto.UserWithRolesDTO;
import com.bao.example.thuctap.model.Role;
import com.bao.example.thuctap.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

//    boolean existsByRoles_Id(Integer roleId);

    List<User> findByUsername(String username);


//    @Query("SELECT ur.roleId FROM UserRole ur WHERE ur.userId = :userId")
//    List<Integer> findRolesByUserId(@Param("userId") Integer userId);

//    List<Role> findByIdIn(List<Integer> roleIds);

    @Query(value ="SELECT u.username, r.name " +
            "FROM tbluser u " +
            "JOIN tbluserrole ur ON u.id = ur.user_id " +
            "JOIN tblrole r ON ur.role_id = r.id " +
            "WHERE u.username = :username", nativeQuery = true)
    List<User> findByUserAndRoles(@Param("username") String username);

}
