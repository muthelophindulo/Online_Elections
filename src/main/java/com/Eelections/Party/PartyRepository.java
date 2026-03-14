package com.Eelections.Party;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PartyRepository extends JpaRepository<Party,Long> {
    Party findByName(String name);
    Party findByAbbreviation(String abbreviation);
}
