package com.Eelections.Voter;

import com.Eelections.User.User;
import com.Eelections.User.UserService;
import com.Eelections.Votes.Vote;
import com.Eelections.Votes.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/voter")
public class VoterController {
    @Autowired
    private VoterService voterService;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private VoteService voteService;

    @PostMapping("/register")
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
            User savedVoter = (Voter) voterService.save(x);
            return ResponseEntity.ok(new VoterDTO(savedVoter.getName(),savedVoter.getEmail(),savedVoter.getCellNumber(),savedVoter.isVoted()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/view/name/{name}")
    public VoterDTO viewVoter(@RequestParam String name){
        return voterService.getVoter(name);
    }

    @GetMapping("/voter-list")
    public List<VoterDTO> getVoters(){
        return voterService.getVoters();
    }

    @GetMapping("/delete/{name}")
    public ResponseEntity<Object> deleteVoter(@RequestParam String name,Principal principal){
        String username = principal.getName();
        User x = userService.findByName(username);
        if(x.getName().equals(name)){
            userService.deleteUser(x);
            return ResponseEntity.ok(HttpStatus.ACCEPTED);
        }
        return ResponseEntity.ok(HttpStatus.NOT_ACCEPTABLE);
    }

    @GetMapping("/vote/{partyname}")
    public ResponseEntity<?> vote(@RequestParam String partyName, Principal principal){
        Vote vote = new Vote();
        try{
            if(voterService.vote(partyName,principal.getName())){
                User voter = voterService.findVoter(voterService.getVoterByIdNo(principal.getName()).getName());
                voter.setVoted(true);
                userService.saveUser(voter);
                vote.setIdNo(voter.getIdNo());
                vote.setPartyName(partyName);
                voteService.saveVote(vote);
                return ResponseEntity.ok(HttpStatus.ACCEPTED);
            }else{
                return ResponseEntity.ok(HttpStatus.NOT_ACCEPTABLE);
            }
        } catch (Exception e) {
            return ResponseEntity.ok(HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
