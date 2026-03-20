package com.Eelections.Admin;

import com.Eelections.Party.Party;
import com.Eelections.Party.PartyDTO;
import com.Eelections.Party.PartyService;
import com.Eelections.User.User;
import com.Eelections.Voter.Voter;
import com.Eelections.Voter.VoterDTO;
import com.Eelections.Voter.VoterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AdminService adminService;

    @Autowired
    private PartyService partyService;

    @Autowired
    private VoterService voterService;

    @PostMapping("/add-admin")
    public ResponseEntity<?> addAdmin(@RequestBody Admin user){
        try{
            Admin x = new Admin();
            x.setPassword(passwordEncoder.encode(user.getPassword()));
            x.setAddress(user.getAddress());
            x.setName(user.getName());
            x.setCellNumber(user.getCellNumber());
            x.setIdNo(user.getIdNo());
            x.setEmail(user.getEmail());
            x.setNationality(user.getNationality());
            x.setRole("ADMIN");
            x.setAdminNo("226");
            Admin saved = adminService.Save(x);
            saved.setAdminNo("22600"+saved.getId());
            adminService.Save(saved);
            return ResponseEntity.ok(new AdminDTO(saved.getName(),saved.getEmail(),saved.getCellNumber(),saved.getAdminNo()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/add-party")
    public ResponseEntity<Object> addParty(@RequestBody Party party){
        try {
            Party party1 = new Party();
            party1.setAbbreviation(party.getAbbreviation());
            party1.setName(party.getName());
            party1.setDescription(party.getDescription());
            party1.setImage(party.getImage());
            party1.setPresident(party.getPresident());
            party1.setSlogan(party.getSlogan());
            party1.setTotalVotes(0);
            log.info("party being added.........");
            return ResponseEntity.ok(adminService.addParty(party1));
        } catch (Exception e) {
            log.info("failed to add party");
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/add-voter")
    public ResponseEntity<Object> registerVoter(@RequestBody Voter user){
        try{
            Voter x = new Voter();
            x.setPassword(passwordEncoder.encode(user.getPassword()));
            x.setAddress(user.getAddress());
            x.setName(user.getName());
            x.setCellNumber(user.getCellNumber());
            x.setIdNo(user.getIdNo());
            x.setEmail(user.getEmail());
            x.setNationality(user.getNationality());
            x.setRole("VOTER");
            User savedVoter = (Voter) adminService.addVoter(x);
            return ResponseEntity.ok(new VoterDTO(savedVoter.getName(),savedVoter.getEmail(),savedVoter.getCellNumber(),savedVoter.isVoted()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/delete-voter/{name}")
    public ResponseEntity<?> deleteVoter(@RequestParam String name){
        try{
            User voter = voterService.findVoter(name);
            adminService.deleteVoter((Voter) voter);

            if(voterService.findVoter(name) == null){
                return ResponseEntity.ok(HttpStatus.ACCEPTED);
            }else{
                return ResponseEntity.ok(HttpStatus.NOT_ACCEPTABLE);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @GetMapping("/list-parties")
    public List<PartyDTO> getParties(){
        return adminService.getParties();
    }
}
