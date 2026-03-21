package com.Eelections.Votes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteService {
    @Autowired
    private VotesRepository votesRepository;

    public List<Vote> getVotes(){
        return votesRepository.findAll();
    }

    public Vote findById(Long id){
        return votesRepository.getById(id);
    }

    public int getPartyVoteCount(String partyName){
        return votesRepository.findByPartyName(partyName)
                .size();
    }

    public Vote saveVote(Vote vote){
        return votesRepository.save(vote);
    }
}
