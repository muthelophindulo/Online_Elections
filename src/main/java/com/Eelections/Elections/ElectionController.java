package com.Eelections.Elections;

import com.Eelections.Party.Party;
import com.Eelections.Party.PartyDTO;
import com.Eelections.Party.PartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/election")
public class ElectionController {
    @Autowired
    private ElectionService electionService;

    @Autowired
    private PartyService partyService;

    @GetMapping
    public List<ElectionDTO> getElections(){
        return electionService.getElections();
    }

    @GetMapping("/{startdate}/elections")
    public List<ElectionDTO> getByStartDate(@RequestParam String startdate){
        LocalDate startDate = LocalDate.parse(startdate);
        return electionService.getElectionByStartDate(startDate);
    }

    @GetMapping("/elections/{endDate}")
    public List<ElectionDTO> getByEndDate(@RequestParam String endDate){
        LocalDate enDdate = LocalDate.parse(endDate);
        return electionService.getElectionByEndDate(enDdate);
    }

    @PostMapping("/add-election")
    public ResponseEntity<?> addElection(@RequestBody Election election){

        ElectionDTO saved = electionService.saveElection(election);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{title}")
    public ElectionDTO getElectionByTitle(@RequestParam String title){
        return electionService.getElectionByTitle(title);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteElection(@RequestParam Long id) {
        try {
            Election election = electionService.getById(id);
            electionService.deleteElection(election);
            return ResponseEntity.ok(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return ResponseEntity.ok(HttpStatus.NOT_ACCEPTABLE);

        }
    }
    @GetMapping("/{electionTitle}/candidates-count")
    public String candidatesCount(@RequestParam String electionTitle){
        return "Number of candidates are: " + electionService.getNumberOfCandidates(electionTitle);
    }

    @PostMapping("/{electionName}/add-party/{partyName}")
    public ResponseEntity<?> addPartyToElection(@RequestParam String electionName,@RequestParam String partyName){
        try{
            Party party = partyService.findByParty(partyName);
            Election election = electionService.getElection(electionName);

            party.setElection(election);

            return ResponseEntity.ok(partyService.saveParty(party));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{electionName}/parties")
    public List<PartyDTO> getElectionParties(@RequestParam String electionName){
        Election election = electionService.getElection(electionName);
        return partyService.mapper(election.getParties());
    }
}
