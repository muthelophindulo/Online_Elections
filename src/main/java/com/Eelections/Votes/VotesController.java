package com.Eelections.Votes;

import com.Eelections.Party.PartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/votes")
public class VotesController {
    @Autowired
    private VoteService voteService;

    @Autowired
    private PartyService partyService;

    @GetMapping("/list-votes")
    public List<Vote> viewVotes(){
        return voteService.getVotes();
    }

    @GetMapping("/{id}")
    public Vote getById(@RequestParam Long id){
        return voteService.findById(id);
    }

    @GetMapping("/count/{partyName}")
    public int getPartyVoteCount(@RequestParam String partyName){
        return partyService.findParty(partyName).getTotalVotes();
    }

}
