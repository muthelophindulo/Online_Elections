package com.Eelections.Elections;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ElectionRepository extends JpaRepository<Election,Long> {
    Election findByTitle(String name);
    List<Election> getByEndDate(LocalDate endDate);
    List<Election> getByStartDate(LocalDate startDate);
}
