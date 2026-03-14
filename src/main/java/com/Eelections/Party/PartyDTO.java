package com.Eelections.Party;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartyDTO {
    private String name;
    private String abbreviation;
    private String president;
    private String slogan;
    private String description;

}
