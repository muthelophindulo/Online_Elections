package com.Eelections.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public Admin Save(Admin admin){
        return adminRepository.save(admin);
    }
}
