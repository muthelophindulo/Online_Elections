package com.Eelections.Elections;
/*
* 1. get all the elections
* 2. get elections by name
* 3. get elections by end date
* 4. get election by start date
* 5. add and delete elections
* 6. get the number of candidates participating
* */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ElectionService {
    @Autowired
    private ElectionRepository electionRepository;

    public List<ElectionDTO> getElections(){
        return electionDTOList(electionRepository.findAll());
    }

    public ElectionDTO getElectionByTitle(String name){
        return electionDTO(electionRepository.findByTitle(name));
    }

    public Election getElection(String name){
        return electionRepository.findByTitle(name);
    }

    public List<ElectionDTO> getElectionByStartDate(LocalDate startDate){
        return electionDTOList(electionRepository.getByStartDate(startDate));
    }

    public List<ElectionDTO> getElectionByEndDate(LocalDate endDate){
        return electionDTOList(electionRepository.getByEndDate(endDate));
    }

    public ElectionDTO saveElection(Election election){

        return electionDTO(electionRepository.save(election));
    }

    public void deleteElection(Election election){
        electionRepository.delete(election);
    }

    public Election getById(Long id){
        return electionRepository.getReferenceById(id);
    }

    public int getNumberOfCandidates(String title){
        return electionRepository.findByTitle(title).getParties().size();
    }


    private List<ElectionDTO> electionDTOList(List<Election> elections){
        List<ElectionDTO> electionDTOList = new ArrayList<>();

        for(Election x : elections){
            electionDTOList.add(new ElectionDTO(x.getTitle(),x.getDescription(),x.getStartDate(),x.getEndDate()));
        }

        return electionDTOList;
    }

    private ElectionDTO electionDTO(Election election){
        if(election != null){
            return new ElectionDTO(election.getTitle(), election.getDescription(), election.getStartDate(),election.getEndDate());
        }else{
            return new ElectionDTO();
        }
    }
}
