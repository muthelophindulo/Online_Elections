package com.Eelections.Voter;

import com.Eelections.User.User;
import com.Eelections.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoterService {
    @Autowired
    private VoterRepository voterRepository;

    public User save(Voter voter){
        return voterRepository.save(voter);
    }
}
