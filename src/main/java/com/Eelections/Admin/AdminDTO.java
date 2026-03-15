package com.Eelections.Admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDTO {
    private String name;
    private String email;
    private String cellNumber;
    private String adminNo;
    private boolean voted;
}
