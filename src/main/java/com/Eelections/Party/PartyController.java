package com.Eelections.Party;

import com.Eelections.Elections.ElectionService;
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

    @Autowired
    private ElectionService electionService;

    @PostMapping("/add/{electionName}")
    public ResponseEntity<Object> addParty(@RequestBody Party party,@RequestParam String electionName){
        try {
            log.info("party being added.........");
            return ResponseEntity.ok(electionService.addParty(party,electionService.getElection(electionName)));
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
