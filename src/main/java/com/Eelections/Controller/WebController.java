package com.Eelections.Controller;

import com.Eelections.Admin.Admin;
import com.Eelections.Admin.AdminRepository;
import com.Eelections.Admin.AdminService;
import com.Eelections.User.User;
import com.Eelections.User.UserService;
import com.Eelections.Voter.Voter;
import com.Eelections.Voter.VoterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class WebController {
    @Autowired
    private VoterService voterService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody Voter user){
        try {
            if(user.getRole().equalsIgnoreCase("voter")){
                Voter x = new Voter();
                x.setPassword(passwordEncoder.encode(user.getPassword()));
                x.setAddress(user.getAddress());
                x.setName(user.getName());
                x.setCellNumber(user.getCellNumber());
                x.setIdNo(user.getIdNo());
                x.setEmail(user.getEmail());
                x.setNationality(user.getNationality());
                x.setRole(user.getRole());
                return ResponseEntity.ok(voterService.save(x));
            } else if (user.getRole().equalsIgnoreCase("admin")) {
                Admin x = new Admin();
                x.setPassword(passwordEncoder.encode(user.getPassword()));
                x.setAddress(user.getAddress());
                x.setName(user.getName());
                x.setCellNumber(user.getCellNumber());
                x.setIdNo(user.getIdNo());
                x.setEmail(user.getEmail());
                x.setNationality(user.getNationality());
                x.setRole(user.getRole());
                x.setAdminNo("231004");
                return ResponseEntity.ok(adminService.Save(x));
            }
            else{
                return ResponseEntity.status(500).build();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
