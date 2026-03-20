package com.Eelections.Admin;
/*
* 1. SIMPLE CRUD METHODS
* 2. ADD ANOTHER ADMIN
* 3. ADD A PARTY
* 4. ADD A VOTER
* 5. LIST VOTERS
* */
import com.Eelections.Party.Party;
import com.Eelections.Party.PartyDTO;
import com.Eelections.Party.PartyService;
import com.Eelections.User.User;
import com.Eelections.User.UserService;
import com.Eelections.Voter.Voter;
import com.Eelections.Voter.VoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PartyService partyService;

    @Autowired
    private UserService userService;

    @Autowired
    private VoterService voterService;

    //ADMIN FUNCTIONALITY
    public Admin Save(Admin admin){
        return adminRepository.save(admin);
    }

    public Admin getByAdminNo(String adminNo){
        return adminRepository.getByAdminNo(adminNo);
    }

    public void delete(Admin admin){
        adminRepository.delete(admin);
    }

    // ADMIN PARTY FUNCTIONALITY
    public PartyDTO addParty(Party party){
        return partyService.saveParty(party);
    }

    public void deleteParty(Party party){
        partyService.deleteParty(party);
    }

    public List<PartyDTO> getParties(){
        return partyService.getParties();
    }


    //ADMIN USERS FUNCTIONALITY
    public User addVoter(Voter voter){
        return voterService.save(voter);
    }

    public void deleteVoter(Voter voter){
        voterService.delete(voter);
    }



}
