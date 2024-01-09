package com.bao.example.thuctap.repositories;

import com.bao.example.thuctap.model.User;
import com.bao.example.thuctap.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {


    List<UserRole> findUserRoleByUserid(Integer userid); //HQL


//    @Query("select  exists (select  1 from  UserRole ur  where ur.roleid =:roleid)")
//    boolean existsByRoles_Id(@Param("roleid") Integer roleid);

//    @Query("SELECT EXISTS(SELECT 1 FROM UserRole ur WHERE ur.roleid = :roleid)")
//    boolean existsByRoles_Id(@Param("roleid") Integer roleid);

    //    @Query(value = "SELECT EXISTS(SELECT 1 FROM tbluserrole ur WHERE ur.role_id = :roleid)", nativeQuery = true)
//    boolean existsByRoles_Id(@Param("roleid") Integer roleid);
    @Query("SELECT CASE WHEN COUNT(ur) > 0 THEN true ELSE false END FROM UserRole ur WHERE ur.roleid = :roleid")
    boolean existsByRoles_Id(@Param("roleid") Integer roleid);




}
