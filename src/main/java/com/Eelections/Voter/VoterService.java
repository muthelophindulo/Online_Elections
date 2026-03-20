package com.Eelections.Voter;
/*
* 1. SIMPLE CRUD METHODS
* 2. GET LIST OF VOTERS
* 3. GET BY ID NUMBER
* 4. VOTE
* */
import com.Eelections.Party.Party;
import com.Eelections.Party.PartyService;
import com.Eelections.User.User;
import com.Eelections.User.UserRepository;
import com.Eelections.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VoterService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PartyService partyService;

    public User save(Voter voter){
        return userService.saveUser(voter);
    }

    public void delete(Voter voter){
        userService.deleteUser(voter);
    }

    public List<VoterDTO> getVoters(){
        return mapVoters(userRepository.findAll());
    }

    public VoterDTO getVoter(String name){
        return mapVoter(userRepository.findByName(name));
    }

    public User findVoter(String name){
        return (User) userRepository.findByName(name);
    }

    public VoterDTO getVoterByIdNo(String id){
        return mapVoter(userRepository.findByIdNo(id));
    }
    public Voter findVoterByIdNo(String id){
        return (Voter)userRepository.findByIdNo(id);
    }

    private List<VoterDTO> mapVoters(List<User> voters){
        List<VoterDTO> voterDTOList = new ArrayList<>();

        for(User x : voters){
            voterDTOList.add(new VoterDTO(x.getName(),x.getEmail(), x.getCellNumber(), x.isVoted()));
        }

        return voterDTOList;
    }
    private VoterDTO mapVoter(User voter){
        return new VoterDTO(voter.getName(), voter.getEmail(), voter.getCellNumber(),voter.isVoted());
    }

    public boolean vote(String partyName,String username){
        try{
            //check if the user has voted
            User user = userService.findByIdNo(username);

            if(!user.isVoted()){
                //get the party
                Party party = partyService.findByParty(partyName);

                party.setTotalVotes(party.getTotalVotes() +1);

                return partyService.saveParty(party) != null;
            }else{
                return false;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
