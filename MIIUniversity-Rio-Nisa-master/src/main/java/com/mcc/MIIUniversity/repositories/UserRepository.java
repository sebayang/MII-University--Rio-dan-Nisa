/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcc.MIIUniversity.repositories;

import com.mcc.MIIUniversity.entities.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Gin
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Query(value = "select * from user u where u.username = :username and u.password = :password", nativeQuery = true)
    User findLogin(@Param("username") String username,@Param("password") String password );
}
