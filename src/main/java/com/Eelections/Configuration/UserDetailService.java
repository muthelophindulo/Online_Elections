package com.Eelections.Configuration;

import com.Eelections.Admin.Admin;
import com.Eelections.Admin.AdminService;
import com.Eelections.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class UserDetailService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /*get the user from the database*/
        com.Eelections.User.User user = userService.findByIdNo(username);

        if(user == null){
            throw new UsernameNotFoundException("admin not found");
        }

        return User
                .withUsername(user.getIdNo())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }
}
