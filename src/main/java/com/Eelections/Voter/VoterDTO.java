package com.Eelections.Voter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoterDTO {
    private String name;
    private String email;
    private String cellNumber;
    private boolean voted;
}
