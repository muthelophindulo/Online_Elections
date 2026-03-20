package com.Eelections.Configuration;

import com.Eelections.Admin.Admin;
import com.Eelections.Admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("adminDetailsService")
public class AdminDetailsService implements UserDetailsService {
    @Autowired
    private AdminService adminService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /*get the user from the database*/
        Admin admin = adminService.getByAdminNo(username);

        if(admin == null){
            throw new UsernameNotFoundException("admin not found");
        }

        return User
                .withUsername(admin.getAdminNo())
                .password(admin.getPassword())
                .roles(admin.getRole())
                .build();
    }
}
