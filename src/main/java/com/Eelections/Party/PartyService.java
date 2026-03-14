package com.Eelections.Party;
/*
* PARTY FUNCTIONALITIES
* 1. SIMPLE CRUD METHODS
* 2. LIST PARTIES
* 3. GET PARTY BY NAME
* 4. GET PARTY BY ABBREVIATION
* */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PartyService {
    @Autowired
    private PartyRepository partyRepository;

    public Party saveParty(Party party){
        return partyRepository.save(party);
    }

    public void deleteParty(Party party){
        partyRepository.delete(party);
    }

    public Party findByParty(String name){
        return partyRepository.findByName(name);
    }

    public Party findById(Long id){
        return partyRepository.getReferenceById(id);
    }

    public List<PartyDTO> getParties(){
        return mapper(partyRepository.findAll());
    }

    public PartyDTO getParty(String name){
        return mapParty(partyRepository.findByName(name));
    }

    public PartyDTO getPartyAbbr(String abbreviation){
        return mapParty(partyRepository.findByAbbreviation(abbreviation));
    }

    private List<PartyDTO> mapper(List<Party> xparties){
        List<PartyDTO> parties = new ArrayList<>();

        for(Party p : xparties){
            parties.add(new PartyDTO(p.getName(),p.getAbbreviation(),p.getPresident(),p.getSlogan(),p.getDescription()));
        }

        return parties;
    }

    private PartyDTO mapParty(Party p) {
        return new PartyDTO(p.getName(),p.getAbbreviation(),p.getPresident(),p.getSlogan(),p.getDescription());
    }


}
