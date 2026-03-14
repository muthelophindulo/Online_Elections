package com.Eelections.Party;

import com.Eelections.User.User;
import com.Eelections.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartyService {
    @Autowired
    private PartyRepository partyRepository;

    public Party saveUser(Party party){
        return partyRepository.save(party);
    }

    public void deleteUser(Party party){
        partyRepository.delete(party);
    }

    public Party findByName(String name){
        return partyRepository.findByName(name);
    }

    public Party findById(Long id){
        return partyRepository.getReferenceById(id);
    }
}
