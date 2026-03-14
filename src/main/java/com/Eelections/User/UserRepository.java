package com.Eelections.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<User,String> {
    //get the user by the id
    User findByIdNo(String idNo);

    //get by name
    User findByName(String name);

    //get by userId
    User findById(Long id);
}
