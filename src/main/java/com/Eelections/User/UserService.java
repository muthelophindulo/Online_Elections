package com.Eelections.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public void deleteUser(User user){
        userRepository.delete(user);
    }

    public User findByIdNo(String idNo){
        return userRepository.findByIdNo(idNo);
    }

    public User findByName(String name){
        return userRepository.findByName(name);
    }

    public User findById(Long id){
        return userRepository.findById(id);
    }
}
