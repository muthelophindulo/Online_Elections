package com.Eelections.Elections;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ElectionDTO {
    private String title;

    private String description;

    private LocalDate startDate;

    private LocalDate endDate;
}
