package com.Eelections.Party;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/party")
public class PartyController {
    @Autowired
    private PartyService partyService;

    @PostMapping("/add")
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
            return ResponseEntity.ok(partyService.saveParty(party1));
        } catch (Exception e) {
            log.info("failed to add party");
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/list")
    public List<PartyDTO> getParties(){
        return partyService.getParties();
    }

    @GetMapping("/get-party/name/{name}")
    public PartyDTO getParty(@RequestParam String name){
        return partyService.getParty(name);
    }

    @GetMapping("/get-party/abbreviation/{abbreviation}")
    public PartyDTO getPartyAbbr(@RequestParam String abbreviation){
        return partyService.getPartyAbbr(abbreviation);
    }

}
