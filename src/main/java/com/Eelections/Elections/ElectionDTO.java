package com.Eelections.Elections;

import com.Eelections.Party.Party;
import com.Eelections.Party.PartyDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ElectionDTO {
    private String title;

    private String description;

    private LocalDate startDate;

    private LocalDate endDate;

}
