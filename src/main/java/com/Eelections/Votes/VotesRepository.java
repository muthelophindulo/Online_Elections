package com.Eelections.Votes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VotesRepository extends JpaRepository<Vote, Long> {
    List<Vote> findByPartyName(String partyName);
}
