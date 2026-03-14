package com.Eelections.Party;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Party {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //party info
    @Column(nullable = false,unique = true)
    private String name;

    @Column(nullable = false,unique = true)
    private String president;

    @Column(nullable = false,unique = true)
    private String abbreviation;

    @Column(nullable = true,unique = false)
    private String slogan;

    @Column(nullable = false,unique = false)
    private String description;

    //party Number of Votes
    @Column
    private int totalVotes;

    //gallery
    @Column
    private String image; //Todo: add a column for photo of the party

}
